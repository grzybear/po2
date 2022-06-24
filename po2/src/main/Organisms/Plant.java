package main.Organisms;
import main.Direction;
import main.Move;
import main.Organism;
import main.World;

import java.util.Random;

public abstract class Plant extends Organism{
    private static int growthChance = 10; //the higher value the lower chance - 1/chance
    public Plant() {};
    public Plant(int power, int x, int y, World world){
        super(power,1,x,y,world);
    };
    public void Action(){
        Random rand = new Random();
        int expansionChance = rand.nextInt(growthChance);
        if(expansionChance == 1) {
            Move cMove = this.world.Random(this.x, this.y, true);
            int cX = this.x + cMove.GetX();
            int xY = this.y + cMove.GetY();
            world.AddNewOrganism(NewOrganism(cX, xY));
        }
    }
    public void Collision(Organism attacker){
        world.AddEvent(attacker.Name() + " ate " + this.Name());
        Die();
    }
}
