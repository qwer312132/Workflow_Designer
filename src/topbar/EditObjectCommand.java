package src.topbar;

import src.canvas.Canvas;
import src.canvas.object.BasicObject;
import src.canvas.object.CompositeObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class EditObjectCommand implements MenuCommand {
    private final Canvas canvas;

    public EditObjectCommand(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute(Component parentComponent) {
        ArrayList<CompositeObject> selected = canvas.getSelectedObjects();
        if (selected.size() != 1) {
            JOptionPane.showMessageDialog(parentComponent, "請選擇一個物件來編輯");
            return;
        }

        // 取得唯一被選到的 basicObject（可根據你的 CompositeObject 結構微調）
        BasicObject target = selected.getFirst().getOwner(); // 假設這個方法回傳 composite root 的某個 basicObject

        // 彈出視窗
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField textField = new JTextField(target.getLabelText()); // 你需要有 getText() / setText()
        JComboBox<String> colorBox = new JComboBox<>(new String[]{"RED", "BLUE", "GREEN"});
        colorBox.setSelectedIndex(0);
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"RECT", "OVAL"});
        typeBox.setSelectedIndex(0);
        JTextField textSizeField = new JTextField(String.valueOf(target.getLabelSize()));
        panel.add(new JLabel("text："));
        panel.add(textField);
        panel.add(new JLabel("color："));
        panel.add(colorBox);
        panel.add(new JLabel("type"));
        panel.add(typeBox);
        panel.add(new JLabel("size"));
        panel.add(textSizeField);

        int result = JOptionPane.showConfirmDialog(parentComponent, panel, "Edit Panel", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            target.setLabelText(textField.getText());

            // 根據選項設定顏色
            Color color = switch (Objects.requireNonNull(colorBox.getSelectedItem()).toString()) {
                case "BLUE" -> Color.BLUE;
                case "GREEN" -> Color.GREEN;
                default -> Color.RED;
            };
            target.setLabelColor(color); // 你需要新增 setTextColor(Color c)

            BasicObject.LabelType labelType = switch(Objects.requireNonNull(typeBox.getSelectedItem()).toString()){
                case "OVAL" -> BasicObject.LabelType.OVAL;
                default -> BasicObject.LabelType.RECT;
            };
            target.setLabelType(labelType);

            target.setLabelSize(Integer.parseInt(textSizeField.getText()));

            canvas.repaint();
        }
    }
}
