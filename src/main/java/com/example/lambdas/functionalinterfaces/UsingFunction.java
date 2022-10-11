package com.example.lambdas.functionalinterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UsingFunction {
    public static List<Integer> map(List<String> list, Function<String, Integer> f) {
        List<Integer> result = new ArrayList<>();

        for (String s : list) {
            result.add(f.apply(s));
        }

        return result;
    }

}

