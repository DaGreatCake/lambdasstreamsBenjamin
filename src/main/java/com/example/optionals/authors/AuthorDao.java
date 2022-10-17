package com.example.optionals.authors;

import com.example.streams.pubsdb.data.MemoryPubsDb;
import com.example.streams.pubsdb.domain.model.Author;

import java.util.Optional;
import java.util.function.Supplier;

// Lab 6.2, see AuthorDaoTest too
public class AuthorDao {

    private final MemoryPubsDb db = MemoryPubsDb.getInstance();

    Optional<Author> findAuthor(String id) {
        return db.authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    boolean hasWritten(String id, String title) {
        return db.titleauthors.stream()
                .anyMatch(titleAuthor -> titleAuthor.getTitle().getTitle().equals(title)
                        && titleAuthor.getAuthor().getId().equals(id));
    }
}
