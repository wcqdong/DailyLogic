package com.wcq.cell;

public class CellTest1 {

    private final static StageCell[] empty_cells = new StageCell[0];

//    public static void main(String[] args) {
//        StageCell cellBegin = new StageCell(85);
//        StageCell cellEnd = new StageCell(148);
//        System.out.println(cellBegin);
//        System.out.println(cellEnd);
//        System.out.println();
//        System.out.println();
//
//        StageCell[] list = getInsectionCells(cellBegin, cellEnd, 2);
//        for (StageCell cell : list){
//            System.out.println(cell);
//        }
//    }
//
//    public static StageCell[] getInsectionCells(StageCell cellBegin, StageCell cellEnd, int offsetIndex) {
//        int offsetMax = offsetIndex << 1;
//
//        // 不相交
//        if(Math.abs(cellBegin.x - cellEnd.x) > offsetMax || Math.abs(cellBegin.y - cellEnd.y) > offsetMax){
//            return empty_cells;
//        }
//
//        int minX;
//        int maxX;
//        if(cellBegin.x > cellEnd.x){
//            minX = Math.max(0, cellBegin.x - offsetIndex);
//            maxX = Math.min(cellWidth - 1, cellEnd.x + offsetIndex);
//        }else{
//            minX = Math.max(0, cellEnd.x - offsetIndex);
//            maxX = Math.min(cellWidth - 1, cellBegin.x + offsetIndex);
//        }
//
//        int minY;
//        int maxY;
//        if(cellBegin.y > cellEnd.y){
//            minY = Math.max(0, cellBegin.y - offsetIndex);
//            maxY = Math.min(cellHeight - 1, cellEnd.y + offsetIndex);
//        }else{
//            minY = Math.max(0, cellEnd.y - offsetIndex);
//            maxY = Math.min(cellHeight - 1, cellBegin.y + offsetIndex);
//        }
//
//        StageCell[] cells = new StageCell[(maxX - minX + 1) * (maxY - minY + 1)];
//        int i = 0;
//        for(int y = minY; y <= maxY; ++y) {
//            for (int x = minX; x <= maxX; ++x) {
//                cells[i++] = getCell(x, y);
//            }
//        }
//
//        return cells;
//    }
//
//    private static StageCell getCell(int x, int y) {
//        return new StageCell(x, y);
//    }
}
