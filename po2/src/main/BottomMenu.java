package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomMenu extends JPanel implements ActionListener {
    private Frame frame;
    private JButton buttons[];
    public BottomMenu(Frame frame){
        this.frame = frame;
        this.setPreferredSize(new Dimension(100,100));
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,25,25));
        buttons = new JButton[3];
        for(int i=0; i<3; i++){
            buttons[i] = new JButton();
            NameButton(i);
            buttons[i].setPreferredSize(new Dimension(80,50));
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusPainted(false);
            //buttons[i].setContentAreaFilled(false);
            buttons[i].setBackground(Color.GRAY);
            buttons[i].setForeground(Color.DARK_GRAY);
            buttons[i].addActionListener(this);
            buttons[i].setVisible(true);
            this.add(buttons[i]);
        }
    }

    private void NameButton(int i){
        String name = "";
        switch (i) {
            case 0 -> name = "save";
            case 1 -> name = "load";
            case 2 -> name = "next";
        }
        buttons[i].setText(name);
        buttons[i].setFont(new Font("Arial",Font.ITALIC,10));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttons[0]){
            frame.Save();
        }
        else if(e.getSource() == buttons[1]){
            frame.Load();
        }
        else if(e.getSource() == buttons[2]){
            frame.Next();
        }
    }
}
