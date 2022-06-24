package main.Organisms.Plants;

import main.Organism;
import main.Organisms.Animal;
import main.Organisms.Plant;
import main.World;

import java.awt.*;

public class Hogweed extends Plant {

    public Hogweed(int x, int y, World world){
        super(10,x,y, world);
    }

    @Override
    public char GetSymbol() {
        return '#';
    }

    @Override
    public Color GetColor() {
        return Color.decode("#9dad7b");
    }

    @Override
    public Organism NewOrganism(int x, int y) {
        return new Hogweed(x,y, world);
    }

    @Override
    public String Name() {
        return "hogweed";
    }

    @Override
    public void Action(){
        Kill(world.GetOrganism(x+1, y));
        Kill(world.GetOrganism(x, y+1));
        Kill(world.GetOrganism(x-1, y));
        Kill(world.GetOrganism(x, y-1));
        super.Action();
    }
    private void Kill(Organism intruder){
        if(intruder != null){
            if(intruder instanceof Animal){
                world.AddEvent(intruder.Name() + " has been killed by " + this.Name());
                intruder.Die();
            }
        }
    }

    @Override
    public void Collision(Organism attacker){
        attacker.Die();
        super.Collision(attacker);
    }
}
