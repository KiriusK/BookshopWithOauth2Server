package ru.kirius.book_service.dto;

public record BookRequestDto(boolean isDigital
        , boolean isPaper
        , String searchReq
) {
}
