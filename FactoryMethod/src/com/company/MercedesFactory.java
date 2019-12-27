package com.company;

public class MercedesFactory extends CarFactory {
    static {

    }
    
    @Override
    public Car createCar() {
        return new Mercedes();
    }
}
