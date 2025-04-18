package src.canvas.object;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Rect extends BasicObject{
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(100, 100, 100));
        g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        if(isSelected()){
            for(ConnectPoint connectPoint : connectPointList){
                connectPoint.draw(g);
            }
        }
        if(!Objects.equals(getLabelText(), "")){
            if(getLabelType() == LabelType.RECT){
                g.setColor(getLabelColor());
                g.fillRect(getUpperLeftX()+5, getUpperLeftY()+35, getWidth()-10, 30);
                g.setColor(Color.BLACK);
                g.drawRect(getUpperLeftX()+5, getUpperLeftY()+35, getWidth()-10, 30);
            }
            else if(getLabelType() == LabelType.OVAL){
                g.setColor(getLabelColor());
                g.fillOval(getUpperLeftX()+5, getUpperLeftY()+35, getWidth()-10, 30);
                g.setColor(Color.BLACK);
                g.drawOval(getUpperLeftX()+5, getUpperLeftY()+35, getWidth()-10, 30);
            }
            g.setFont(new Font("Arial", Font.BOLD, getLabelSize()));
            g.drawString(getLabelText(), getUpperLeftX()+15, getUpperLeftY()+50);
        }
    }

    public Rect(int x, int y, int id){
        super(x, y, id);
        setX2(x+100);
        setY2(y+100);
        connectPointList = new ArrayList<>();
        int []connectpointX={0,50,100,0,100,0,50,100};
        int []connectpointY={0,0,0,50,50,100,100,100};
        for(int i = 0; i < connectpointX.length; i++){
            connectPointList.add(new ConnectPoint(getX1() + connectpointX[i], getY1() + connectpointY[i]));
        }
    }
}
