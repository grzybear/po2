package main.Organisms.Plants;

import main.Organism;
import main.Organisms.Plant;
import main.World;

import java.awt.*;

public class Grass extends Plant {

    public Grass(int x, int y, World world){
        super(0,x,y, world);
    }

    @Override
    public char GetSymbol() {
        return ',';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#cee8c1");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Grass(x,y, world);
    }

    @Override
    public String Name() {
        return "grass";
    }
}
