package ru.kirius.data_storage_service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kirius.data_storage_service.services.CoverService;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/cover")
public class CoverController {

    private final CoverService service;
    private final Logger logger;

    public CoverController(CoverService service) {
        this.service = service;
        logger = LoggerFactory.getLogger(CoverController.class);
    }

    @GetMapping()
    public ResponseEntity<Resource> downloadBookCover(@RequestParam(name = "coverUrl") String filename) throws IOException {
        if (Objects.equals(filename, "")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return service.getBookCover(filename);
    }

    @PostMapping("/add")
    public ResponseEntity<String> uploadBookCover(@RequestParam("file") MultipartFile file) {
        String filePath = service.saveBookCover(file);
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
