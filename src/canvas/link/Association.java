package src.canvas.link;

import src.canvas.object.ConnectPoint;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class Association extends Link{

    public Association(ConnectPoint start, ConnectPoint end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics2D g) {
        g.draw(new Line2D.Double(start.getX(), start.getY(), end.getX(), end.getY()));
        double angle = Math.atan2(end.getY() - start.getY(), end.getX() - start.getX());
        AffineTransform tx = g.getTransform();
        int x = end.getX();
        int y = end.getY();
        int size = 10;
        g.translate(x, y);
        g.rotate(angle - Math.PI / 2);
        Path2D.Double arrowHead = new Path2D.Double();
        arrowHead.moveTo(-size, -size);
        arrowHead.lineTo(0, 0);
        arrowHead.lineTo(size, -size);
        g.draw(arrowHead);
        g.setTransform(tx);
    }
}
