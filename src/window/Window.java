package src.window;

import src.button.ButtonBar;
import src.drawPanel.DrawPanel;
import src.topbar.TopBar;

import javax.swing.*;
import java.awt.*;

public class Window {
    public Window(){}
    public static void createAndShowGUI() {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("Workflow Design");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        TopBar topbar = new TopBar();
        ButtonBar buttonBar = new ButtonBar();
        DrawPanel drawPanel = new DrawPanel(buttonBar);
        frame.add(topbar, BorderLayout.PAGE_START);
        frame.add(buttonBar, BorderLayout.LINE_START);
        frame.add(drawPanel, BorderLayout.CENTER);
        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }
}
