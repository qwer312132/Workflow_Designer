package src.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import src.button.ButtonConstant.ButtonState;

public class ButtonBar extends JPanel {
    private ButtonState buttonState = ButtonState.RECT;

    private final List<ButtonStateListener> listeners = new ArrayList<>();
    private final JButton[] buttons; // 宣告為成員變數
    private final Color defaultColor = new JButton().getBackground(); // 系統預設顏色
    private final Color selectedColor = defaultColor.darker(); // 選中顏色

    public ButtonBar() {
        this.setLayout(new GridLayout(6, 1, 0, 5));
        String[] names = {"select", "association", "generalization", "composition", "rect", "oval"};
        buttons = new JButton[names.length]; // 初始化

        for (int i = 0; i < names.length; i++) {
            ImageIcon img = new ImageIcon("src/img/" + names[i] + ".png");
            buttons[i] = new JButton(img);

            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    setButtonState(ButtonState.values()[finalI]);
                }
            });
            this.add(buttons[i]);
        }

        // 初始狀態選中 RECT
        updateButtonColors();
    }

    public void setButtonState(ButtonState newState) {
        this.buttonState = newState;
        updateButtonColors(); // 更新按鈕顏色
        notifyListeners();    // 通知監聽器
    }

    public ButtonState getButtonState() {
        return buttonState;
    }

    public void addButtonStateListener(ButtonStateListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ButtonStateListener listener : listeners) {
            listener.onButtonStateChanged(buttonState);
        }
    }

    private void updateButtonColors() {
        for (int i = 0; i < buttons.length; i++) {
            if (ButtonState.values()[i] == buttonState) {
                buttons[i].setBackground(selectedColor);
            } else {
                buttons[i].setBackground(defaultColor);
            }
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
        }
    }
}
