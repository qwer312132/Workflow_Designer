package src.drawPanel;

import java.awt.*;

public abstract  class BasicObject {
    public int x1, x2, y1, y2;
    public abstract void draw(Graphics2D g);
    public BasicObject(int x, int y){
        x1 = x;
        y1 = y;
        x2 = x+50;
        y2 = y+100;
    }
    public int getUpperLeftX() {
        return Math.min(x1, x2);
    }
    public int getUpperLeftY() {
        return Math.min(y1, y2);
    }
    public int getWidth() {
        return Math.abs(x2 - x1);
    }
    public int getHeight() {
        return Math.abs(y2 - y1);
    }
}
