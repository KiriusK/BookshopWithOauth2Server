package ru.kirius.web_service.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import ru.kirius.web_service.dto.BookDto;
import ru.kirius.web_service.dto.BookRequestDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

@Service
public class RequestService {

    @Value("${app.gatewayUri}")
    private String GATEWAYURI;

    public RequestService() {
    }

    public List<BookDto> getBooks(BookRequestDto requestDto) {
        RestClient client = RestClient.create();
        try {
            var resp = client
                    .post()
                    .uri(GATEWAYURI + "/books/get")
                    .headers(config -> config
                            .add("Content-type", "application/json")
                    )
                    .body(requestDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        throw new RuntimeException(response.getStatusCode() + response.getHeaders().toString());
                    })
                    .body(List.class)
            ;
            List<LinkedHashMap<String, ?>> mapList = (List<LinkedHashMap<String, ?>>) resp;
            List<BookDto> result = mapperFromLinkedHashMap(mapList);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Get all books exceptions", e);
        }
    }

    public List<BookDto> searchBooks(BookRequestDto requestDto) {
        RestClient client = RestClient.create();
        try {
            var resp = client
                    .post()
                    .uri(GATEWAYURI + "/books/search")
                    .headers(config -> config
                            .add("Content-type", "application/json")
                    )
                    .body(requestDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        throw new RuntimeException(response.getStatusCode() + response.getHeaders().toString());
                    })
                    .body(List.class)
                    ;
            List<LinkedHashMap<String, ?>> mapList = (List<LinkedHashMap<String, ?>>) resp;
            List<BookDto> result = mapperFromLinkedHashMap(mapList);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Get all books exceptions", e);
        }
    }

    private List<BookDto> mapperFromLinkedHashMap(List<LinkedHashMap<String, ?>> objectList) {
        if (objectList == null) {
            throw new RuntimeException("Null is returned from book db");
        }
        return objectList.stream().map(e -> new BookDto((boolean) e.get("isDigit")
                , (int) e.get("id")
                , (String) e.get("name")
                , (String) e.get("authorName")
                , (String) e.get("authorSurName")
                , (String) e.get("description")
                , (String) e.get("bookCoverUrl")
                , (String) e.get("pdfBookUrl")
                , (String) e.get("fb2BookUrl")
                , (int) e.get("quantity")
                , (double) e.get("price"))
        ).toList();
    }
}
