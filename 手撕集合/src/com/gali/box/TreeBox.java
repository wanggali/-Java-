package com.gali.box;

public class TreeBox<T> extends AbstractBox<T> {
    @Override
    public boolean add(T element) {
        return false;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
