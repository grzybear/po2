package main.Organisms.Plants;

import main.Organism;
import main.Organisms.Plant;
import main.World;

import java.awt.*;

public class Guarana extends Plant {
    private static final int strength = 3;
    public Guarana(int x, int y, World world){
        super(0,x,y, world);
    }

    @Override
    public char GetSymbol() {
        return '.';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#e8c3c6");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Guarana(x,y, world);
    }

    @Override
    public String Name() {
        return "guarana";
    }

    @Override
    public void Collision(Organism attacker){
        attacker.IncreasePower(strength);
        super.Collision(attacker);
    }
}
