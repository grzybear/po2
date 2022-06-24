package main.Organisms.Animals;

import main.Organism;
import main.Organisms.Animal;
import main.World;

import java.awt.*;

public class Fox extends Animal {

    public Fox(int x, int y, World world){
        super(3,7,x,y,world);
    }

    @Override
    public char GetSymbol() {
        return 'F';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#ffd88a");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Fox(x,y,world);
    }

    @Override
    public String Name() {
        return "fox";
    }

    @Override
    public void Action(){
        super.Action();
        Organism target = world.GetOrganism(x,y);
        if(target == null)return;
        else if(HasTargetHigherPower(target))Back();
    }
}