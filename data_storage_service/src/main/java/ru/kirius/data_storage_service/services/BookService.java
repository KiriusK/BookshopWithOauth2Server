package ru.kirius.data_storage_service.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.kirius.data_storage_service.dto.GetBookDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;


public abstract class BookService {
    private final String folderPath;

    public BookService(String folderPath) {
        this.folderPath = folderPath;
    }

    public String saveBook(MultipartFile file) {
        String filename = null;
        if (!file.isEmpty()) {
            boolean createNameFl = true;
            while (createNameFl) {
                filename = UUID.randomUUID() + "." + getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
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

    public ResponseEntity<Resource> getBook(GetBookDto getBook) {
        String utfChars = stringToUtfHex(getBook.bookName() + "." + getFileExtension(getBook.fileName()));
        try {
            Resource fileRes = new FileSystemResource(Path.of(folderPath, getBook.fileName()));
            if (fileRes.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileRes.getFilename() + "\"; filename*=utf8\"" + utfChars + "\"");
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(fileRes.getFile().length())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(fileRes);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private String stringToUtfHex(String input) {
        StringBuilder result = new StringBuilder();
        for (char e : input.toCharArray()) {
            result.append("%").append(Integer.toHexString(e));
        }
        return result.toString();
    }

    private String getFileExtension(String fileName) {
        String[] fileNameArr = fileName.split("\\.");
        return fileNameArr[fileNameArr.length - 1];
    }
}
