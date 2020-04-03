package com.trelloiii;

import java.util.Iterator;

public class LinkedList<T> implements List<T> {
    Node<T> head=new Node<>(null,null,null);
    Node<T> tail=new Node<>(null,null,null);
    private int size;
    public LinkedList() {
        tail.prev=head;
        head.next=tail;
    }

    @Override
    public void add(T o) {
        Node<T> node=new Node<>(o,head.next,head);
        head.next.prev=node;
        head.next=node;
        node.index=size++;
    }
    @Override
    public void addToFront(T o){
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
        return 0;
    }

    @Override
    public void set(T o, int index) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
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
        Node<T> current=tail.prev;
        while(current.prev!=null){
            if(current.index==index)
                return current;
            current=current.prev;
        }
        throw new IndexOutOfBoundsException();
    }
    private Node<T> node(T o){
        Node<T> current=tail.prev;
        while(current.prev!=null){
            if(current.current.equals(o))
                return current;
            current=current.prev;
        }
        throw new IndexOutOfBoundsException();
    }
    private T unlink(Node<T> removed){
        removed.next.prev=removed.prev;
        removed.prev.next=removed.next;
        size--;
        return removed.current;
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
}
