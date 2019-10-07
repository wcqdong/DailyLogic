package com.wcq.unionFind;

public interface ILinkPoint {
    /** 关系，具有相同关系和触点属于同一个连通分量 */
    public int getRelation();
    public void setRelation(int relation);
    /** 实际应用中每个触点应该有个id来唯一表示此触点 */
    public long getId();

}
