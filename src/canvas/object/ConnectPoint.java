package src.canvas.object;

import java.awt.*;

public class ConnectPoint {
    int x, y;
    int size = 5;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ConnectPoint(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public boolean isIn(int x, int y){
        return getX()-size <= x && getX()+size >= x && getY()-size <= y && getY()+size >= y;
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x-size, y-size, 10, 10);
    }

    public void move(int x, int y){
        setX(getX()+x);
        setY(getY()+y);
    }
}
