package com.example.lambdas.functionalinterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class UsingSupplier {
    public static List<String> concat(List<String> list, Supplier<String> s) {
        List<String> result = new ArrayList<>();

        for (String str : list) {
            result.add(str.concat(s.get()));
        }

        return result;
    }

}

