package src.canvas.object;

import java.awt.*;
import java.util.ArrayList;

public abstract  class BasicObject {
    private int x1, x2, y1, y2;
    private boolean isSelected;
    private int id;
    private Color labelColor;
    private String labelText;
    private int labelSize;
    public enum LabelType{RECT, OVAL}
    private LabelType labelType;
    private CompositeObject composite;
    public ArrayList<ConnectPoint> connectPointList;
    public abstract void draw(Graphics2D g);
    public int getX1() {return x1;}
    public int getX2() {return x2;}
    public int getY1() {return y1;}
    public int getY2() {return y2;}
    public Color getLabelColor() {return labelColor;}
    public String getLabelText() {return labelText;}
    public int getLabelSize() {return labelSize;}
    public LabelType getLabelType(){return labelType;}

    public void setX1(int x1) {this.x1 = x1;}
    public void setX2(int x2) {this.x2 = x2;}
    public void setY1(int y1) {this.y1 = y1;}
    public void setY2(int y2) {this.y2 = y2;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public void setLabelColor(Color labelColor) {this.labelColor = labelColor;}
    public void setLabelText(String labelText) {this.labelText = labelText;}
    public void setLabelSize(int labelSize) {this.labelSize = labelSize;}
    public void setLabelType(LabelType labelType){this.labelType = labelType;}

    public boolean isClicked(int x, int y){
        return this.x1 <= x && this.x2 >= x && this.y1 <= y && this.y2 >= y;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isBoxed(int x1, int y1, int x2, int y2){
        if(x2 < x1){
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if(y2 < y1){
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }
        return x1 <= getX1() &&  x2 >= getX2() && y1 <= getY1() && y2 >= getY2();
    }

    public BasicObject(int x, int y, int id){
        setX1(x);
        setY1(y);
        setId(id);
        this.composite = new CompositeObject();
        this.composite.setOwner(this);
        isSelected = false;
        this.labelText = "";
        this.labelType = LabelType.RECT;
        this.labelColor = Color.RED;
        this.labelSize = 20;
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

    public void move(int x, int y){
        setX1(getX1()+x);
        setY1(getY1()+y);
        setX2(getX2()+x);
        setY2(getY2()+y);
        for(ConnectPoint connectPoint:connectPointList){
            connectPoint.move(x, y);
        }
    }

    public ConnectPoint inConnectPoint(int x, int y){
        for(ConnectPoint connectPoint:connectPointList){
            if(connectPoint.isIn(x, y)){
                return connectPoint;
            }
        }
        return null;
    }


    public void setComposite(CompositeObject composite) {
        this.composite = composite;
        composite.setOwner(this);
    }

    public CompositeObject getComposite() {
        return composite;
    }
}
