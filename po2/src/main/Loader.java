package main;

import com.sun.jdi.IntegerType;
import main.Organisms.Animals.*;
import main.Organisms.Plants.*;

import java.io.*;

public class Loader {
    World world;
    private final static String path = "save.txt";
    public Loader(World world){
        this.world = world;
    }
    public void Save() throws IOException {
        FileWriter out = new FileWriter(path);
        world.Save(out);
        out.flush();
        out.close();
    }
    public World Load(Log log) throws FileNotFoundException {
        FileReader in = new FileReader(path);
        try {
            int x = 0,y = 0;
            String line[] = new String[3];
            for(int j=0; j<3; j++){
                line[j] = "";
            }
            int a = in.read();
            int i=0;
            while(a != '\n'){
                if(a == ' '){
                    i++;
                }
                else line[i]+=(char)a;
                a = in.read();
            }
            x = Integer.parseInt(line[0]);
            y = Integer.parseInt(line[1]);
            world = new World(x,y,log);
            System.out.println(x + " " + y);
            for(int j=0; j<3; j++){
                line[j] = "";
            }
            i=0;
            a = in.read();
            while(a != -1){
                if((char)a == '\n'){
                    System.out.println(line[0]);
                    x = Integer.parseInt(line[1]);
                    y = Integer.parseInt(line[2]);
                    Spawn(line[0],x,y);
                    System.out.println(x + " " + y);
                    for(int j=0; j<3; j++){
                        line[j] = "";
                    }
                    i=0;
                }
                else if((char)a == ' '){
                    i++;
                }
                else{
                    line[i]+=(char)a;
                }
                a = in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return world;
    }
    private void Spawn(String name, int x, int y){
        switch (name){
            case "sheep" -> world.AddNewOrganism(new Sheep(x,y,world));
            case "wolf" -> world.AddNewOrganism(new Wolf(x,y,world));
            case "fox" -> world.AddNewOrganism(new Fox(x,y,world));
            case "turtle" -> world.AddNewOrganism(new Turtle(x,y,world));
            case "antelope" -> world.AddNewOrganism(new Antelope(x,y,world));
            case "grass" -> world.AddNewOrganism(new Grass(x,y,world));
            case "dandelion" -> world.AddNewOrganism(new Dandelion(x,y,world));
            case "guarana" -> world.AddNewOrganism(new Guarana(x,y,world));
            case "hogweed" -> world.AddNewOrganism(new Hogweed(x,y,world));
            case "nightshade" -> world.AddNewOrganism(new Nightshade(x,y,world));
            case "human" -> world.AddNewOrganism(new Human(x,y,world));
        }
    }
}
