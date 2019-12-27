package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        DoTasks tasks=new DoTasks();
	    Thread producer=new Thread(()->{
            try {
                tasks.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
	    Thread consumer=new Thread(()->{
            try {
                tasks.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
	    producer.start();
	    consumer.start();

	    try {
            producer.join();
            consumer.join();
        }
	    catch (Exception e){
	        e.printStackTrace();
        }
    }
}

class DoTasks{
    Queue<Integer> queue=new LinkedList<>();
    private final Object lock=new Object();
    int MAX_SIZE=10;
    public void produce() throws InterruptedException {
        while(true) {
            synchronized (this.lock) {
                while (queue.size()==MAX_SIZE) {
                    lock.wait();
                }
                Random r = new Random();
                int next=r.nextInt(10);
                queue.offer(next);
                // System.out.println(next+" produced");
                // System.out.println("queue size is: "+queue.size());
                lock.notify();
            }
        }
    }
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this.lock) {
                while (queue.size() == 0) {
                    lock.wait();
                }
                Random r = new Random();
                System.out.println(queue.poll()+" consumed");
                System.out.println("queue size is: "+queue.size());
                lock.notify();
            }
            Thread.sleep(1000);
        }
    }
}
