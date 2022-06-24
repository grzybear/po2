package main;

import java.awt.*;

public abstract class Organism {
    protected int power;
    protected int initiative;
    protected World world;
    protected int x,y;
    public Organism(){}
    public Organism(int power, int initiative, int x, int y, World world) {
        this.power = power;
        this.initiative = initiative;
        this.x = x;
        this.y = y;
        this.world = world;
    };
    public abstract char GetSymbol();
    public abstract Color GetColor();
    public abstract void Action();
    public abstract void Collision(Organism attacker);
    public abstract Organism NewOrganism(int x, int y);
    public void Back() {};
    public abstract String Name();

    public void Die(){
        world.DeleteOrganism(this);
    }
    public void UpdateCoordinates(Move move){
        this.x += move.GetX();
        this.y += move.GetY();
    };
    public void SetPosition(int x, int y) {
        this.x = x;
        this.y = y;
    };
    public void IncreasePower(int x) {
        this.power+=x;
    };
    public int GetX(){return x;};
    public int GetY(){return y;};
    public int GetPower() {return power;};
    public boolean HasTargetHigherPower(Organism target) {
        return target.power > this.power;
    };
    public boolean HasTargetLowerPower(Organism target) {
        return target.power < this.power;
    };
    public boolean HasTargetLowerInitiative(Organism target) {
        return target.initiative < this.initiative;
    };
    public boolean IsTargetOnSameField(Organism target) {
        return (target.x == this.x && target.y == this.y);
    };
}
