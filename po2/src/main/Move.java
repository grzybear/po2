package main;

public class Move {
    Direction direction;
    int x, y;
    public Move(Direction d){
        x = 0;
        y = 0;
        UpdateXY(d);
    }
    public Move(int d){
        x = 0;
        y = 0;
        UpdateXY(DirectionFromInt(d));
    }
    private Direction DirectionFromInt(int d) {
        if (d == Direction.UP.ordinal()) return Direction.UP;
        if (d == Direction.RIGHT.ordinal()) return Direction.RIGHT;
        if (d == Direction.DOWN.ordinal()) return Direction.DOWN;
        if (d == Direction.LEFT.ordinal()) return Direction.LEFT;
        return Direction.NONE;
    }
    private void UpdateXY(Direction d){
        switch(d){
            case UP -> y = -1;
            case LEFT -> x = -1;
            case RIGHT ->  x = 1;
            case DOWN -> y = 1;
            case NONE -> {}
        }
    }
    public boolean IsNone(){return x==0 && y ==0;}
    public int GetX(){return x;}
    public int GetY(){return y;}
}
