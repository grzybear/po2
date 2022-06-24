package main;

import main.Organisms.Animals.*;
import main.Organisms.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMenu extends JPanel implements ActionListener {
    private AllOrganisms chosen;
    private static final int organismsNumber = 10;
    private JButton buttons[];
    private World world;
    private int x,y;
    public TopMenu(World world){
        this.world = world;
        this.setPreferredSize(new Dimension(100,100));
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,25,25));

        buttons = new JButton[organismsNumber];
        for(int i=0; i<organismsNumber; i++){
            buttons[i] = new JButton();
            NameButton(i);
            buttons[i].setPreferredSize(new Dimension(80,50));
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusPainted(false);
            //buttons[i].setContentAreaFilled(false);
            buttons[i].setBackground(Color.GRAY);
            buttons[i].setForeground(Color.DARK_GRAY);
            buttons[i].addActionListener(this);
            buttons[i].setVisible(false);
            this.add(buttons[i]);
        }
    }

    public void ShowButtons(){
        for(int i=0; i<organismsNumber; i++){
            buttons[i].setVisible(true);
        }
    }

    public void HideButtons(){
        for(int i=0; i<organismsNumber; i++){
            buttons[i].setVisible(false);
        }
    }

    public void Spawn(int x, int y){
        this.x = x;
        this.y = y;
        ShowButtons();
        this.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<organismsNumber; i++){
            if(e.getSource() == buttons[i]){
                chosen = AllOrganisms.values()[i];
                Organism newOrganism = null;
                switch (chosen){
                    case Sheep -> {
                        newOrganism = new Sheep(x,y,world);
                    }
                    case Wolf -> {
                        newOrganism = new Wolf(x,y,world);
                    }
                    case Fox -> {
                        newOrganism = new Fox(x,y,world);
                    }
                    case Turtle -> {
                        newOrganism = new Turtle(x,y,world);
                    }
                    case Antelope -> {
                        newOrganism = new Antelope(x,y,world);
                    }
                    case Grass -> {
                        newOrganism = new Grass(x,y,world);
                    }
                    case Dandelion -> {
                        newOrganism = new Dandelion(x,y,world);
                    }
                    case Guarana -> {
                        newOrganism = new Guarana(x,y,world);
                    }
                    case Nightshade -> {
                        newOrganism = new Nightshade(x,y,world);
                    }
                    case Hogweed -> {
                        newOrganism = new Hogweed(x,y,world);
                    }
                }
                if(newOrganism!=null)world.AddNewOrganism(newOrganism);
            }
        }
        HideButtons();
    }

    private void NameButton(int i){
        AllOrganisms organism = AllOrganisms.values()[i];
        String name = "";
        switch (organism){
            case Sheep -> {
                name = "Sheep";
            }
            case Wolf -> {
                name = "Wolf";
            }
            case Fox -> {
                name = "Fox";
            }
            case Turtle -> {
                name = "Turtle";
            }
            case Antelope -> {
                name = "Antelope";
            }
            case Grass -> {
                name = "Grass";
            }
            case Dandelion -> {
                name = "Dandelion";
            }
            case Guarana -> {
                name = "Guarana";
            }
            case Nightshade -> {
                name = "Nightshade";
            }
            case Hogweed -> {
                name = "Hogweed";
            }
        }
        buttons[i].setText(name);
        buttons[i].setFont(new Font("Arial",Font.ITALIC,10));
    }
}
