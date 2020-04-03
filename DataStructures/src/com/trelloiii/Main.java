package com.trelloiii;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
//        java.util.ArrayList
//        java.util.Queue<String> list=new java.util.LinkedList<>();
//        list.addFirst();
//        list.remove("s")
//        list.remove("sa")
        Queue<String> myList=new LinkedList<>(5);
        for(int i=0;i<5;i++)
            myList.offer(i+" str");

        System.out.println(myList.peek());
//        myList.poll();
        System.out.println(myList.size());
    }
}
