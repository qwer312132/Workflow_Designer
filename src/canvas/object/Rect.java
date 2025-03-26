package src.canvas.object;

import java.awt.*;

public class Rect extends BasicObject{
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(100, 100, 100));
        g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }

    public Rect(int x, int y){
        super(x, y);
        setX2(x+50);
        setY2(y+100);
    }
    public Rect(int x1, int y1, int x2, int y2){
        super(x1, y1);
        this.x2 = x2;
        this.y2 = y2;
    }
}
