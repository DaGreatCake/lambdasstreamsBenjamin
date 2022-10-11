package com.example.streams;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DishTest {

    @Test
    public void lowCaloricClassicStyle() {
        //Arrange
        List<Dish> menu = ExampleCollections.menu;
        String[] result = new String[3];
        String[] expectedResult = new String[]{"prawns", "rice", "season fruit"};

        //Act
        menu.sort(Comparator.comparing(Dish::getName));

        int resultCounter = 0;
        for (Dish dish : menu) {
            if (resultCounter < 3 && dish.getCalories() < 400) {
                result[resultCounter] = dish.getName();
                resultCounter++;
            }
        }

        //Assert
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void lowCaloricStreams() {
        //Arrange
        List<Dish> menu = ExampleCollections.menu;
        ArrayList<String> result = new ArrayList<>();
        String[] expectedResult = new String[]{"prawns", "rice", "season fruit"};

        //Act
        menu
                .stream()
                .sorted(Comparator.comparing(Dish::getName))
                .filter(dish -> dish.getCalories() < 400)
                .limit(3)
                .forEach(dish -> result.add(dish.getName()));


        //Assert
        assertThat(result.toArray()).isEqualTo(expectedResult);
    }
}
