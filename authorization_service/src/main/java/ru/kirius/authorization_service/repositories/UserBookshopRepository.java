package ru.kirius.authorization_service.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kirius.authorization_service.entities.UserBookshop;

import java.util.Optional;

@Repository
public interface UserBookshopRepository extends ListCrudRepository<UserBookshop, Long> {
    Optional<UserBookshop> findByUsername(@NonNull String username);

    void deleteByUsername(@NonNull String username);
}