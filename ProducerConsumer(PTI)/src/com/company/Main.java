package com.company;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        DoTasks tasks= new DoTasks();
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
    private BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(10);

    public void produce() throws InterruptedException {
        while(true){
            Random r= new Random();
            int next=r.nextInt(10);
            queue.put(next);
        }
    }
    public void consume() throws InterruptedException {
        while (true){
            Thread.sleep(1000);
            System.out.println("consumed: "+queue.take());
            System.out.println("queue size is: "+queue.size());
        }
    }
}
