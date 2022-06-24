package main.Organisms.Animals;

import main.Organism;
import main.Organisms.Animal;
import main.World;

import java.awt.*;

public class Sheep extends Animal {

    public Sheep(int x, int y, World world){
        super(4,4,x,y,world);
    }

    @Override
    public char GetSymbol() {
        return 'S';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#e3e3de");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Sheep(x,y,world);
    }

    @Override
    public String Name() {
        return "sheep";
    }

}
