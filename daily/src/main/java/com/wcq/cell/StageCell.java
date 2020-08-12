package com.wcq.cell;

import java.util.List;

public class StageCell {
    public final int x;
    public final int y;

    public List<StageCell> cells9;
    public List<StageCell> cells16;

    StageCell(int x, int y){
        this.x = x;
        this.y = y;
    }

    StageCell(int index){
        this.x = index % cellTest.cellWidth;
        this.y = index / cellTest.cellWidth;
    }

    @Override
    public String toString() {
        return x + y*cellTest.cellWidth + "  " + x + "  " + y;
    }

    public void setCells9(List<StageCell> cells9) {
        this.cells9 = cells9;
    }

    public void setCells16(List<StageCell> cells16) {
        this.cells16 = cells16;
    }
}
