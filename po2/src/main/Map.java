package main;
import main.Organisms.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Map extends JPanel {
    private static int borderThickness = 2;
    private static Color backgroundColor = Color.GRAY;
    private JPanel tiles[][];
    private int x,y;
    private int width,height;
    private static int size = 35;
    private World world;
    private TopMenu topMenu;
    Map(World world, TopMenu topMenu, int parentSizeX, int parentSizeY){
        this.topMenu = topMenu;
        this.world = world;
        width = world.x*size + 1;
        height = world.y*size + 1;
        x = (parentSizeX - width)/2;
        y = (parentSizeY - height)/2;
        this.setBackground(backgroundColor);
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        tiles = new JPanel[y][x];
    }
    public void Update(){
        this.repaint();

    }
    public void paint(Graphics g){

        super.paint(g);
        int rows = height / size;
        int columns = width / size;
        Graphics2D graphics2D = (Graphics2D) g;
        this.setVisible(false);
        for(int i=0; i<rows; i++){
            Color tileColor;
            for(int j=0; j<columns; j++){
                tileColor = world.GetColor(j,i);
                if(tileColor == Color.GRAY){
                    if(tiles[i][j]==null){
                        tiles[i][j]=new Tile(topMenu,j, i, size);
                        this.add(tiles[i][j]);
                    }
                }
                else{
                    if(tiles[i][j]!=null){
                        remove(tiles[i][j]);
                        tiles[i][j] = null;
                    }
                    graphics2D.setColor(tileColor);
                    graphics2D.fillRect(size*j + borderThickness,size*i + borderThickness,size - borderThickness,size - borderThickness);
                }
            }
        }
        this.setVisible(true);
    }
    public int GetX(){
        return x;
    }
    public int GetY(){
        return y;
    }
    public int GetWidth(){
        return width;
    }
    public int GetHeight(){
        return height;
    }
}
