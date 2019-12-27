package com.company;

public class Bmw extends Car {
    @Override
    public void drive() {
        System.out.println("BMW is drive");
    }

    @Override
    public void beep() {
        System.out.println("BMW beep");
    }

    public Bmw() {
        this.mark="BMW";
    }
}
