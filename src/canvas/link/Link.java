package src.canvas.link;

import src.canvas.object.ConnectPoint;

import java.awt.*;

public abstract class Link {
    ConnectPoint start, end;

    public ConnectPoint getStart() {
        return start;
    }

    public ConnectPoint getEnd() {
        return end;
    }

    public Link(ConnectPoint start, ConnectPoint end) {
        this.start = start;
        this.end = end;
    }

    public void setEnd(ConnectPoint end) {
        this.end = end;
    }

    public abstract void draw(Graphics2D g);
}
