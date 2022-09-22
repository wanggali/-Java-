package com.gali.box;

//添加中间这个抽象类的目的
//  为了让每一个具体的Box，更加的灵活/方便

//  适配器模式Adapter Pattern
//  类适配器，对象适配器，缺省适配器

//  23种常用的设计模式      最终的体现无外乎就是类和类之间的关系

public abstract class AbstractBox<T> implements Box<T>{

    private int size;

    //主要
    public abstract boolean add(T element);
    //主要
    public abstract T get(int index);
    //主要
    public abstract T remove(int index);
    //主要
    @Override
    public int size(){
        return size;
    }


    @Override
    public void add(int index, T element){
        throw new NuSupportedOperationException();
    }
    @Override
    public void addAll(Box<T> box){
        throw new NuSupportedOperationException();
    }
    @Override
    public void addAll(int index, Box<T> box){
        throw new NuSupportedOperationException();
    }

    //添加一些所有子类共有的，但是父类接口还没有规定的
    //独有

}
