package main;

import java.awt.*;

public class Grafika extends  Canvas{
    public void paint(Graphics g) {
        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("wolf.png");
        g.drawImage(i, 3,3,this);
        setBackground(Color.BLACK);
    }
}
