package main;

import main.Organisms.Animals.*;
import main.Organisms.Plants.*;

import java.util.Random;

public class WorldGenerator {
    private final static int maxX = 20;
    private final static int maxY = 20;
    private final static int minX = 10;
    private final static int minY = 10;
    private final static int organisms = 15;
    World world;
    public WorldGenerator(Log log){
        Random rand = new Random();
        int thisx = rand.nextInt(maxX-minX)+minX;
        int thisy = rand.nextInt(maxY-minY)+minY;
        world = new World(thisx, thisy, log);
        AllOrganisms organism;
        Organism newOrganism = null;
        int x,y;
        for(int i=0; i<organisms; i++){
            do{
                x = rand.nextInt(thisx);
                y = rand.nextInt(thisy);
            }while(!world.Empty(x,y));
            organism = AllOrganisms.values()[rand.nextInt(10)];
            world.AddNewOrganism(new Human(0,0, world));
            switch (organism){
                case Sheep -> {
                    newOrganism = new Sheep(x,y,world);
                }
                case Wolf -> {
                    newOrganism = new Wolf(x,y,world);
                }
                case Fox -> {
                    newOrganism = new Fox(x,y,world);
                }
                case Turtle -> {
                    newOrganism = new Turtle(x,y,world);
                }
                case Antelope -> {
                    newOrganism = new Antelope(x,y,world);
                }
                case Grass -> {
                    newOrganism = new Grass(x,y,world);
                }
                case Dandelion -> {
                    newOrganism = new Dandelion(x,y,world);
                }
                case Guarana -> {
                    newOrganism = new Guarana(x,y,world);
                }
                case Nightshade -> {
                    newOrganism = new Nightshade(x,y,world);
                }
                case Hogweed -> {
                    newOrganism = new Hogweed(x,y,world);
                }
            }
            world.AddNewOrganism(newOrganism);
        }
    }
    public World GetWorld(){
        return world;
    }
}
