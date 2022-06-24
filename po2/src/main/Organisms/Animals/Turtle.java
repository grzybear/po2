package main.Organisms.Animals;

import main.Move;
import main.Organism;
import main.Organisms.Animal;
import main.World;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal {

    public Turtle(int x, int y, World world){
        super(2,1,x,y,world);
    }

    @Override
    public char GetSymbol() {
        return 'T';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#b6f0bc");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Turtle(x,y,world);
    }

    @Override
    public String Name() {
        return "turtle";
    }

    @Override
    public void Action(){
        Random rand = new Random();
        int moveChance = rand.nextInt(4);
        if(moveChance == 0) super.Action();
    }

    @Override
    public void Collision(Organism attacker){
        if(attacker.getClass().equals(this.getClass())){
            attacker.Back();
            ((Animal) attacker).NewAnimal();
        }
        else if (HasTargetLowerPower(attacker)){
            world.AddEvent(attacker.Name() + " has been killed by " + this.Name());
            attacker.Die();
        }
        else if (attacker.GetPower()<5){
            world.AddEvent(this.Name() + " protected himself from " + attacker.Name() + "s attack");
            attacker.Back();
        }
        else {
            world.AddEvent(this.Name() + " has been killed by " + attacker.Name());
            Die();
        }
    }
}
