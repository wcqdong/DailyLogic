package com.wcq.unionFind.QuickUnion;

import com.wcq.unionFind.AbstractUnionFind;
import com.wcq.unionFind.ILinkPoint;

import java.util.List;

public class QuickUnion extends AbstractUnionFind {

    private ILinkPoint[] elements;

    public QuickUnion(List<ILinkPoint> points){
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
        ILinkPoint root = elements[point];
        while(root.getRelation() != point){
            point = root.getRelation();
            root = elements[point];
        }
        return root;
    }

    @Override
    public void union(int p1, int p2) {
        ILinkPoint root1 = find(p1);
        ILinkPoint root2 = find(p2);

        if(connected(root1, root2)){
            return;
        }
        // root2的relation指向root
        root2.setRelation(root1.getRelation());

        count--;
    }

    @Override
    public boolean connected(int p1, int p2) {

        return find(p1).getRelation() == find(p2).getRelation();
    }

    public boolean connected(ILinkPoint p1, ILinkPoint p2) {

        return p1.getRelation() == p2.getRelation();
    }

}
