package com.example.lambdas.parametrization.apples;

public class AppleConsumer implements Consumer<Apple> {
    @Override
    public void accept(Apple apple) {
        apple.setWeight(0);
    }
}
