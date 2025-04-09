package src.canvas;

import src.button.ButtonBar;
import src.button.ButtonConstant.ButtonState;
import src.button.ButtonStateListener;
import src.canvas.link.Association;
import src.canvas.link.Composition;
import src.canvas.link.Link;
import src.canvas.link.Generalization;
import src.canvas.object.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Canvas extends JPanel implements ButtonStateListener {
    private final JLabel test;
    private ButtonState curButtonState;
    private PreviewSelect previewSelect;
    private Link previewLink;
    private boolean isSelecting;
    private final ArrayList<CompositeObject> selecting = new ArrayList<>();
    private final ArrayList<BasicObject> basicObjects = new ArrayList<>();
    private final ArrayList<Link> links = new ArrayList<>();

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

    public ArrayList<CompositeObject> getSelectedObjects() {
        return new ArrayList<>(selecting);
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
        for(Link link : links){
            link.draw(g2);
        }
        if(previewSelect != null && !isSelecting) {
            previewSelect.draw(g2);
        }
        if(previewLink!=null){
            previewLink.draw(g2);
        }
        g.drawImage(image, 0, 0, null);
    }

    private class MouseHandler extends MouseAdapter {
        // creates and sets the initial position for the new shape
        private int x1, x2, y1, y2;
        public void mousePressed(MouseEvent e) {
            if(curButtonState == ButtonState.RECT){
                basicObjects.add(new Rect(e.getX(), e.getY(), basicObjects.size()));
            }
            else if(curButtonState == ButtonState.OVAL){
                basicObjects.add(new Oval(e.getX(), e.getY(), basicObjects.size()));
            }
            else if(curButtonState == ButtonState.SELECT){
                x1 = e.getX();
                y1 = e.getY();
                previewSelect = new PreviewSelect(x1, y1, x1, y1);
                selecting.clear();
                isSelecting = false;
                for(BasicObject basicObject : basicObjects){
                    if(basicObject.isClicked(e.getX(), e.getY())){
                        isSelecting = true;
                        selecting.add(basicObject.getComposite().getRoot());
                    }
                    else{
                        basicObject.setSelected(false);
                    }
                }
                if(selecting.size()>0){
                    CompositeObject temp = selecting.getLast();
                    for(int i = 0; i < selecting.size()-1; i++){
                        selecting.get(i).unselect();
                    }
                    selecting.clear();
                    selecting.add(temp);
                    temp.select();
                }

            }
            else if(curButtonState == ButtonState.ASSOCIATION){
                x1 = e.getX();
                y1 = e.getY();
                for(BasicObject basicObject:basicObjects){
                    if(basicObject.inConnectPoint(x1, y1)!=null){
                        previewLink = new Association(basicObject.inConnectPoint(x1, y1), basicObject.inConnectPoint(x1, y1));
                    }
                }
            }
            else if(curButtonState == ButtonState.GENERALIZATION){
                x1 = e.getX();
                y1 = e.getY();
                for(BasicObject basicObject:basicObjects){
                    if(basicObject.inConnectPoint(x1, y1)!=null){
                        previewLink = new Generalization(basicObject.inConnectPoint(x1, y1), basicObject.inConnectPoint(x1, y1));
                    }
                }
            }
            else if(curButtonState == ButtonState.COMPOSITION){
                x1 = e.getX();
                y1 = e.getY();
                for(BasicObject basicObject:basicObjects){
                    if(basicObject.inConnectPoint(x1, y1)!=null){
                        previewLink = new Composition(basicObject.inConnectPoint(x1, y1), basicObject.inConnectPoint(x1, y1));
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
                    for(CompositeObject compositeObject : selecting){
                        compositeObject.move(x2 - x1, y2- y1);
                    }
                    x1 = x2;
                    y1 = y2;
                }
            }
            else if((curButtonState == ButtonState.ASSOCIATION || curButtonState == ButtonState.GENERALIZATION || curButtonState == ButtonState.COMPOSITION)&&previewLink!=null){
                previewLink.setEnd(new ConnectPoint(x2, y2));
            }
            repaint();
        }
        public void mouseReleased(MouseEvent e){
            x2 = e.getX();
            y2 = e.getY();
            if(curButtonState == ButtonState.SELECT){
                if(selecting.size()==1)
                    return;
                Set<CompositeObject> selectingSet = new HashSet<>();
                selecting.clear();
                for(BasicObject basicObject:basicObjects){
                    if(basicObject.isBoxed(x1, y1, x2, y2)){
                        selectingSet.add(basicObject.getComposite().getRoot());
                    }
                }
                for(CompositeObject compositeObject : selectingSet){
                    compositeObject.select();
                    selecting.add(compositeObject);
                }
            }
            else if(curButtonState == ButtonState.ASSOCIATION){
                x2 = e.getX();
                y2 = e.getY();
                for(BasicObject basicObject:basicObjects){
                    if(basicObject.inConnectPoint(x2, y2)!=null){
                        links.add(new Association(previewLink.getStart(), basicObject.inConnectPoint(x2, y2)));
                    }
                }
            }
            else if(curButtonState == ButtonState.GENERALIZATION){
                x2 = e.getX();
                y2 = e.getY();
                for(BasicObject basicObject:basicObjects){
                    if(basicObject.inConnectPoint(x2, y2)!=null){
                        links.add(new Generalization(previewLink.getStart(), basicObject.inConnectPoint(x2, y2)));
                    }
                }
            }
            else if(curButtonState == ButtonState.COMPOSITION){
                x2 = e.getX();
                y2 = e.getY();
                for(BasicObject basicObject:basicObjects){
                    if(basicObject.inConnectPoint(x2, y2)!=null){
                        links.add(new Composition(previewLink.getStart(), basicObject.inConnectPoint(x2, y2)));
                    }
                }
            }
            previewSelect = null;
            previewLink = null;
            repaint();
        }
    }
}
