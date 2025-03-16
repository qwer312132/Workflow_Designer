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

    private final List<ButtonStateListener> listeners = new ArrayList<>(); // 監聽器列表

    public ButtonBar() {
        this.setLayout(new GridLayout(6,1,0,5));
        String[] names = {"select", "association", "generalization", "composition", "rect", "oval"};
        for (int i = 0; i < names.length; i++) {
            final int index = i;
            JButton[] buttons = new JButton[names.length];
            buttons[i] = new JButton(names[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    setButtonState(ButtonState.values()[index]);
                }
            });
            this.add(buttons[i]);
        }
    }

    // **狀態變更方法**
    public void setButtonState(ButtonState newState) {
        this.buttonState = newState;
        notifyListeners(); // 通知監聽器
    }

    public ButtonState getButtonState() {
        return buttonState;
    }

    // **監聽機制**
    public void addButtonStateListener(ButtonStateListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ButtonStateListener listener : listeners) {
            listener.onButtonStateChanged(buttonState);
        }
    }
}
