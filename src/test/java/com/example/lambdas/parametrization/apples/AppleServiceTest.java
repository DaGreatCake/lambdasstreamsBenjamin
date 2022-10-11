package com.example.lambdas.parametrization.apples;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class AppleServiceTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void shouldPrintApples() {
        //Arrange
        AppleService appleService = new AppleService();
        ArrayList<Apple> apples = testApples();

        //Act
        appleService.consumeApples(apples, (Apple apple) -> {
            System.out.println(apple.toString());
        });

        //Assert
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo("Apple Color: red, Weight: 20"
                + System.lineSeparator() + "Apple Color: green, Weight: 30");
    }
    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void applesWeightZero() {
        //Arrange
        AppleService appleService = new AppleService();
        ArrayList<Apple> apples = testApples();

        //Act
        appleService.consumeApples(apples, new AppleConsumer());
        int weightZero = apples.get(0).getWeight() + apples.get(1).getWeight();

        //Assert
        assertThat(weightZero).isEqualTo(0);
    }

    private ArrayList<Apple> testApples() {
        Apple apple1 = new Apple("red", 20);
        Apple apple2 = new Apple("green", 30);

        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);

        return apples;
    }
}
