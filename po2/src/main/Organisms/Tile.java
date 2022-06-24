package main.Organisms;

import main.TopMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JPanel implements MouseListener {
    private static Color normal = Color.GRAY;
    private static Color hover = Color.DARK_GRAY;
    private int size, x, y;
    private boolean toggle;
    private TopMenu topMenu;
    public Tile(TopMenu topMenu,int x, int y, int size){
        this.x = x;
        this.y = y;
        this.topMenu = topMenu;
        toggle = false;
        this.size = size;
        setBounds(x*size,y*size,size,size);
        setBackground(normal);
        this.addMouseListener(this);
    }

    /*public void paint(Graphics g){
        if(toggle)g.setColor(hover);
        else g.setColor(normal);
        g.fillRect(2,2,size-2,size-2);
    }*/

    @Override
    public void mouseClicked(MouseEvent e) {
        topMenu.Spawn(x,y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(hover);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(normal);
    }
}
