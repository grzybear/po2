package main.Organisms.Animals;

import main.Move;
import main.Organism;
import main.Organisms.Animal;
import main.World;

import java.awt.*;
import java.util.Random;

public class Antelope extends Animal {

    public Antelope(int x, int y, World world){
        super(3,7,x,y,world);
    }

    @Override
    public char GetSymbol() {
        return 'A';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#c7b89f");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Antelope(x,y,world);
    }

    @Override
    public String Name() {
        return "antelope";
    }

    @Override
    public void Action(){
        super.Action();
        super.Action();
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
        else {
            Random rand = new Random();
            int dodgeChance = rand.nextInt(2);
            if(dodgeChance == 0) {
                world.AddEvent(this.Name() + " has been killed by " + attacker.Name());
                Die();
            }
            else{
                world.AddEvent(this.Name() + " escaped from " + attacker.Name());
                attacker.Back();
            }
        }
    }
}