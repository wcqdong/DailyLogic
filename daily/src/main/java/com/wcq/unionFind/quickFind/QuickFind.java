package com.wcq.unionFind.quickFind;

import com.wcq.unionFind.AbstractUnionFind;
import com.wcq.unionFind.ILinkPoint;

import java.util.List;

public class QuickFind extends AbstractUnionFind {

//    private Map<Long, ILinkPoint> id2Point = new HashMap<>();
    private ILinkPoint[] elements;

    public QuickFind(List<ILinkPoint> points){
        super(points);

        elements = new ILinkPoint[points.size()];

        for(int i=0; i<points.size(); ++i){
            ILinkPoint point = points.get(i);
            point.setRelation(i);
            elements[i] = point;
//            id2Point.put(point.getId(), point);
        }
    }

    @Override
    public ILinkPoint find(int point) {
        return elements[point];
    }

    @Override
    public void union(int p1, int p2) {
        ILinkPoint point1 = find(p1);
        ILinkPoint point2 = find(p2);

        // 关系相等，已经相同
        if(point1.getRelation() == point2.getRelation()){
            return;
        }

        // 把所有p2点的关系都改成p1的关系
        for (ILinkPoint e : elements) {
            if(e.getRelation() == point2.getRelation()){
                e.setRelation(point1.getRelation());
            }
        }

        count--;
    }

    @Override
    public boolean connected(int p1, int p2) {
        ILinkPoint point1 = find(p1);
        ILinkPoint point2 = find(p2);

        return point1.getRelation() == point2.getRelation();
    }

    public boolean connected(ILinkPoint p1, ILinkPoint p2) {
        return p1.getRelation() == p2.getRelation();
    }
}
