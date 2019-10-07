package com.wcq.unionFind.quickFind;

import com.wcq.unionFind.AbstractUnionFind;
import com.wcq.unionFind.ILinkPoint;

import java.util.List;

public class QuickFind extends AbstractUnionFind {
    private ILinkPoint[] elements;

    public QuickFind(List<ILinkPoint> points){
        super(points);

        elements = new ILinkPoint[points.size()];

        for(int i=0; i<points.size(); ++i){
            ILinkPoint point = points.get(i);
            point.setRelation(i);
            elements[i] = point;
        }
    }

    @Override
    public ILinkPoint find(int point) {
        ILinkPoint linkPoint = elements[point];
        // 下标指向自己（和自己的relation一样），说明自己就是根节点
        if(linkPoint.getRelation() == point){
            return linkPoint;
        }
        return elements[linkPoint.getRelation()];
    }

    @Override
    public void union(int p1, int p2) {
        ILinkPoint root1 = find(p1);
        ILinkPoint root2 = find(p2);

        // 关系相等，已经相同
        if(root1.getRelation() == root2.getRelation()){
            return;
        }

        // 把所有p2点的关系都改成p1的关系
        for (ILinkPoint e : elements) {
            if(e.getRelation() == root2.getRelation()){
                e.setRelation(root1.getRelation());
            }
        }

        count--;
    }

    @Override
    public boolean connected(int p1, int p2) {
        ILinkPoint root1 = find(p1);
        ILinkPoint root2 = find(p2);

        return root1.getRelation() == root2.getRelation();
    }
}
