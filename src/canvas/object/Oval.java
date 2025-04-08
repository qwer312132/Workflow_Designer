package src.canvas.object;

import java.awt.*;
import java.util.ArrayList;

public class Oval extends BasicObject{
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(100, 100, 100));
        g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        if(isSelected()){
            for(ConnectPoint connectPoint : connectPointList){
                connectPoint.draw(g);
            }
        }
    }

    public Oval(int x, int y, int id){
        super(x, y, id);
        setX2(x+100);
        setY2(y+50);
        connectPointList = new ArrayList<>();
        int []connectpointX={50,0,100,50};
        int []connectpointY={0,25,25,50};
        for(int i = 0; i < connectpointX.length; i++){
            connectPointList.add(new ConnectPoint(getX1() + connectpointX[i], getY1() + connectpointY[i]));
        }
    }
}
