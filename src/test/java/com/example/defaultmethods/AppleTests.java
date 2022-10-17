package com.example.defaultmethods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppleTests {
    /*TODO @Joris ik snap niet goed wat er de bedoeling is van deze opdracht (opdracht 5) alles wat ik probeer werkt namelijk gewoon*/
    @Test
    @DisplayName("Tests apple functionality")
    public void appleTest() {
        //Arrange
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();

        //Act
        apple1.dispose();
        apple2.eat();

        //Assert
        assertThat(apple1.getSlicesLeft()).isEqualTo(0);
        assertThat(apple2.getSlicesLeft()).isEqualTo(3);
    }
}
