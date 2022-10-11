package com.example.lambdas.methodreferences;

import com.example.lambdas.parametrization.apples.Apple;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.function.*;

public class MethodReferences {

    // Lab 3.1
    public void equivalentMethodReferences() {
        ToIntFunction<String> stringToIntLambda = (String s) -> Integer.parseInt(s);
        ToIntFunction<String> stringToIntMethodReference = Integer::parseInt;

        BiPredicate<List<String>, String> containsLambda = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> containsMethodReference = List::contains;

        Predicate<String> startsWithNumberLambda = (String string) -> this.startsWithNumber(string);
        Predicate<String> startsWithNumberMethodReference = this::startsWithNumber;

        // ----------------------

        // Usage of these lambda's:
        int i42 = parse("42", (String s) -> Integer.parseInt(s));
        check(List.of("1", "2", "3"), "3", (list, element) -> list.contains(element));
        check("1abc", (String string) -> this.startsWithNumber(string));

        //Method reference usage
        int int42 = parse("42", Integer::parseInt);
        check(List.of("1", "2", "3"), "3", List::contains);
        check("1abc", this::startsWithNumber);
    }

    // Lab 3.2
    public void systemMethodReferences() {
        // For example:
        Supplier<Console> s = System::console;
        Callable<Console> c = System::console;
        Supplier<Properties> p = System::getProperties;
    }

    // Lab 3.3
    public void stringMethodReferences() {
        Function<Integer, String> s = String::valueOf;
    }

    // Lab 3.4
    public List<Apple> appleFactory(String[] colors, int[] weights, BiFunction<String, Integer, Apple> creator) {
        List<Apple> result = new ArrayList<>();

        for (String color : colors) {
            for (int weight : weights) {
                result.add(creator.apply(color, weight));
            }
        }

        return result;
    }

    private int parse(String s, ToIntFunction<String> f) {
        return f.applyAsInt(s);
    }

    private boolean check(List<String> strings, String s, BiPredicate<List<String>, String> p) {
        return p.test(strings, s);
    }

    private boolean check(String s, Predicate<String> p) {
        return p.test(s);
    }

    private boolean startsWithNumber(String s) {
        return s.charAt(0) >= 48 && s.charAt(0) < 58;
    }
}
