package ru.kirius.web_service.dto;

public record BookRequestDto(boolean isDigital
        , boolean isPaper
        , String searchReq
) {
}
