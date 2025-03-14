package ru.kirius.book_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.kirius.book_service.dto.BookDto;
import ru.kirius.book_service.entities.DigitalBook;
import ru.kirius.book_service.entities.PaperBook;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "isDigit", constant = "true")
    @Mapping(target = "quantity", constant = "-1")
    BookDto toBookDto (DigitalBook book);

    DigitalBook toDigitalBook(BookDto book);

    @Mapping(target = "isDigit", constant = "false")
    @Mapping(target = "pdfBookUrl", constant = "")
    @Mapping(target = "fb2BookUrl", constant = "")
    BookDto toBookDto(PaperBook book);

    PaperBook toPaperBook(BookDto book);

    List<BookDto> toBookDtoFromDigitalList(List<DigitalBook> bookList);

    List<BookDto> toBookDtoFromPaperList(List<PaperBook> bookList);

    List<DigitalBook> toDigitalBookList(List<BookDto> bookList);

    List<PaperBook> toPaperBookList(List<BookDto> bookList);
}
