package com.example.streams;

import com.example.streams.pubsdb.data.MemoryPubsDb;
import com.example.streams.pubsdb.domain.model.Author;
import com.example.streams.pubsdb.domain.model.Title;
import com.example.streams.pubsdb.domain.model.TitleAuthor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

public class PubsDBATest {
    @Test
    @DisplayName("Print all Author objects and all Title objects.")
    public void assignment1() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        database.authors.forEach(System.out::println);
        database.titles.forEach(System.out::println);
    }

    @Test
    @DisplayName("Print all Authors living in CA.")
    public void assignment2() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        database.authors.stream()
                .filter(author -> author.getState().equals("CA"))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Print all the full names, (auFname + auLname) sort them alphabetically.")
    public void assignment3() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        database.authors.stream()
                .map(author -> author.getFirstname() + " " + author.getLastname())
                .sorted(naturalOrder())
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Count the number of titles.")
    public void assignment4() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        System.out.println(database.titles.stream()
                .count());
    }

    @Test
    @DisplayName("Print the values of the advance property.")
    public void assignment5() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        database.titles.stream()
                .map(Title::getAdvance)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Does some property advance contain a null?")
    public void assignment6() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        System.out.println(database.titles.stream()
                .map(Title::getAdvance)
                .anyMatch(Objects::isNull));
    }

    @Test
    @DisplayName("Print all business books.")
    public void assignment7() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        database.titles.stream()
                .filter(title -> title.getType().equals("business"))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Count the number of authors that wrote business books.")
    public void assignment8() {
        System.out.println(getBusinessAuthorsFromTitle().count());
    }

    @Test
    @DisplayName("Print all the authors that wrote business books starting from the Title stream.")
    public void assignment9() {
        getBusinessAuthorsFromTitle()
                .forEach(author -> System.out.println(author.getFirstname() + " " + author.getLastname()));
    }

    @Test
    @DisplayName("Idem as former question but now starting from the Author stream.")
    public void assignment10() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        database.authors.stream()
                .map(Author::getTitleauthors)
                .flatMap(Collection::stream)
                .filter(titleAuthor -> titleAuthor.getTitle().getType().equals("business"))
                .map(TitleAuthor::getAuthor)
                .distinct()
                .forEach(author -> System.out.println(author.getFirstname() + " " + author.getLastname()));
    }

    @Test
    @DisplayName("Print all the authors that did not write business books.")
    public void assignment11() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        database.authors.stream()
                .filter(author -> getBusinessAuthorsFromTitle()
                        .noneMatch(author1 -> author1.getId().equals(author.getId())))
                .forEach(author -> System.out.println(author.getFirstname() + " " + author.getLastname()));
    }

    private Stream<Author> getBusinessAuthorsFromTitle() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        return database.titles.stream()
                .filter(title -> title.getType().equals("business"))
                .map(Title::getTitleauthors)
                .flatMap(Collection::stream)
                .map(TitleAuthor::getAuthor)
                .distinct();
    }
}
