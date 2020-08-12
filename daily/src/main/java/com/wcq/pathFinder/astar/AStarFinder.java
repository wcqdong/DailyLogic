package com.wcq.pathFinder.astar;

import com.wcq.pathFinder.PathFinder;
import com.wcq.utils.Vector2D;
import com.wcq.utils.Vector3D;

import java.util.HashMap;
import java.util.List;

/**
 * A*寻路
 */
public class AStarFinder implements PathFinder {
    /** 地图数据 */
    private AStarMap map = new AStarMap(new HashMap<>(), 100, 100, 1);


    @Override
    public Vector3D raycast(Vector3D start, Vector3D end) {
        AStarCell startCell = map.getCell(start);
        if(startCell == null || startCell.isObstacle()){
            return start;
        }

        AStarCell endCell = map.getCell(end);
        if(endCell == null){
            return start;
        }

        AStarCell hitCell = startCell;
        Vector3D hitPos = start;
        Vector2D dir = end.sub(start).toVector2D();
        while(true){
            AStarCell nextCell = raycastCell(hitCell,hitPos,  dir);
            if(nextCell == null){
                break;
            }
        }


        return map.getPos(hitCell);
    }

    @Override
    public Vector3D getHeight(Vector3D pos) {
        AStarCell cell = map.getCell(pos);
        return cell == null ? pos : new Vector3D(pos.x, pos.y, cell.z);
    }

    @Override
    public List<Vector3D> findPath(Vector3D start, Vector3D end) {
        return null;
    }


    private AStarCell raycastCell(AStarCell hitCell, Vector3D hitPos, Vector2D dir) {
        AStarMap map = this.map;
        double cellSize = map.cellSize;
        double dx;
        double dy;


        AStarCell nextCell;
        if(dir.x == 0){
            if(dir.y > 0){
                nextCell = map.getCell(hitCell.x, hitCell.y + 1);
            }else{
                nextCell = map.getCell(hitCell.x, hitCell.y - 1);
            }
        }else if(dir.y == 0){
            if(dir.x > 0){
                nextCell = map.getCell(hitCell.x + 1, hitCell.y);
            }else{
                nextCell = map.getCell(hitCell.x - 1, hitCell.y);
            }
        }else{
            int x;
            int y;
            if(dir.x > 0){
                x = hitCell.x + 1;
            }else{
                x = hitCell.x;
            }
            if(dir.y > 0){
                y = hitCell.y + 1;
            }else{
                y = hitCell.y;
            }
            nextCell = map.getCell(x, y);
        }


        return null;

    }

}
