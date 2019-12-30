package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int imagesCount=100;
        int threadsCount=4;
        ImageUploader uploader=new ImageUploader();
        ExecutorService executorService= Executors.newFixedThreadPool(threadsCount);
        long start=System.currentTimeMillis();
        for(int i=0;i<threadsCount;i++){
            executorService.submit(()->{
                for(int j=0;j<imagesCount/threadsCount;j++){
                    uploader.uploadImage();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("final time: "+(System.currentTimeMillis()-start));
    }
}

class ImageUploader{

    public void uploadImage(){
        System.out.println("Image upload starts...");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Error, image not uploaded");
        }
        System.out.println("Image uploaded");
    }
}
