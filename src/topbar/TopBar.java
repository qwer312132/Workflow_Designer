package src.topbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class TopBar extends JPanel {
    private final Map<String, MenuCommand> commands = new HashMap<>();

    public TopBar() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.GRAY);

        JLabel fileLabel = new JLabel("File");
        JLabel editLabel = new JLabel("Edit");
        this.add(fileLabel);
        this.add(editLabel);

        // Edit 選單的 popup menu
        JPopupMenu popupMenu = new JPopupMenu();

        // 動態加選單項目
        addMenuItem(popupMenu, "顯示對話框", "showDialog");
        addMenuItem(popupMenu, "group", "groupSelected");
        addMenuItem(popupMenu, "ungroup", "ungroupSelected");

        // 點擊 editLabel 顯示 popup
        editLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                popupMenu.show(editLabel, e.getX(), e.getY());
            }
        });
    }

    private void addMenuItem(JPopupMenu menu, String label, String commandKey) {
        JMenuItem item = new JMenuItem(label);
        item.addActionListener(e -> {
            MenuCommand command = commands.get(commandKey);
            if (command != null) {
                command.execute(this); // 這裡的 this 是 parentComponent
            }
        });
        menu.add(item);
    }

    public void registerCommand(String key, MenuCommand command) {
        commands.put(key, command);
    }
}
