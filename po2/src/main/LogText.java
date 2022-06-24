package main;

import javax.swing.*;
import java.awt.*;

public class LogText extends JPanel {
    public LogText(String event){
        super();
        this.setLayout(null);
        //this.setBackground(Color.DARK_GRAY);
        JLabel text = new JLabel("- " + event);
        text.setBounds(10,0,300,20);
        text.setForeground(Color.white);
        this.setOpaque(false);
        this.add(text);
    }
}
