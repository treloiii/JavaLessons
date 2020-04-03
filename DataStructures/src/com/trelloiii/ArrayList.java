package com.trelloiii;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayList<T> implements List<T> {
    private final int INIT_CAPACITY=5;
    private int size=0;
    private Object[] array;

    public ArrayList() {
        this.array=new Object[INIT_CAPACITY];
    }

    public ArrayList(int initCapacity) {
        this.array=new Object[initCapacity];
    }

    @Override
    public void add(T o) {
        if(size<array.length) {
            array[size++] = o;
        }
        else {
            int newLen=size+INIT_CAPACITY;
            array=Arrays.copyOf(array,newLen);
            add(o);
        }


    }

    @Override
    public T get(int index) {
        return (T)array[index];
    }

    @Override
    public T remove(int index) {
        T removed=(T) array[index];
        int countOfCopy=size-index-1;// count of elements which need to be copy
        if(countOfCopy>0)
            System.arraycopy(array,index+1,array,index,countOfCopy);//copy all elements from index+1 and paste it into index position
        array[--size]=null;// null last element, because it not need no more and garbage collector can delete it
        return removed;
    }

    @Override
    public T remove(T o) {
        int index =Arrays.binarySearch(array,o,null);
        return remove(index);
    }

    @Override
    public int indexOf(T o) {
        return Arrays.binarySearch(array,o,null);
    }

    @Override
    public void set(T o, int index) {
        this.array[index]=o;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.array=new Object[INIT_CAPACITY];
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for (T i:this) {
            sb.append(i).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator(this);
    }

    class ArrayListIterator implements Iterator<T>{
        private ArrayList<T> list;
        private int startIndex=0;
        public ArrayListIterator(ArrayList<T> list) {
            this.list=list;
        }

        @Override
        public boolean hasNext() {
            return list.size()>startIndex;
        }

        @Override
        public T next() {
            return list.get(startIndex++);
        }
    }
}
