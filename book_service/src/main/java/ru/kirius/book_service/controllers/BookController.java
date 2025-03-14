package ru.kirius.book_service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirius.book_service.dto.BookDto;
import ru.kirius.book_service.dto.BookRequestDto;
import ru.kirius.book_service.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;
    private final Logger logger;

    public BookController(BookService service) {
        this.service = service;
        this.logger = LoggerFactory.getLogger(BookController.class);
    }

    @PostMapping("/get")
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestBody BookRequestDto request) {
        logger.info("all books method");
        List<BookDto> result = service.getAllBooks(request);
        if (result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<BookDto>> searchBooks(@RequestBody BookRequestDto request) {
        List<BookDto> result = service.searchBooks(request);
        if (result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exHandler(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>("False", HttpStatus.NOT_ACCEPTABLE);
    }



}
