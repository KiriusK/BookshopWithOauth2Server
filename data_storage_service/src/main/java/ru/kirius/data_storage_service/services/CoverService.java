package ru.kirius.data_storage_service.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Service
public class CoverService {

    private final String folderPath;

    public CoverService() {
        this.folderPath = "data_storage_service/covers";
    }

    public String saveBookCover(MultipartFile file) {
        String filename = null;
        if (!file.isEmpty()) {
            boolean createNameFl = true;
            while(createNameFl) {
                String[] nameArr = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
                filename = UUID.randomUUID() + "." + nameArr[nameArr.length-1];
                Path savePath = Path.of(folderPath, filename);
                if (Files.notExists(savePath)) {
                    try {
                        file.transferTo(savePath);
                        createNameFl = false;
                    } catch (IOException | NullPointerException ex) {
                        System.out.println("exception " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
        return filename;
    }

    public ResponseEntity<Resource> getBookCover(String filename) {
        Resource fileRes = new FileSystemResource(Path.of(folderPath, filename));
        if (fileRes.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileRes.getFilename() + "\"");
            try {
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(fileRes.getFile().length())
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(fileRes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public boolean deleteBook(String fileName) {
        Path file = Path.of(folderPath, fileName);
        if (Files.exists(file)) {
            try {
                Files.delete(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
}
