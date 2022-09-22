package com.gali.box;

//设计一个统一的规则，约束以后的所有Box长相一样
//  所有的使用方法都一样  add get remove  size

public interface Box<T> {

    //主要
    public abstract boolean add(T element);

    public abstract void add(int index,T element);

    public abstract void addAll(Box<T> box);

    public abstract void addAll(int index,Box<T> box);

    //主要
    public abstract T get(int index);
    //主要
    public abstract T remove(int index);
    //主要
    public abstract int size();

}
