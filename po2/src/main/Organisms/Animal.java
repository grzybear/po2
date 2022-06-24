package main.Organisms;
import main.Move;
import main.Organism;
import main.World;

public abstract class Animal extends Organism{
    private int oldX, oldY;
    public Animal(int power, int initiative, int x, int y, World world){
        super(power,initiative,x,y,world);
        SetOld();
    }
    protected void SetOld(){
        oldX=x;
        oldY=y;
    }
    public void Back(){
        x = oldX;
        y = oldY;
    }
    @Override
    public void Action(){
        SetOld();
        Move move = this.world.Random(this.x, this.y,false);
        x += move.GetX();
        y += move.GetY();
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
            world.AddEvent(this.Name() + " has been killed by " + attacker.Name());
            Die();
        }
    }
    public void NewAnimal(){
        Move cMove = world.Random(x, y, true);
        if(!cMove.IsNone()) {
            int cX = x + cMove.GetX();
            int cY = y + cMove.GetY();
            world.AddNewOrganism(this.NewOrganism(cX, cY));
        }
    }
}
