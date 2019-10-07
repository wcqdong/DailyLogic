package com.wcq.unionFind.weightedQuickUnion;

import com.wcq.unionFind.AbstractUnionFind;
import com.wcq.unionFind.ILinkPoint;

import java.util.List;

/**
 * 加权的QuickUnion
 */
public class WeightedQuickUinon extends AbstractUnionFind {

    private ILinkPoint[] elements;
    private int[] weights;

    public WeightedQuickUinon(List<ILinkPoint> points){
        super(points);

        this.elements = new ILinkPoint[points.size()];
        this.weights = new int[points.size()];
        for (int i=0; i<points.size(); ++i) {
            ILinkPoint point = points.get(i);
            this.elements[i] = point;
            point.setRelation(i);
            // 每个触点就是一个连接分量，所以权重都是1
            this.weights[i] = 1;
        }

    }

    @Override
    public ILinkPoint find(int point) {
        ILinkPoint root = elements[point];
        while (root.getRelation() != point){
            point = (int)root.getRelation();
            root = elements[point];
        }

        return root;
    }

    @Override
    public void union(int p1, int p2) {
        ILinkPoint point1 = find(p1);
        ILinkPoint point2 = elements[p2];

        if(weights[p1] < weights[p2]){
            point1.setRelation(point2.getRelation());
            weights[p2] += weights[p1];
        }else{
            point2.setRelation(point1.getRelation());
            weights[p1] += weights[p2];
        }

        count--;
    }

    @Override
    public boolean connected(int p1, int p2) {
        ILinkPoint point1 = elements[p1];
        ILinkPoint point2 = elements[p2];
        return point1.getRelation() == point2.getRelation();
    }
}
