package com.wcq.pathFinder.astar;

import com.wcq.utils.Vector3D;

import java.util.Map;

public class AStarMap {
    /** 行走面数据,value可能为空，代表不可行走 */
    public final Map<Integer, Double> heightMap;

    public int width;
    public int height;
    /** 格子宽度 */
    public float cellSize;

    private AStarCell[] cells;

    public AStarMap(Map<Integer, Double> heightMap, int width, int height, float cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;

        // TODO 为了有数据测试
        for(int i=0; i<width; ++i){
            for(int j=0; j<height; ++j){
                heightMap.put(index(i, j), 0D);
            }
        }

        this.heightMap = heightMap;

        this.cells = new AStarCell[width * height];

    }

    public AStarCell getCell(Vector3D pos){
        int x = (int)(pos.x / cellSize);
        int y = (int)(pos.y / cellSize);

        return getCell(x, y);
    }

    public AStarCell getCell(int x, int y){
        int index = index(x, y);
        AStarCell cell = cells[index(x, y)];
        if(cell == null){
            if(!isInBorder(x, y)){
                return null;
            }
            double height = loadZ(index);
            cell = new AStarCell(x, y, height);
            cells[index] = cell;
        }
        return cell;
    }

    private double loadZ(int index) {
        return heightMap.get(index);
    }

    private int index(int x, int y){
        return x + y*width;
    }

    /** 是否在范围内 */
    private boolean isInBorder(int x, int y){
        return x >=0 && x <width && y>=0 && x<height;
    }

    public Vector3D getPos(AStarCell cell) {
        return new Vector3D(cell.x, cell.y, cell.z);
    }
}
