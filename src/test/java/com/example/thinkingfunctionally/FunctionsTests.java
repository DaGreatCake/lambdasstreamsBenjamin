package com.example.thinkingfunctionally;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionsTests {
    @Test
    @DisplayName("Tests if fibI returns the n-th fibonacci number")
    public void fibITest() {
        //Arrange
        int n = 7;

        //Act
        int result = Functions.fibI(n);

        //Assert
        assertThat(result).isEqualTo(8);
    }

    @Test
    @DisplayName("Tests if fibR returns the n-th fibonacci number")
    public void fibRTest() {
        //Arrange
        int n = 7;

        //Act
        int result = Functions.fibR(n);

        //Assert
        assertThat(result).isEqualTo(8);
    }

    @Test
    @DisplayName("Print 1-nth number using fibR")
    public void fibRPrintTest() {
        int n = 8;

        for (int i = 1; i <= n; i++) {
            System.out.println(Functions.fibR(i));
        }
    }

    @Test
    @DisplayName("Print 1-nth number using fibTailR")
    public void fibTailRPrintTest() {
        int n = 8;

        for (int i = 1; i <= n; i++) {
            System.out.println(Functions.fibTailR(i));
        }
    }

    @Test
    @DisplayName("Measure time difference between fibI and fibR and fibTailR")
    public void fibIAndFibRTimeMeasureTest() {
        long startTimeFibI = System.nanoTime();
        Functions.fibI(45);
        long elapsedTimeFibI = System.nanoTime() - startTimeFibI;

        long startTimeFibR = System.nanoTime();
        Functions.fibR(45);
        long elapsedTimeFibR = System.nanoTime() - startTimeFibR;

        long startTimeFibTailR = System.nanoTime();
        Functions.fibTailR(45);
        long elapsedTimeFibTailR = System.nanoTime() - startTimeFibTailR;

        System.out.println("FibI time: " + elapsedTimeFibI);
        System.out.println("FibR time: " + elapsedTimeFibR);
        System.out.println("FibTailR time: " + elapsedTimeFibTailR);
    }
}
