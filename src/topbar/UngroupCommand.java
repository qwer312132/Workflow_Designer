package src.topbar;

import src.canvas.object.BasicObject;
import src.canvas.object.CompositeObject;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import src.canvas.Canvas;

public class UngroupCommand implements MenuCommand {
    private final Canvas canvas;

    public UngroupCommand(Canvas canvas) {
        this.canvas = canvas;
    }


    @Override
    public void execute(Component parentComponent) {
        ArrayList<CompositeObject> selected = canvas.getSelectedObjects();
        if(selected.size()>1)
            return;
        CompositeObject compositeObject = selected.getFirst();
        compositeObject.ungroup();
        canvas.repaint();
    }

}