package com.wcq.unionFind;

import java.util.List;

/**
 * 连通方法父类
 * 为了不重复定义count变量，所以这里用抽象类，也可以定义为借口
 */
public abstract class AbstractUnionFind {
    /** 连接分量的个数 */
    protected int count;

    /** 构造方法 */
    public AbstractUnionFind(List<ILinkPoint> points){
        this.count = points.size();
    }

    /** 查找连同分量的根节点 */
    public abstract ILinkPoint find(int point);

    /** 连接两个触点 */
    public abstract void union(int p1, int  p2);

    /** 判断两个触点是否相连 */
    public abstract boolean connected(int p1, int  p2);

    /** 连通分量的个数 */
    public int getCount() {
        return count;
    }
}
