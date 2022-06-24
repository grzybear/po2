package main.Organisms.Plants;

import main.Organism;
import main.Organisms.Plant;
import main.World;

import java.awt.*;

public class Nightshade extends Plant {

    public Nightshade(int x, int y, World world){
        super(99,x,y, world);
    }

    @Override
    public char GetSymbol() {
        return ':';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#a7a1d4");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Nightshade(x,y, world);
    }

    @Override
    public String Name() {
        return "nightshade";
    }

    @Override
    public void Collision(Organism attacker){
        attacker.Die();
        super.Collision(attacker);
    }
}