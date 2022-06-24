package main;

import main.Organisms.Animals.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Frame extends JFrame implements KeyListener {
    private final static int keySpaceBar = 32;
    private final static int keyLeft = 37;
    private final static int keyUp = 38;
    private final static int keyRight = 39;
    private final static int keyDown = 40;
    private final static int keyS = 83;
    private final static int keyL = 76;
    private final Loader loader;
    private Map map;
    private World world;
    private final Log log;
    private TopMenu topMenu;
    private BottomMenu bottomMenu;
    private JPanel mapContainer;
    Frame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(1200, 1000);
        this.setResizable(false);
        this.setFocusable(true);

        Color borderColor = Color.DARK_GRAY;
        Dimension borderSize = new Dimension(100,100);
        JPanel panel;
        panel = new JPanel();
        panel.setBackground(borderColor);
        panel.setPreferredSize(borderSize);
        this.add(panel,BorderLayout.EAST);

        bottomMenu = new BottomMenu(this);
        this.add(bottomMenu,BorderLayout.SOUTH);

        log = new Log();
        this.add(log,BorderLayout.WEST);
        world = new WorldGenerator(log).GetWorld();
        loader = new Loader(world);
        this.addKeyListener(this);
        Reload();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case keyLeft -> {
                world.SetHumanDirection(Direction.LEFT);
                world.CalculateTurn();
            }
            case keyUp -> {
                world.SetHumanDirection(Direction.UP);
                world.CalculateTurn();
            }
            case keyRight -> {
                world.SetHumanDirection(Direction.RIGHT);
                world.CalculateTurn();
            }
            case keyDown -> {
                world.SetHumanDirection(Direction.DOWN);
                world.CalculateTurn();
            }
            case keySpaceBar -> {
                world.SetHumanDirection(Direction.SPECIAL);
                world.CalculateTurn();
            }
            case keyS -> {
                Save();
            }
            case keyL -> {
                Load();
            }
        }
        map.Update();
    }
    private void Reload(){
        if(topMenu != null)this.remove(topMenu);
        if(mapContainer != null)this.remove(mapContainer);
        if(map != null)this.remove(map);

        topMenu = new TopMenu(world);
        this.add(topMenu,BorderLayout.NORTH);

        mapContainer = new JPanel();
        mapContainer.setBackground(Color.DARK_GRAY);
        mapContainer.setLayout(null);
        this.add(mapContainer,BorderLayout.CENTER);



        this.setVisible(true);
        map = new Map(world, topMenu, 784, 761);
        map.Update();
        mapContainer.add(map);
        this.requestFocus();
    }
    public void Load(){
        try {
            world = loader.Load(this.log);
            Reload();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.requestFocus();
    }
    public void Save(){
        try {
            loader.Save();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.requestFocus();
    }
    public void Next(){
        world.CalculateTurn();
        map.Update();
        this.requestFocus();
    }
}
