package src.drawPanel;

import src.button.ButtonBar;
import src.button.ButtonConstant.ButtonState;
import src.button.ButtonStateListener;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel implements ButtonStateListener {
    private final JLabel test;

    public DrawPanel(ButtonBar buttonBar) {
        setBackground(Color.WHITE);
        test = new JLabel();
        test.setText(buttonBar.getButtonState().toString());
        this.add(test);

        // **註冊監聽器**
        buttonBar.addButtonStateListener(this);
    }

    // **當 ButtonBar 狀態變更時，這個方法會被呼叫**
    @Override
    public void onButtonStateChanged(ButtonState newState) {
        test.setText(newState.toString()); // 更新 JLabel 顯示
        repaint();
    }
}
