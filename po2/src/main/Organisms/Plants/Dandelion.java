package main.Organisms.Plants;

import main.Organism;
import main.Organisms.Plant;
import main.World;

import java.awt.*;

public class Dandelion extends Plant {
    private static int growthTimes = 3;
    public Dandelion(int x, int y, World world){
        super(0,x,y, world);
    }

    @Override
    public char GetSymbol() {
        return '*';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#dff5f5");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Dandelion(x,y, world);
    }

    @Override
    public String Name() {
        return "dandelion";
    }

    @Override
    public void Action(){
        for(int i=0; i<growthTimes; i++){
            super.Action();
        }
    }
}
