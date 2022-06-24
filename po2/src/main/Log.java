package main;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Log extends JPanel {
    private final static int max = 35;
    private List<String> events;
    Log(){
        events = new LinkedList<>();
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(300,100));
        this.setLayout(new GridLayout(max,1,0,0));
    }

    public void AddEvent(String event){
        events.add(event);
        if(events.size()>max)events.remove(0);
        Reload();
    }

    private void Reload(){
        this.setVisible(false);
        this.removeAll();
        for (var event: events) {
            this.add(new LogText(event));
        }
        this.setVisible(true);
    }


}
