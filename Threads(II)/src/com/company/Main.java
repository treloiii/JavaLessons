package com.company;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> images1=new ArrayList<>();
        List<Integer> images2=new ArrayList<>();
        for(int i=0;i<1000;i++){
            images1.add(i);
        }
        for(int i=1000;i<2000;i++){
            images2.add(i);
        }
        ImageUploader uploader1=new ImageUploader(images1);
        ImageUploader uploader2=new ImageUploader(images2);
        List<ImageUploader> uploaders=new ArrayList<>();
        uploaders.add(uploader1);
        uploaders.add(uploader2);
        ExecutorService executorService= Executors.newFixedThreadPool(4);
        executorService.submit(()->{
            try {
                uploader1.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try{
                uploader1.consume();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                uploader2.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try{
                uploader2.consume();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        });
//        List<Thread> threads=new ArrayList<>();
//        for(ImageUploader uploader:uploaders){
//            Thread a=new Thread(()->{
//                try {
//                    uploader.produce();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            Thread b=new Thread(()->{
//                try {
//                    uploader.consume();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            threads.add(a);
//            threads.add(b);
//        }
//        for(Thread thread:threads){
//            thread.start();
//        }
        long start=System.currentTimeMillis();
//        for(Thread thread:threads){
//            thread.join();
//        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("time: "+(System.currentTimeMillis()-start));
        System.out.println(Arrays.toString(uploader1.getResult().toArray()));
        System.out.println(Arrays.toString(uploader2.getResult().toArray()));
      //  uploader1.getResult().addAll(uploader2.getResult());


    }
}
class ImageUploader{
    private int queueSize=5;
    private Queue<Integer> imageQueue=new LinkedList<>();
//    private Lock lock=new ReentrantLock();
    private final Object lock=new Object();
    private List<Integer> images;
    private List<Integer> result=new ArrayList<>();
    private int index=0;

    public List<Integer> getResult() {
        return result;
    }

    public ImageUploader(List<Integer> images) {
        this.images = images;
        System.out.println(this.images.hashCode());
    }

    public List<Integer> getImages() {
        return images;
    }

    public void produce() throws InterruptedException {
        while(images.size()>0) {
            synchronized (this.lock) {
                //System.out.println(Arrays.toString(images.toArray()));
                // lock.lock();
                while (imageQueue.size() == queueSize) {
                    lock.wait();
                }
                try {
                    imageQueue.offer(images.get(index));
                }
                catch (Exception e){
                    break;
                }
                ++index;
                // lock.unlock();
                lock.notify();
            }

        }

    }
    public void consume() throws InterruptedException {
        while (images.size()>0) {
            synchronized (this.lock) {
                //System.out.println(Arrays.toString(images.toArray()));
                //lock.lock();
                while (imageQueue.size() == 0) {
                    lock.wait();
                }
                int polled=imageQueue.poll();
                System.out.println("image: " + polled + " uploading");
                Thread.sleep(1);
                System.out.println("successful");
                images.remove(0);
                --index;
                result.add(polled);
                //lock.unlock();
                lock.notify();
            }
            Thread.sleep(2);
            System.out.println("work");
        }
    }

}