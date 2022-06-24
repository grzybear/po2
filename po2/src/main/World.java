package main;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import main.Organisms.Animal;
import java.util.Random;


public class World {
    List<Organism> organisms;
    List<Organism> newOrganisms;
    Organism[][] map;
    int x,y;
    private Direction humanDirection;
    private Log log;
    public World(int x, int y, Log log){
        this.log = log;
        organisms = new LinkedList<>();
        newOrganisms = new LinkedList<>();
        this.x = x;
        this.y = y;
        map = new Organism[y][x];
        humanDirection = Direction.NONE;
    }
    public void CalculateTurn(){
            for (var organism : organisms) {
                if (organism != null) {
                    if (organism instanceof Animal) {
                        map[organism.GetY()][organism.GetX()] = null;
                        organism.Action();
                        var target = map[organism.GetY()][organism.GetX()];
                        if (target != null && target != organism) target.Collision(organism);
                    } else organism.Action();
                    UpdateMap();
                }
            }
            UpdateOrganisms();
        //DrawMap();
        humanDirection = Direction.NONE;
    }
    public void CalculateTurn(Map mapG){
        for (var organism : organisms) {
            if (organism != null) {
                if (organism instanceof Animal) {
                    map[organism.GetY()][organism.GetX()] = null;
                    organism.Action();
                    var target = map[organism.GetY()][organism.GetX()];
                    if (target != null) target.Collision(organism);
                } else organism.Action();
                UpdateMap();
                mapG.Update();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        UpdateOrganisms();
        //DrawMap();
    }
    private void DrawMap(){
        String mapG = "";
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++) {
                if (map[i][j] == null) mapG += '_';
                else mapG += map[i][j].GetSymbol();
            }
            mapG+='\n';
        }
        System.out.flush();
        System.out.print(mapG);
        System.out.println(organisms.size());
    }
    public void AddNewOrganism(Organism organism){
        if(map[organism.GetY()][organism.GetX()] != null)return;
        newOrganisms.add(organism);
        map[organism.GetY()][organism.GetX()] = organism;
    }
    public void DeleteOrganism(Organism organism){
        boolean endFlag = true;
        for (int i = 0; i < y && endFlag; i++) {
            for (int j = 0; j < x; j++) {
                if (map[i][j] == organism) {
                    endFlag = true;
                    map[i][j] = null;
                    break;
                }
            }
        }
        int i = organisms.indexOf(organism);
        if(i>=0){
            organisms.set(i,null);
        }
        else{
            i = newOrganisms.indexOf(newOrganisms);
            if(i>=0)newOrganisms.set(i,null);
        }
    }
    public void UpdateMap(){
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                map[i][j] = null;
            }
        }
        for (var organism : organisms) {
            if(organism != null)
            if (map[organism.GetY()][organism.GetX()] == null)
                map[organism.GetY()][organism.GetX()] = organism;
            //else organisms.remove(organism);
        }
        for (var organism : newOrganisms) {
            if(organism != null)
            if (map[organism.GetY()][organism.GetX()] == null)
                map[organism.GetY()][organism.GetX()] = organism;
            //else newOrganisms.remove(organism);
        }
    }
    public void UpdateOrganisms(){
        while (newOrganisms.size()>0){
            AddOrganismToMainList(newOrganisms.get(0));
            newOrganisms.remove(0);
        }
    }
    public Move Random(int x, int y, boolean empty){
        int possibleDirections = 4;
        boolean[] directions = {true,true,true,true};
        if(y<=0 || (empty && map[y-1][x]!=null)){
            possibleDirections--;
            directions[Direction.UP.ordinal()] = false;
        }
        if(x>=this.x - 1 || (empty && map[y][x+1]!=null)){
            possibleDirections--;
            directions[Direction.RIGHT.ordinal()] = false;
        }
        if(y>=this.y-1 || (empty && map[y+1][x]!=null)){
            possibleDirections--;
            directions[Direction.DOWN.ordinal()] = false;
        }
        if(x<=0 || (empty && map[y][x-1]!=null)){
            possibleDirections--;
            directions[Direction.LEFT.ordinal()] = false;
        }
        if(possibleDirections==0)return new Move(Direction.NONE);
        Random rand = new Random();
        int position = rand.nextInt(possibleDirections);
        for(int i=0; i<=position; i++){
            if(i>=Direction.NONE.ordinal()){
                return new Move(Direction.NONE);
            }
            if(!directions[i])position++;
        }
        return new Move(position);
    }
    private boolean AddOrganismToMainList(Organism newOrganism){
        boolean endFlag = true;
        while (endFlag){
            endFlag = organisms.remove(null);
        }
        endFlag = true;
        while (endFlag){
            endFlag = newOrganisms.remove(null);
        }
        for (var organism : organisms) {
            if(newOrganism.HasTargetLowerInitiative(organism)){
                int i = organisms.indexOf(organism);
                organisms.add(i,newOrganism);
                return true;
            }
        }
        organisms.add(newOrganism);
        return false;
    }
    public Color GetColor(int x, int y){
        if(map[y][x]==null)return Color.GRAY;
        else return map[y][x].GetColor();
    }
    public Organism GetOrganism(int x, int y){
        if(x<=0 || y <= 0 || x >= this.x || y >=this.y)return null;
        return map[y][x];
    }
    public void SetHumanDirection(Direction d){
        humanDirection = d;
    }
    public Direction GetHumanDirection(){
        return humanDirection;
    }
    public boolean InRange(int x, int y){
        return x>=0 && x<this.x && y>=0 && y<this.y;
    }
    public void AddEvent(String event){
        log.AddEvent(event);
    }
    public boolean Empty(int x, int y){
        return map[y][x] == null;
    }
    public void Save(FileWriter out) throws IOException {
        String line;
        line = x + " " + y + "\n";
        out.write(line);
        for(var organism : organisms){
            line = organism.Name() + " " + organism.x + " " + organism.y + "\n";
            out.write(line);
            AddEvent("Saving " + organism.Name() + " completed");
        }
        for(var organism : newOrganisms){
            line = organism.Name() + " " + organism.x + " " + organism.y + "\n";
            out.write(line);
            AddEvent("Saving " + organism.Name() + " completed");
        }
    }
}
