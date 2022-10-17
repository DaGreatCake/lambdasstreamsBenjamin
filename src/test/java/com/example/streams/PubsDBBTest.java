package com.example.streams;

import com.example.streams.pubsdb.data.MemoryPubsDb;
import com.example.streams.pubsdb.domain.model.*;
import com.sun.source.tree.Tree;
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
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        System.out.println(database.authors.stream()
                .collect(groupingBy(Author::getState, counting())));
    }

    @Test
    @DisplayName("Group authors living in the same state and having their firstname starting with the same character. Use multilevel grouping. Sort by state.")
    public void assignment8() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        TreeMap<String, Map<Object, List<Author>>> stateAuthors = database.authors.stream()
                .collect(groupingBy(Author::getState, TreeMap::new,
                        groupingBy(author -> author.getFirstname().charAt(0), TreeMap::new, toList())));

        stateAuthors.forEach((s, objectListMap) -> objectListMap.forEach((o, authors) -> {
            String authorString = authors.stream()
                                    .map(author -> author.getFirstname() + " " + author.getLastname())
                                    .collect(joining(", "));
            System.out.println(s + " - " + o.toString() + ": " + authorString);
        }));
    }

    @Test
    @DisplayName("Same as previous one, but now count the number of authors in each group.")
    public void assignment9() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        TreeMap<String, Map<Object, Long>> stateAuthors = database.authors.stream()
                .collect(groupingBy(Author::getState, TreeMap::new,
                        groupingBy(author -> author.getFirstname().charAt(0), TreeMap::new, counting())));

        stateAuthors.forEach((s, objectListMap) ->
                objectListMap.forEach((o, count) ->
                        System.out.println(s + " - " + o.toString() + ": " + count)));
    }

    @Test
    @DisplayName("Partition sales in two groups: those above quantity 20.00, and those below.")
    public void assignment10() {
        MemoryPubsDb database = MemoryPubsDb.getInstance();

        database.sales.stream()
                .collect(partitioningBy(sale -> sale.getQuantity().intValue() > 20))
                .forEach((above20, sales) -> System.out.println(above20 + ": " + sales.toString()));
    }
}
