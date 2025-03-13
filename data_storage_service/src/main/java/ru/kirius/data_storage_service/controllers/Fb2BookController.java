package ru.kirius.data_storage_service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kirius.data_storage_service.dto.GetBookDto;
import ru.kirius.data_storage_service.services.BookFb2Service;

@RestController
@RequestMapping("/fb2")
public class Fb2BookController {

    private final BookFb2Service service;
    private final Logger logger;

    public Fb2BookController(BookFb2Service service) {
        this.service = service;
        this.logger = LoggerFactory.getLogger(Fb2BookController.class);
    }

    @PostMapping()
    public ResponseEntity<Resource> downloadFb2Book(@RequestBody GetBookDto getBook) {
        return service.getBook(getBook);
    }

    @PostMapping("/add")
    public ResponseEntity<String> uploadFb2Book(@RequestParam("book") MultipartFile book) {
        String filePath = service.saveBook(book);
        return new ResponseEntity<>(filePath, HttpStatus.OK);
    }

    @PostMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFb2Book(@PathVariable String fileName) {
        if(service.deleteBook(fileName)) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("false", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> exceptionHandler(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
