package ru.kirius.book_database_service.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.kirius.book_database_service.entities.DigitalBook;

import java.util.List;

/**
 * Repository for digital book
 */

@Repository
public interface DigitalBookRepository extends ListCrudRepository<DigitalBook, Long> {

    List<DigitalBook> findByAuthorSurNameContainsIgnoreCase(String authorSurName);

    List<DigitalBook> findByNameContainsIgnoreCase(String name);
}