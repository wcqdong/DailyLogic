package com.wcq.cell;

import java.util.ArrayList;
import java.util.List;

public class cellTest {

    /** 广播格子数 宽*/
    public static int cellWidth = 36;
    /** 广播格子数 高*/
    public static int cellHeight = 36;

    private static StageCell[] index2Cells;

    private static int count = 10000000;

    public static void main(String[] args) {

        index2Cells = new StageCell[cellHeight * cellWidth];
        for(int y = 0; y< cellHeight; y++){
            for(int x = 0; x< cellWidth; x++){
                int index = getCellIndex(x, y);
                StageCell cell = new StageCell(index);
                index2Cells[index] = cell;
            }
        }


        for(StageCell cell : index2Cells) {
			List<StageCell> cells9 = getCells9(cell);
			// 获得25宫格
			List<StageCell> cells25 = new ArrayList<>();

			int maxX = cell.x + 2;
			int maxY = cell.y + 2;
			int minX = cell.x - 2;
			int minY = cell.y - 2;
			for (int y = minY; y<=maxY; ++y) {
                if(y < 0 || y >= cellHeight){
                    continue;
                }
				for (int x = minX; x<=maxX; ++x) {
				    if(x < 0 || x >= cellWidth){
				        continue;
                    }
					StageCell temp = getCell(x, y);
					if (temp == null) {
						continue;
					}
					cells25.add(temp);
				}
			}

			cell.setCells9(cells9);

			cells25.removeAll(cells9);
			cell.setCells16(cells25);
		}


//        StageCell cellBegin = index2Cells[288];
//        StageCell cellEnd = index2Cells[289];
        StageCell cellBegin = getCell(3, 2);
        StageCell cellEnd = getCell(2, 2);
        System.out.println(cellBegin);
        System.out.println(cellEnd);
        System.out.println();
        System.out.println();

//        StageCell[] list = getChangedCells2(cellBegin, cellEnd, 2);
//        for (StageCell cell : list){
//            System.out.println(cell);
//        }

        StageCell[] list = getInsectionCells(cellBegin, cellEnd, 3);
        for (StageCell cell : list){
            System.out.println(cell);
        }

//        for(int i=0; i<count; i++) {
//            getChangedCells1(cellBegin, cellEnd, 3);
//            getChangedCells1(cellBegin, cellEnd, 2);
//            getChangedCells1(cellBegin, cellEnd, 1);
//        }
//        for(int i=0; i<count; i++){
//            getChangedCells2(cellBegin, cellEnd, 3);
//            getChangedCells2(cellBegin, cellEnd, 2);
//            getChangedCells2(cellBegin, cellEnd, 1);
//
////            for (StageCell cell : list){
////                System.out.println(cell);
////            }
//        }
//
//
//        long time = System.currentTimeMillis();
//
//        for(int i=0; i<count; i++){
//            getChangedCells1(cellBegin, cellEnd, 3);
//            getChangedCells1(cellBegin, cellEnd, 2);
//            getChangedCells1(cellBegin, cellEnd, 1);
//
////            StageCell[] list = getChangedCells(cellBegin, cellEnd, 2);
////            for (StageCell cell : list){
////                System.out.println(cell);
////            }
//        }
//        System.out.println("time:" + (System.currentTimeMillis() - time));
//        time = System.currentTimeMillis();
//
//
//
//        for(int i=0; i<count; i++){
//            getChangedCells2(cellBegin, cellEnd, 3);
//            getChangedCells2(cellBegin, cellEnd, 2);
//            getChangedCells2(cellBegin, cellEnd, 1);
//
////            for (StageCell cell : list){
////                System.out.println(cell);
////            }
//        }
//        System.out.println("time:" + (System.currentTimeMillis() - time));
//        time = System.currentTimeMillis();
    }

    private static StageCell[] empty = new StageCell[0];

    private static StageCell[] getChangedCells1(StageCell cellBegin, StageCell cellEnd, int offsetIndex) {
        int l_minX = Math.max(0, cellBegin.x - offsetIndex);
        int l_maxX = Math.min(cellWidth - 1, cellBegin.x + offsetIndex);
        int d_minY = Math.max(0, cellBegin.y - offsetIndex);
        int d_maxY = Math.min(cellHeight - 1, cellBegin.y + offsetIndex);

        int r_minX = Math.max(0, cellEnd.x - offsetIndex);
        int r_maxX = Math.min(cellWidth - 1, cellEnd.x + offsetIndex);
        int u_minY = Math.max(0, cellEnd.y - offsetIndex);
        int u_maxY = Math.min(cellHeight - 1, cellEnd.y + offsetIndex);

//        Point startPoint = new Point()

//        int diffX = Math.abs(minX - _minX);
//        diffX = Math.max(maxX - minX, diffX) + 1;
//
//        int diffY = Math.abs(minY - _minY);
//        diffY = Math.max(maxY - minY, diffY) + 1;

        int dirX = cellEnd.x - cellBegin.x;
        int dirY = cellEnd.y - cellBegin.y;

        List<StageCell> cells = new ArrayList<>();

        int x1;
        int x2;
        if(dirX > 0){
            x1 = l_minX;
            x2 = r_minX;
        }else{
            x1 = r_maxX + 1;
            x2 = l_maxX + 1;
        }


        for(int x=x1; x<x2; ++x){
            for(int y=d_minY; y<=d_maxY; ++y){
                cells.add(getCell(x, y));
            }
        }

        int x3;
        int x4;
        if(dirX > 0){
            x3 = r_minX;
            x4 = l_maxX;
        }else{
            x3 = l_minX;
            x4 = r_maxX;
        }

        int y3;
        int y4;
        if(dirY > 0){
            y3 = d_minY;
            y4 = u_minY;
        }else{
            y3 = u_maxY + 1;
            y4 = d_maxY + 1;
        }

        for(int x=x3; x<=x4; ++x){
            for(int y=y3; y<y4; ++y){
                cells.add(getCell(x, y));
            }
        }


//        List<StageCell> cells = new ArrayList<>();

//        StageCell[] cells = new StageCell[];

//        for(int y=u_minY; y<=u_maxY; ++y){
//            for(int x=l_minX; x<=l_maxX; ++x){
//                if(x >= r_minX && x <= r_maxX && y >= d_minY && y <= d_maxY){
//                    continue;
//                }
////                cells.add(getCell(x, y));
//            }
//        }
        return cells.toArray(empty);

//        return null;

    }

    private static StageCell[] getChangedCells2(StageCell cellBegin, StageCell cellEnd, int offsetIndex) {

        if(Math.abs(cellBegin.x - cellEnd.x) > (offsetIndex << 1) || Math.abs(cellBegin.y - cellEnd.y) > (offsetIndex << 1)){
            return getCells(cellBegin, offsetIndex);
        }

        int l_minX = Math.max(0, cellBegin.x - offsetIndex);
        int l_maxX = Math.min(cellWidth - 1, cellBegin.x + offsetIndex);
        int d_minY = Math.max(0, cellBegin.y - offsetIndex);
        int d_maxY = Math.min(cellHeight - 1, cellBegin.y + offsetIndex);

        int r_minX = Math.max(0, cellEnd.x - offsetIndex);
        int r_maxX = Math.min(cellWidth - 1, cellEnd.x + offsetIndex);
        int u_minY = Math.max(0, cellEnd.y - offsetIndex);
        int u_maxY = Math.min(cellHeight - 1, cellEnd.y + offsetIndex);

        int minX;
        int maxX;
        if(cellBegin.x > cellEnd.x){
            minX = Math.max(0, cellBegin.x - offsetIndex);
            maxX = Math.min(cellWidth - 1, cellEnd.x + offsetIndex);
        }else{
            minX = Math.max(0, cellEnd.x - offsetIndex);
            maxX = Math.min(cellWidth - 1, cellBegin.x + offsetIndex);
        }


        int minY;
        int maxY;
        if(cellBegin.y > cellEnd.y){
            minY = Math.max(0, cellBegin.y - offsetIndex);
            maxY = Math.min(cellHeight - 1, cellEnd.y + offsetIndex);
        }else{
            minY = Math.max(0, cellEnd.y - offsetIndex);
            maxY = Math.min(cellHeight - 1, cellBegin.y + offsetIndex);
        }

        StageCell[] cells = new StageCell[(d_maxY - d_minY + 1) * (l_maxX - l_minX + 1) - (maxY - minY + 1) * (maxX - minX + 1)];

        int i = 0;
        for(int y=d_minY; y<=d_maxY; ++y){
            for(int x=l_minX; x<=l_maxX; ++x){
                if(x >= r_minX && x <= r_maxX && y >= u_minY && y <= u_maxY){
                    continue;
                }
                cells[i++] = getCell(x, y);
//                cells.add(getCell(x, y));
            }
        }
        return cells;

//        return null;

    }

    private static StageCell[] getCells(StageCell cell, int offsetIndex) {
        if(cell == null){
            return empty;
        }
        int minX = Math.max(0, cell.x - offsetIndex);
        int maxX = Math.min(cellWidth - 1, cell.x + offsetIndex);
        int minY = Math.max(0, cell.y - offsetIndex);
        int maxY = Math.min(cellHeight - 1, cell.y + offsetIndex);

        StageCell[] cells = new StageCell[(maxX - minX + 1) * (maxY - minY + 1)];
        int i = 0;
        for(int y = minY; y <= maxY; ++y) {
            for (int x = minX; x <= maxX; ++x) {
                cells[i++] = getCell(x, y);
            }
        }

        return cells;
    }

    private static List<StageCell> getChangedCellsOld(StageCell cellBegin, StageCell cellEnd, int i) {
        List<StageCell> list1 = new ArrayList<>();
        list1.addAll(cellBegin.cells9);
        list1.addAll(cellBegin.cells16);

        List<StageCell> list2 = new ArrayList<>();
        list2.addAll(cellEnd.cells9);
        list2.addAll(cellEnd.cells16);

        List<StageCell> result = new ArrayList<>();

        for(StageCell cell : list1){
            if(list2.contains(cell)){
                continue;
            }
            result.add(cell);

        }
        return result;
    }

    private static List<StageCell> getCells9(StageCell cell) {
        List<StageCell> list = new ArrayList<>();
        int minX = Math.max(0, cell.x - 1);
        int maxX = Math.min(cellHeight - 1, cell.x + 1);
        int minY = Math.max(0, cell.y - 1);
        int maxY = Math.min(cellHeight - 1, cell.y + 1);
        for(int y = minY; y<=maxY; y++){
            for(int x=minX; x<=maxX; x++){
                list.add(getCell(x, y));
            }
        }
        return list;
    }

    public static int getCellIndex(int x, int y){
        return x + cellWidth * y;
    }

    private static StageCell[] getChangedCells(StageCell cellBegin, StageCell cellEnd, int offsetIndex) {
            int offsetMax = offsetIndex << 1;

            int minX = -1;
            int maxX = -1;
            int diffX = 0;
            if(cellEnd.x != cellBegin.x){
                // 计算X轴跨了多少个格子
                int offsetX = cellEnd.x - cellBegin.x;
                // 计算X轴跨的方向
                int dirX = offsetX / Math.abs(offsetX);
                // 跨度不能超过offsetIndex的2倍（左右各跨offsetIndex所以是2倍）
                offsetX = Math.abs(offsetX) > offsetMax ? dirX*offsetMax : (offsetX - dirX);
                // 从cellbegin开始远离cellEnd的格子位置
                int startX = cellBegin.x - dirX*offsetIndex;
                // 如果小于0，说明向着X轴正方向走，startX小  startX+offsetX大
                minX = dirX > 0 ? startX : startX + offsetX ;
                maxX = dirX < 0 ? startX : startX + offsetX ;
                if((minX < 0 && maxX < 0) || (minX >= cellWidth && maxX >= cellWidth)){
                    minX = -1;
                    maxX = -1;
                }else{
                    minX = clamp(0, cellWidth - 1, minX);
                    maxX = clamp(0, cellWidth - 1, maxX);
                    diffX = maxX - minX + 1;
                }
            }

            int minY = -1;
            int maxY = -1;
            int diffY = 0;
            if(cellEnd.y != cellBegin.y){
                int offsetY = cellEnd.y - cellBegin.y;
                int dirY = offsetY / Math.abs(offsetY);
                offsetY = Math.abs(offsetY) > offsetMax? dirY*offsetMax : (offsetY - dirY);
                int startY = cellBegin.y - dirY*offsetIndex;
                minY = dirY > 0 ? startY : startY + offsetY;
                maxY = dirY < 0 ? startY : startY + offsetY;

                if((minY < 0 && maxY < 0) || (minY >= cellHeight && maxY >= cellHeight)){
                    minY = -1;
                    maxY = -1;
                }else{
                    minY = clamp(0, cellHeight - 1, minY);
                    maxY = clamp(0, cellHeight - 1, maxY);
                    diffY = maxY - minY + 1;
                }
            }

            int widthMin = Math.max(0, cellBegin.x - offsetIndex);
            int widthMax = Math.min(cellWidth - 1, cellBegin.x + offsetIndex);
            int heightMin = Math.max(0, cellBegin.y - offsetIndex);
            int heightMax = Math.min(cellHeight - 1, cellBegin.y + offsetIndex);

            StageCell[] cells = new StageCell[diffX*(heightMax - heightMin + 1) + diffY*(widthMax - widthMin + 1) - diffX*diffY];
            int i = 0;

            if(diffY > 0){
                for(int y=minY; y<=maxY; ++y){
                    for(int x=widthMin; x<= widthMax; ++x){
                        cells[i++] = getCell(x, y);
                    }
                }
            }
            if(diffX > 0){
                for(int y = heightMin; y <= heightMax; ++y) {
                    // 重复的去掉
                    if(isBetween(minY, maxY, y)){
                        continue;
                    }
                    for (int x = minX; x <= maxX; ++x) {
                        cells[i++] = getCell(x, y);
                    }
                }
            }

            return cells;
    }

    public static int clamp(int min, int max, int value) {
        if (value < min) {
            return min;
        } else if (value >= max) {
            return max;
        } else {
            return value;
        }
    }

    private static boolean isBetween(int minY, int maxY, int y) {
        if(y >= minY && y <= maxY){
            return true;
        }else{
            return false;
        }
    }

    private static StageCell getCell(int x, int y) {
        int index = getCellIndex(x, y);
        return index2Cells[index];
    }


    public static StageCell[] getInsectionCells(StageCell cellBegin, StageCell cellEnd, int offsetIndex) {
        int offsetMax = offsetIndex << 1;

        // 不相交
        if(Math.abs(cellBegin.x - cellEnd.x) > offsetMax || Math.abs(cellBegin.y - cellEnd.y) > offsetMax){
            return empty;
        }

        int minX;
        int maxX;
        if(cellBegin.x > cellEnd.x){
            minX = Math.max(0, cellBegin.x - offsetIndex);
            maxX = Math.min(cellWidth - 1, cellEnd.x + offsetIndex);
        }else{
            minX = Math.max(0, cellEnd.x - offsetIndex);
            maxX = Math.min(cellWidth - 1, cellBegin.x + offsetIndex);
        }

        int minY;
        int maxY;
        if(cellBegin.y > cellEnd.y){
            minY = Math.max(0, cellBegin.y - offsetIndex);
            maxY = Math.min(cellHeight - 1, cellEnd.y + offsetIndex);
        }else{
            minY = Math.max(0, cellEnd.y - offsetIndex);
            maxY = Math.min(cellHeight - 1, cellBegin.y + offsetIndex);
        }

        StageCell[] cells = new StageCell[(maxX - minX + 1) * (maxY - minY + 1)];
        int i = 0;
        for(int y = minY; y <= maxY; ++y) {
            for (int x = minX; x <= maxX; ++x) {
                cells[i++] = getCell(x, y);
            }
        }

        return cells;
    }

}
