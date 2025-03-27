package src.canvas.object;

import java.awt.*;
import java.util.ArrayList;

public class Rect extends BasicObject{
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(100, 100, 100));
        g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        if(isSelected){
            for(ConnectPoint connectPoint : connectPointList){
                connectPoint.draw(g);
            }
        }
    }

    public Rect(int x, int y){
        super(x, y);
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
