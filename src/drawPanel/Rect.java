package src.drawPanel;

import java.awt.*;

public class Rect extends BasicObject{
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(100, 100, 100));
        g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }

    public Rect(int x, int y){
        super(x, y);
    }
}
