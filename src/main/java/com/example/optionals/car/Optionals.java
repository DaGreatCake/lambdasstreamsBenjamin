package com.example.optionals.car;

import java.util.Objects;
import java.util.Optional;

public class Optionals {

    public static String getCarInsuranceNameNotSafe(Person person) {
        return person.getCar().getInsurance().getName();
    }

    public static String getCarInsuranceNameNested(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    public static String getCarInsuranceNameMultipleExitPoints(Person person) {
        if (person == null) return "Unknown";
        Car car = person.getCar();
        if (car == null) return "Unknown";
        Insurance insurance = car.getInsurance();
        if (insurance == null) return "Unknown";
        return insurance.getName();
    }

    public static String getCarInsuranceName(Person person) {
        return Optional.ofNullable(person)
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
