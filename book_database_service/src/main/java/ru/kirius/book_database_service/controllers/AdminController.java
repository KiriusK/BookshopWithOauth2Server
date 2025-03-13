package ru.kirius.book_database_service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirius.book_database_service.dto.BookDto;
import ru.kirius.book_database_service.services.BookService;


@RestController()
@RequestMapping("/admin")
public class AdminController {

    private final BookService service;
    private final Logger logger;

    public AdminController(BookService service) {
        this.service = service;
        this.logger = LoggerFactory.getLogger(AdminController.class);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookDto request) {
        try {
            service.addBook(request);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody BookDto request) {
        if(service.updateBook(request)) {
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestBody BookDto request) {
        if(service.deleteBook(request)) {
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exHandler(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>("False", HttpStatus.NOT_ACCEPTABLE);
    }


}



