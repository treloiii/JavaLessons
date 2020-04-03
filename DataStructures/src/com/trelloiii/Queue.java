package com.trelloiii;

public interface Queue<T> extends Iterable<T>{
    void offer(T o);
    T peek();
    T poll();
    int size();
}
