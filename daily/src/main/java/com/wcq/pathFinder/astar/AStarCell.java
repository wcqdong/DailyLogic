package com.wcq.pathFinder.astar;

public class AStarCell {
    public final int x;
    public final int y;
    public double z;


    public AStarCell(int x, int y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean isObstacle(){
        return z < 0;
    }
}
