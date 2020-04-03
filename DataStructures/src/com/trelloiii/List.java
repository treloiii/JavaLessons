package com.trelloiii;

public interface List<T> extends Iterable<T> {
    void add(T o);
    T get(int index);
    T remove(int index);
    T remove(T o);
    int indexOf(T o);
    void set(T o,int index);
    int size();
    void clear();
}
