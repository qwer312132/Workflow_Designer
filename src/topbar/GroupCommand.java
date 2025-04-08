package src.topbar;

import src.canvas.Canvas;
import src.canvas.object.BasicObject;
import src.canvas.object.CompositeObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GroupCommand implements MenuCommand {
    private final Canvas canvas;

    public GroupCommand(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute(Component parentComponent) {
        ArrayList<CompositeObject> selected = canvas.getSelectedObjects();
        if (selected.size() <= 1) {
            return;
        }

        CompositeObject groupRoot = new CompositeObject(); // 新的 composite root

        for (CompositeObject compositeObject : selected) {
            compositeObject.setParent(groupRoot);
            groupRoot.addChild(compositeObject);
        }

        canvas.repaint(); // 更新畫面
    }
}

