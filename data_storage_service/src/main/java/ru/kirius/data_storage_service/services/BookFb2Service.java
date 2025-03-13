package ru.kirius.data_storage_service.services;

import org.springframework.stereotype.Service;

@Service
public class BookFb2Service extends BookService{
    public BookFb2Service() {
        super("data_storage_service/fb2Books");
    }
}
