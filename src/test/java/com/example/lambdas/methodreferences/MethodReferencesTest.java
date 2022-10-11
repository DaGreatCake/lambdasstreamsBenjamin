package com.example.lambdas.methodreferences;

import com.example.lambdas.parametrization.apples.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodReferencesTest {

    private final String[] COLORS = {"green", "red", "yellow"};
    private final int[] WEIGHTS = {100, 200, 300, 400};

    private MethodReferences target = new MethodReferences();

    @Test
    public void testAppleFactory() {
        //Arrange
        List<Apple> apples;

        //Act
        apples = target.appleFactory(COLORS, WEIGHTS, Apple::new);
        int listSize = apples.size();
        int expectedSize = COLORS.length * WEIGHTS.length;

        //Assert
        assertThat(listSize).isEqualTo(expectedSize);
    }
}
