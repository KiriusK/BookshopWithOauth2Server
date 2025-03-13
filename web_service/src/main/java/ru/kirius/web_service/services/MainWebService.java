package ru.kirius.web_service.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kirius.web_service.dto.BookDto;
import ru.kirius.web_service.dto.BookRequestDto;

import java.util.List;

@Service
public class MainWebService {

    private final RequestService requestService;

    public MainWebService(RequestService requestService) {
        this.requestService = requestService;
    }

    public List<BookDto> getAllBooks() {
        BookRequestDto requestBody = new BookRequestDto(true, true, "");
        List<BookDto> result = requestService.getBooks(requestBody);
        return result;
    }

    public List<BookDto> getDigitBooks() {
        BookRequestDto requestBody = new BookRequestDto(true, false, "");
        List<BookDto> result = requestService.getBooks(requestBody);
        return result;
    }

    public List<BookDto> getPaperBooks() {
        BookRequestDto requestBody = new BookRequestDto(false, true, "");
        List<BookDto> result = requestService.getBooks(requestBody);
        return result;
    }

    public List<BookDto> searchBooks(String searchStr) {
        BookRequestDto requestBody = new BookRequestDto(true, true, searchStr);
        List<BookDto> result = requestService.searchBooks(requestBody);
        return result;
    }
}
