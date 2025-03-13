package ru.kirius.book_database_service.dto;

public record BookDto(boolean isDigit
        , long id
        , String name
        , String authorName
        , String authorSurName
        , String description
        , String bookCoverUrl
        , String pdfBookUrl
        , String fb2BookUrl
        , int quantity
        , double price
) {
}
