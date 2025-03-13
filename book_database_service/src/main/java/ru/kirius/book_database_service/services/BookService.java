package ru.kirius.book_database_service.services;

import org.springframework.stereotype.Service;
import ru.kirius.book_database_service.dto.BookDto;
import ru.kirius.book_database_service.dto.BookRequestDto;
import ru.kirius.book_database_service.entities.DigitalBook;
import ru.kirius.book_database_service.entities.PaperBook;
import ru.kirius.book_database_service.mappers.BookMapper;
import ru.kirius.book_database_service.repositories.DigitalBookRepository;
import ru.kirius.book_database_service.repositories.PaperBookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final DigitalBookRepository digitalRepo;
    private final PaperBookRepository paperRepo;
    private final BookMapper bookMapper;

    public BookService(DigitalBookRepository digitalRepo, PaperBookRepository paperRepo, BookMapper bookMapper) {
        this.digitalRepo = digitalRepo;
        this.paperRepo = paperRepo;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks(BookRequestDto request) {
        List<BookDto> result = new ArrayList<>();
        if (request.isDigital()) {
            result.addAll(bookMapper.toBookDtoFromDigitalList(digitalRepo.findAll()));
        }
        if (request.isPaper()) {
            result.addAll(bookMapper.toBookDtoFromPaperList(paperRepo.findAll()));
        }
        return result;
    }

    public List<BookDto> searchByAuthor(BookRequestDto request) {
        List<BookDto> result = new ArrayList<>();
        if (request.isDigital()) {
            result.addAll(bookMapper.toBookDtoFromDigitalList(digitalRepo.findByAuthorSurNameContainsIgnoreCase(request.searchReq())));
        }
        if (request.isPaper()) {
            result.addAll(bookMapper.toBookDtoFromPaperList(paperRepo.findByAuthorSurNameContainsIgnoreCase(request.searchReq())));
        }
        return result;
    }

    public List<BookDto> searchByName(BookRequestDto request) {
        List<BookDto> result = new ArrayList<>();
        if (request.isDigital()) {
            result.addAll(bookMapper.toBookDtoFromDigitalList(digitalRepo.findByNameContainsIgnoreCase(request.searchReq())));
        }
        if (request.isPaper()) {
            result.addAll(bookMapper.toBookDtoFromPaperList(paperRepo.findByNameContainsIgnoreCase(request.searchReq())));
        }
        return result;
    }

    public List<BookDto> searchBooks(BookRequestDto request) {
        List<BookDto> result = new ArrayList<>();
        result.addAll(searchByName(request));
        result.addAll(searchByAuthor(request));
        return result;
    }

    public void addBook(BookDto request) {
        if (request.isDigit()) {
            digitalRepo.save(bookMapper.toDigitalBook(request));
        } else {
            paperRepo.save(bookMapper.toPaperBook(request));
        }
    }

    public boolean updateBook(BookDto request) {
        if (request.isDigit()) {
            DigitalBook book = digitalRepo.findById(request.id()).orElse(null);
            if (book != null) {
                book.setName(request.name());
                book.setAuthorName(request.authorName());
                book.setAuthorSurName(request.authorSurName());
                book.setBookCoverUrl(request.bookCoverUrl());
                book.setPrice(request.price());
                digitalRepo.save(book);
                return true;
            }
        } else {
            PaperBook book = paperRepo.findById(request.id()).orElse(null);
            if (book != null) {
                book.setName(request.name());
                book.setAuthorName(request.authorName());
                book.setAuthorSurName(request.authorSurName());
                book.setBookCoverUrl(request.bookCoverUrl());
                book.setQuantity(request.quantity());
                book.setPrice(request.price());
                paperRepo.save(book);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(BookDto request) {
        if (request.isDigit()) {
            DigitalBook book = digitalRepo.findById(request.id()).orElse(null);
            if (book != null) {
                digitalRepo.delete(book);
                return true;
            }
        } else {
            PaperBook book = paperRepo.findById(request.id()).orElse(null);
            if (book != null) {
                paperRepo.delete(book);
                return true;
            }
        }
        return false;
    }


}
