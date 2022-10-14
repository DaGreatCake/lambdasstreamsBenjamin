package com.example.streams;

import com.example.streams.pubsdb.data.MemoryPubsDb;
import com.example.streams.pubsdb.domain.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class PubsDBBTest {
    @Test
    @DisplayName("Collect all the authors who have published for \"New Moon Books\" in a new List and count the number of elements in that list. This should be 5.")
    public void assignment1() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        Optional<Publisher> publisherNewMoon = database.publishers.stream()
                .filter(publisher -> publisher.getPubName().equals("New Moon Books"))
                .findFirst();

        List<Author> authors = null;

        if (publisherNewMoon.isPresent()) {
            authors = publisherNewMoon.get().getTitles().stream()
                    .map(Title::getTitleauthors)
                    .flatMap(Collection::stream)
                    .map(TitleAuthor::getAuthor)
                    .collect(Collectors.toUnmodifiableList());
        }

        if (authors != null) {
            System.out.println(authors.stream()
                    .count());
        }
    }

    @Test
    @DisplayName("For each discount, find the store(s) which issued that discount.")
    public void assignment2() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        database.discounts.stream()
                .filter(discount -> discount.getStore() != null)
                .forEach(discount -> System.out.println(discount.getDiscounttype()
                        + ": " + discount.getStore().getStoreName()));
    }

    @Test
    @DisplayName("Find the author(s) with the most sales.")
    public void assignment3() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        int mostSales = database.authors.stream()
                .map(Author::getTitleauthors)
                .flatMap(Collection::stream)
                .map(TitleAuthor::getTitle)
                .map(title -> title.getSales().size())
                .max(Integer::compare)
                .orElse(-1);

        database.authors.stream()
                .filter(author -> author.getTitleauthors().stream()
                        .map(TitleAuthor::getTitle)
                        .map(title -> title.getSales().size())
                        .max(Integer::compare)
                        .orElse(-1) == mostSales)
                .forEach(author -> System.out.println(author.getFirstname() + " " + author.getLastname()));
    }

    @Test
    @DisplayName("Find the author(s) with the lowest royalty percentage using minBy.")
    public void assignment4() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        BigDecimal lowestRoyaltyper = database.authors.stream()
                .map(Author::getTitleauthors)
                .flatMap(Collection::stream)
                .map(TitleAuthor::getRoyaltyper)
                .min(BigDecimal::compareTo)
                .orElse(new BigDecimal(-1));

        database.authors.stream()
                .filter(author -> author.getTitleauthors().stream()
                        .map(TitleAuthor::getRoyaltyper)
                        .min(BigDecimal::compareTo)
                        .orElse(new BigDecimal(-1)).equals(lowestRoyaltyper))
                .forEach(author -> System.out.println(author.getFirstname() + " " + author.getLastname()));
    }

    @Test
    @DisplayName("Print the summary of sales using summarizingInt on quantity.")
    public void assignment5() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        IntSummaryStatistics intSummaryStatistics =  database.sales.stream()
                .map(Sale::getQuantity)
                .collect(summarizingInt(BigDecimal::intValue));

        System.out.println(intSummaryStatistics);
    }

    @Test
    @DisplayName("Find the total number of sales using summingInt (should be 493).")
    public void assignment6() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        System.out.println(database.sales.stream()
                .map(Sale::getQuantity)
                .collect(summingInt(BigDecimal::intValue)));
    }

    @Test
    @DisplayName("Count the number of authors for each state (hint: use groupingBy).")
    public void assignment7() {

    }
}
