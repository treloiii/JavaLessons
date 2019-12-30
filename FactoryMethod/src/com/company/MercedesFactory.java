package com.company;

public class MercedesFactory extends CarFactory {
    @Override
    public Car createCar() {
        return new Mercedes();
    }
}
