package com.wcq.unionFind;

public class Friend implements ILinkPoint<Integer> {

    private long id;

    private Integer relation;

    public Friend(long id){
        this.id = id;
    }

    @Override
    public Integer getRelation() {
        return relation;
    }

    @Override
    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    @Override
    public long getId() {
        return id;
    }
}
