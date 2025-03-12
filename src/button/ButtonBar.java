package src.button;

import javax.swing.*;
import java.awt.*;

public class ButtonBar extends JPanel{
    public ButtonBar(){
        this.setLayout(new GridLayout(6,1));
        JButton a = new JButton("1");
        JButton b = new JButton("2");
        JButton c = new JButton("3");
        JButton d = new JButton("4");
        JButton e = new JButton("5");
        JButton f = new JButton("5");

        this.add(a);
        this.add(b);
        this.add(c);
        this.add(d);
        this.add(e);
        this.add(f);
    }


}
