package com.example.lambdas.parametrization.apples;

public class ApplePrinter implements Consumer<Apple> {
    @Override
    public void accept(Apple apple) {
        System.out.println(apple.toString());
    }
}
