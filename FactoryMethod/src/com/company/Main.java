package com.company;

public class Main {

    public static void main(String[] args) {
        MercedesFactory mercedesFactory=new MercedesFactory();
        Car mercedes=mercedesFactory.createCar();
        BmwFactory bmwFactory=new BmwFactory();
        Car bmw=bmwFactory.createCar();

        bmw.beep();
        bmw.drive();
        mercedes.beep();
        mercedes.drive();
    }
}
