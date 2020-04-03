package com.trelloiii;

import java.util.Iterator;

public class LinkedList<T> implements List<T> , Queue<T> {
    Node<T> head=new Node<>(null,null,null);
    Node<T> tail=new Node<>(null,null,null);
    private int size;
    private int maxSize=10;
    public LinkedList() {
        tail.prev=head;
        head.next=tail;
    }
    public LinkedList(int maxSize) {
        tail.prev=head;
        head.next=tail;
        this.maxSize=maxSize;
    }
    @Override
    public void add(T o) {
        Node<T> node=new Node<>(o,head.next,head);
        head.next.prev=node;
        head.next=node;
        node.index=size++;
    }

    public void addFront(T o){
        Node<T> node=new Node<>(o,tail,tail.prev);
        tail.prev.next=node;
        tail.prev=node;
        node.index=size++;
    }

    @Override
    public T get(int index) {
        return node(index).current;
    }

    @Override
    public T remove(int index) {
        return unlink(node(index));
    }

    @Override
    public T remove(T o) {
        return unlink(node(o));
    }

    @Override
    public int indexOf(T o) {
        return node(o).index;
    }

    @Override
    public void set(T o, int index) {
        node(index).current=o;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        tail.prev=head;
        head.next=tail;
        size=0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<>(tail);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("[");
        Node<T> current=tail.prev;
        while(current.prev!=null){
            sb.append(current.current).append(",");
            current=current.prev;
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }
    private Node<T> node(int index){
        boolean leftOrRight=index<((double) size/2.0);//if true, go from head to tail, else go from tail to head
        if(leftOrRight){
            Node<T> current = head.next;
            while (current.next != null) {
                if (current.index == index)
                    return current;
                current = current.next;
            }
        }else {
            Node<T> current = tail.prev;
            while (current.prev != null) {
                if (current.index == index)
                    return current;
                current = current.prev;
            }
        }
        throw new IndexOutOfBoundsException();
    }
    private Node<T> node(T o){
        Node<T> current = tail.prev;
        if(o!=null) {
            while (current.prev != null) {
                if (current.current.equals(o))
                    return current;
                current = current.prev;
            }
        }
        else{
            while (current.prev != null) {
                if (current.current==null)
                    return current;
                current = current.prev;
            }
        }
        throw new IndexOutOfBoundsException();
    }
    private T unlink(Node<T> removed){
        removed.next.prev=removed.prev;
        removed.prev.next=removed.next;
        size--;
        return removed.current;
    }

    @Override
    public void offer(T o) {
        if(size<maxSize)
            add(o);
        else
            throw new IllegalStateException("Queue is full");
    }

    @Override
    public T peek() {
        return head.next.current;
    }

    @Override
    public T poll() {
        Node<T> polled=head.next;
        unlink(polled);
        return polled.current;
    }

    private static class Node<T>{
        public T current;
        public Node<T> next;
        public Node<T> prev;
        public int index;
        public Node(T current, Node<T> next, Node<T> prev) {
            this.current = current;
            this.next = next;
            this.prev = prev;
        }
    }
    class LinkedIterator<T> implements Iterator<T>{
        Node<T> current;
        LinkedIterator(Node<T> current){
            this.current=current;
        }
        @Override
        public boolean hasNext() {
            current = current.prev;
            return current.prev != null;
        }

        @Override
        public T next() {
            return current.current;
        }
    }
}
