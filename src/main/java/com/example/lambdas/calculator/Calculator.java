package com.example.lambdas.calculator;

import java.util.function.Consumer;
import java.util.function.Function;

public class Calculator {

    private Double result;

    private Calculator(Double d) {
        this.result = d;
    }

    public static Calculator of(Double d) {
        return new Calculator(d);
    }

    public Calculator calculate(Function<Double, Double> f) {
        return new Calculator(f.apply(this.result));
    }

    public Calculator subCalculate(Function<Double, Calculator> f) {
        return f.apply(this.result);
    }

    public Double result() {
        return result;
    }

    public Calculator accept(Consumer<Double> c) {
        c.accept(this.result);
        return this;
    }
}
