package com.example.lambdas.parametrization.apples.elaborated;

import com.example.lambdas.parametrization.apples.Apple;
import com.example.lambdas.parametrization.apples.elaborated.AppleFinderElaborated;
import com.example.lambdas.parametrization.apples.elaborated.ApplePredicate;
import com.example.lambdas.parametrization.apples.elaborated.IsGreenApple;
import com.example.lambdas.parametrization.apples.elaborated.IsHeavyApple;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static com.example.lambdas.parametrization.apples.AppleStock.appleList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppleFinderElaboratedTest {
    @Test
    public void findGreenApples() {
        List<Apple> greenApples = AppleFinderElaborated.findGreenApples(appleList());

        for (Apple greenApple : greenApples) {
            assertEquals("green", greenApple.getColor());
        }
    }

    @Test
    public void findApples() {
        List<Apple> apples = AppleFinderElaborated.findApples(appleList(), "green", 100);

        for (Apple apple : apples) {
            boolean found = apple.getColor().equals("green") || apple.getWeight() >= 100;
            assertThat(found).isTrue();
        }
    }

    @Test
    public void findByPredicate() {
        List<Apple> greenApples = AppleFinderElaborated.findApplesByPredicate(appleList(), new IsGreenApple());
        for (Apple greenApple : greenApples) {
            assertEquals("green", greenApple.getColor());
        }

        List<Apple> heavyApples = AppleFinderElaborated.findApplesByPredicate(appleList(), new IsHeavyApple());
        for (Apple a : heavyApples) {
            assertTrue(150 <= a.getWeight());
        }

        List<Apple> redApples = AppleFinderElaborated.findApplesByPredicate(appleList(), new ApplePredicate() {
            @Override public boolean test(Apple a) {
                return "red".equalsIgnoreCase(a.getColor());
            }
        });
        for (Apple a : redApples) {
            assertEquals("red", a.getColor());
        }

    }

    @Test
    public void findByLambda() {
        List<Apple> apples = AppleFinderElaborated.findApplesByPredicate(appleList(), a -> "yellow".equalsIgnoreCase(a.getColor()));
        for (Apple a : apples) {
            assertEquals("yellow", a.getColor());
        }
    }

    @Test
    public void sortStock() {
        appleList().sort(new Comparator<Apple>() {
            @Override public int compare(Apple o1, Apple o2) {
                return 0;
            }
        });
    }
}