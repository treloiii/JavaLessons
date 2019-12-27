package com.company;

public class Mercedes extends Car {
    @Override
    public void drive() {
        System.out.println("Mercedes is drive");
    }

    @Override
    public void beep() {
        System.out.println("Mercedes beep");
    }

    public Mercedes() {
        this.mark="Mercedes";
    }
}
