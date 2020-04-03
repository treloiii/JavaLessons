package com.trelloiii;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
//        java.util.ArrayList
//        java.util.LinkedList<String> list=new java.util.LinkedList<>();
//        list.remove("sa")
        List<String> myList=new LinkedList<>();
        for(int i=0;i<5;i++)
            myList.add(i+" str");
        myList.remove(1);
        System.out.println(myList);
    }
}
