package main.Organisms.Animals;

import main.Direction;
import main.Move;
import main.Organism;
import main.Organisms.Animal;
import main.World;

import java.awt.*;
import java.util.Random;

public class Human extends Animal {
    private final static int maxCoolDown = 10;
    private final static int skillCoolDown = 5;
    private int coolDown;

    public Human(int x, int y, World world){
        super(5,4,x,y,world);
        coolDown = 0;
    }

    @Override
    public char GetSymbol() {
        return 'H';
    }

    @Override
    public Color GetColor() {
        if(coolDown > skillCoolDown)return Color.decode("#402e1d");
        else return Color.decode("#bf7958");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return null;
    }

    @Override
    public String Name() {
        return "human";
    }

    @Override
    public void Action(){
        Direction d = world.GetHumanDirection();
        if(d == Direction.SPECIAL){
            if(coolDown==0) coolDown = maxCoolDown;
        }
        else {
            Move move = new Move(d);
            SetOld();
            x += move.GetX();
            y += move.GetY();
            if(!world.InRange(x,y))Back();
            coolDown--;
            if(coolDown<0)coolDown = 0;
        }
    }

    @Override
    public void Collision(Organism attacker){
        if(coolDown > skillCoolDown && !HasTargetLowerPower(attacker)){
            Move move = world.Random(x,y,true);
            attacker.SetPosition(x+ move.GetX(), y + move.GetY());
        }
        else super.Collision(attacker);
    }
}