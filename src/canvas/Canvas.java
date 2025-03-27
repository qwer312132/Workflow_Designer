package src.canvas;

import src.button.ButtonBar;
import src.button.ButtonConstant.ButtonState;
import src.button.ButtonStateListener;
import src.canvas.object.BasicObject;
import src.canvas.object.Oval;
import src.canvas.object.Rect;
import src.canvas.object.PreviewSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Canvas extends JPanel implements ButtonStateListener {
    private final JLabel test;
    private ButtonState curButtonState;
    private PreviewSelect previewSelect;
    private boolean isSelecting;
    private final ArrayList<Integer> selecting = new ArrayList<>();
    private final ArrayList<BasicObject> basicObjects = new ArrayList<>();

    public Canvas(ButtonBar buttonBar) {
        setBackground(Color.WHITE);
        test = new JLabel();
        test.setText(buttonBar.getButtonState().toString());
        curButtonState = buttonBar.getButtonState();
        isSelecting = false;
        this.add(test);
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        // **註冊監聽器**
        buttonBar.addButtonStateListener(this);
    }

    // **當 ButtonBar 狀態變更時，這個方法會被呼叫**
    @Override
    public void onButtonStateChanged(ButtonState newState) {
        test.setText(newState.toString()); // 更新 JLabel 顯示
        curButtonState = newState;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        for(BasicObject basicObject : basicObjects) {
            basicObject.draw(g2);
        }
        if(previewSelect != null && !isSelecting) {
            previewSelect.draw(g2);
        }
        g.drawImage(image, 0, 0, null);
    }

    private class MouseHandler extends MouseAdapter {
        // creates and sets the initial position for the new shape
        private int x1, x2, y1, y2;
        public void mousePressed(MouseEvent e) {
            if(curButtonState == ButtonState.RECT){
                basicObjects.add(new Rect(e.getX(), e.getY()));
            }
            else if(curButtonState == ButtonState.OVAL){
                basicObjects.add(new Oval(e.getX(), e.getY()));
            }
            else if(curButtonState == ButtonState.SELECT){
                x1 = e.getX();
                y1 = e.getY();
                previewSelect = new PreviewSelect(x1, y1, x1, y1);
                isSelecting = false;
                for(int i = 0; i < basicObjects.size(); i++){
                    BasicObject basicObject = basicObjects.get(i);
                    if(basicObject.isClicked(e.getX(), e.getY())){
                        System.out.println("click");
                        basicObject.isSelected = true;
                        isSelecting = true;
                        selecting.add(i);
                    }
                    else{
                        basicObject.isSelected = false;
                    }
                }
            }
            repaint();
        }
        public void mouseDragged(MouseEvent e){
            x2 = e.getX();
            y2 = e.getY();
            if(curButtonState == ButtonState.SELECT){
                previewSelect.setX2(x2);
                previewSelect.setY2(y2);
                if(isSelecting){
                    for(Integer i : selecting){
                        basicObjects.get(i).move(x2 - x1, y2- y1);
                    }
                    x1 = x2;
                    y1 = y2;
                }
            }
            repaint();
        }
        public void mouseReleased(MouseEvent e){
            x2 = e.getX();
            y2 = e.getY();
            if(curButtonState == ButtonState.SELECT){
                for(BasicObject basicObject:basicObjects){
                    if(basicObject.isBoxed(x1, y1, x2, y2)){
                        System.out.println("box");
                    }
                }
            }
            previewSelect = null;
            selecting.clear();
            repaint();
        }
    }
}
