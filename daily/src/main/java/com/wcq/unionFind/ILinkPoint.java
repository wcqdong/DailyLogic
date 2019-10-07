package com.wcq.unionFind;

public interface ILinkPoint<T> {

    public T getRelation();
    public void setRelation(T relation);

    public long getId();

}
