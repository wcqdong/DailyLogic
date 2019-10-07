package com.wcq.unionFind;

import java.util.List;

public abstract class AbstractUnionFind {
    protected int count;

    public AbstractUnionFind(List<ILinkPoint> points){
        this.count = points.size();
    }

//    public abstract ILinkPoint getById(long id);

    public abstract ILinkPoint find(int point);

    public abstract void union(int p1, int  p2);

    public abstract boolean connected(int p1, int  p2);

    public int getCount() {
        return count;
    }
}
