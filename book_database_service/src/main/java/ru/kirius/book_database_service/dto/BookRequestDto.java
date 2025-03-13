package ru.kirius.book_database_service.dto;

public record BookRequestDto(boolean isDigital
        , boolean isPaper
        , String searchReq
) {
}
