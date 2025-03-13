package ru.kirius.data_storage_service.services;

import org.springframework.stereotype.Service;

@Service
public class BookPdfService extends BookService{

    public BookPdfService() {
        super("data_storage_service/pdfBooks");
    }
}
