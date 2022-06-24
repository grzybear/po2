package main.Organisms.Animals;

import main.Organism;
import main.Organisms.Animal;
import main.World;

import java.awt.*;

public class Wolf extends Animal {

    public Wolf(int x, int y, World world){
        super(9,5,x,y,world);
    }

    @Override
    public char GetSymbol() {
        return 'W';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#a19193");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Wolf(x,y,world);
    }

    @Override
    public String Name() {
        return "wolf";
    }

}
