package com.company;

public class BmwFactory extends CarFactory {
    @Override
    public Car createCar() {
        return new Bmw();
    }
}
