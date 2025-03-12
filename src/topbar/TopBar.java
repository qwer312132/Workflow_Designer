package src.topbar;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    public TopBar(){
        this.setLayout(new FlowLayout());
        this.setBackground(Color.GRAY);
        JLabel fileLabel = new JLabel("File");
        JLabel editLabel = new JLabel("Edit");
        this.add(fileLabel);
        this.add(editLabel);
    }

}
