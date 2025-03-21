package src.drawPanel;

import java.awt.*;

public class Oval extends BasicObject{
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(100, 100, 100));
        g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }

    public Oval(int x, int y){
        super(x, y);
    }
}
