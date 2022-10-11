package com.example.streams;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamsTest {
    @Test
    public void assignment3() {
        //Arrange
        Character[] chars = charList();
        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();
        StringBuilder result3 = new StringBuilder();
        StringBuilder result4 = new StringBuilder();
        StringBuilder result5 = new StringBuilder();

        //Act
        Arrays.stream(chars)
                .forEach(result1::append);
        Arrays.stream(chars)
                .forEach(c -> result2.append(c).append("-"));
        Arrays.stream(chars)
                .forEach(c -> result3.append(c.toString().toUpperCase()));
        Arrays.stream(chars)
                .filter(c -> (c != 'a') && (c != 'e') && (c != 'i') && (c != 'o') && (c != 'u'))
                .forEach(c -> result4.append(c.toString().toUpperCase()));
        Arrays.stream(chars)
                .filter(c -> (c != 'a') && (c != 'e') && (c != 'i') && (c != 'o') && (c != 'u'))
                .distinct()
                .forEach(c -> result5.append(c.toString().toUpperCase()));

        //Assert
        assertThat(result1.toString()).isEqualTo("HelloWorld");
        assertThat(result2.toString()).isEqualTo("H-e-l-l-o-W-o-r-l-d-");
        assertThat(result3.toString()).isEqualTo("HELLOWORLD");
        assertThat(result4.toString()).isEqualTo("HLLWRLD");
        assertThat(result5.toString()).isEqualTo("HLWRD");
    }

    @Test
    public void assignment4() {
        //Arrange
        int[] numbers = new int[]{1, 2, 3, 4};
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(1);
        expectedResult.add(4);
        expectedResult.add(9);
        expectedResult.add(16);

        //Act
        Arrays.stream(numbers)
                .forEach(number -> result.add(number * number));

        //Assert
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void assignment5() {
        //Arrange
        int[] numbers = new int[]{1, 2, 3, 4};
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(1);
        expectedResult.add(1);
        expectedResult.add(2);
        expectedResult.add(2);
        expectedResult.add(3);
        expectedResult.add(3);
        expectedResult.add(4);
        expectedResult.add(4);

        //Act
        Arrays.stream(numbers)
                .flatMap(number -> {
                    int[] temp = new int[]{number, number};
                    return Arrays.stream(temp);
                })
                .forEach(result::add);

        //Assert
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void assignment6() {
        //Arrange
        int[] numbers1 = new int[]{1, 2, 3};
        int[] numbers2 = new int[]{3, 4};
        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> expectedResult = new ArrayList<>();
        expectedResult.add(new int[] {1, 3});
        expectedResult.add(new int[] {1, 4});
        expectedResult.add(new int[] {2, 3});
        expectedResult.add(new int[] {2, 4});
        expectedResult.add(new int[] {3, 3});
        expectedResult.add(new int[] {3, 4});

        //Act
        Arrays.stream(numbers1)
                .forEach(number1 -> {
                    Arrays.stream(numbers2)
                            .forEach(number2 -> {
                                result.add(new int[]{number1, number2});
                            });
                });

        //Assert
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i)).isEqualTo(expectedResult.get(i));
        }
        // Idk why this doesnt work VVV
        // assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void assignment7() {
        //Arrange
        int[] numbers1 = new int[]{1, 2, 3};
        int[] numbers2 = new int[]{3, 4};
        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> expectedResult = new ArrayList<>();
        expectedResult.add(new int[] {2, 4});
        expectedResult.add(new int[] {3, 3});

        //Act
        Arrays.stream(numbers1)
                .forEach(number1 -> {
                    Arrays.stream(numbers2)
                            .forEach(number2 -> {
                                if ((number1 + number2) % 3 == 0) {
                                    result.add(new int[]{number1, number2});
                                }
                            });
                });

        //Assert
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i)).isEqualTo(expectedResult.get(i));
        }
    }

    @Test
    public void assignment8() {
        //Arrange
        Character[] chars = charList();
        String result;

        //Act
        String[] array = Stream.of(chars).map(String::valueOf).toArray(String[]::new);
        result = Arrays.stream(array)
                        .collect(Collectors.joining());

        //Assert
        assertThat(result).isEqualTo("HelloWorld");
    }

    @Test
    public void assignment9() {
        //Arrange
        Character[] chars = charList();
        String result;

        //Act
        String[] array = Stream.of(chars).map(String::valueOf).toArray(String[]::new);
        result = Arrays.stream(array)
                        .collect(Collectors.joining("_"));

        //Assert
        assertThat(result).isEqualTo("H_e_l_l_o_W_o_r_l_d");
    }

    @Test
    public void assignment10() {
        //Arrange
        Character[] chars = charList();
        String result;

        //Act
        String[] array = Stream.of(chars).map(String::valueOf).toArray(String[]::new);
        result = Arrays.stream(array)
                    .reduce((a, b) -> a + "_" + b)
                    .orElse("");

        //Assert
        assertThat(result).isEqualTo("H_e_l_l_o_W_o_r_l_d");
    }

    private Character[] charList() {
        return new Character[]{'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd'};
    }
}
