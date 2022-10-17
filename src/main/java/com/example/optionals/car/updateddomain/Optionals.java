package com.example.optionals.car.updateddomain;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

// Example of a utility class implemented as an interface with default methods.
public interface Optionals {

    String unknown = "Unknown";

    default String getCarInsuranceName(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse(unknown);
    }

    default String getCarInsuranceName(Person person, int minAge) {
        Optional<Person> personWithAge = Optional.ofNullable(person)
                .filter(p -> p.getAge() >= minAge);

        if (personWithAge.isPresent()) {
            return getCarInsuranceName(personWithAge.get());
        } else {
            return unknown;
        }
    }

    default Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(car -> car.flatMap(Car::getInsurance))
                .map(insurance -> insurance.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(toSet());
    }

    default Insurance findCheapestInsurance(Person person, Car car) {
        // This method is NOT null safe...
        if (person.getAge() > 25 && !car.getName().equals("VW Golf")) {
            return new Insurance("El Cheapo");        // perform some database query ...
        } else {
            return new Insurance("Normal insurance"); // perform some other database query ...
        }
    }

    default Optional<Insurance> findCheapestInsuranceNullSafe(Person person, Car car) {
        Optional<Person> optPerson = Optional.ofNullable(person);
        Optional<Car> optCar = Optional.ofNullable(car);

//        if (optPerson.isPresent() && optCar.isPresent()) {
//            return Optional.of(findCheapestInsurance(optPerson.get(), optCar.get()));
//        } else {
//            return Optional.empty();
//        }

        return optPerson.flatMap(p -> optCar.map(c -> findCheapestInsurance(p, c)));
    }
}
