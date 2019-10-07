package com.wcq.unionFind;

public class Friend implements ILinkPoint {

    private long id;

    private Integer relation;

    public Friend(long id){
        this.id = id;
    }

    @Override
    public int getRelation() {
        return relation;
    }

    @Override
    public void setRelation(int relation) {
        this.relation = relation;
    }

    @Override
    public long getId() {
        return id;
    }
}
