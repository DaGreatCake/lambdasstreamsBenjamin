package com.example.lambdas.functionalinterfaces;

import java.util.List;
import java.util.function.Consumer;

public class UsingConsumer {

    public static void forEach(List<Integer> list, Consumer<Integer> c) {
        for (Integer i : list) {
            c.accept(i);
        }
    }

}

