package ru.kirius.book_service.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.kirius.book_service.entities.PaperBook;

import java.util.List;

/**
 * Repisitory for paperbook
 */

@Repository
public interface PaperBookRepository extends ListCrudRepository<PaperBook, Long> {
    List<PaperBook> findByAuthorSurNameContainsIgnoreCase(String authorSurName);

    List<PaperBook> findByNameContainsIgnoreCase(String name);
}