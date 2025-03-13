package ru.kirius.web_service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kirius.web_service.dto.BookDto;
import ru.kirius.web_service.services.MainWebService;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainWebController {

    @Value("${app.bookshopName}")
    private String BOOKSHOPNAME;
    @Value("${app.gatewayUri}")
    private String GATEWAYURI;
    @Value("${app.webServiceUri}")
    private String WEBSERVICEURI;

    private final MainWebService service;
    private final Logger logger;

    public MainWebController(MainWebService service) {
        this.service = service;
        this.logger = LoggerFactory.getLogger(MainWebController.class);
    }

    @GetMapping
    public String mainPage(Model model) {
        List<BookDto> booksList = service.getAllBooks();
        model.addAttribute("bookshopName", BOOKSHOPNAME);
        model.addAttribute("gateway", GATEWAYURI);
        model.addAttribute("webService", WEBSERVICEURI);
        model.addAttribute("books", booksList);
        return "main";
    }

    @GetMapping("/digit")
    public String mainDigit(Model model) {
        List<BookDto> booksList = service.getDigitBooks();
        model.addAttribute("bookshopName", BOOKSHOPNAME);
        model.addAttribute("gateway", GATEWAYURI);
        model.addAttribute("webService", WEBSERVICEURI);
        model.addAttribute("books", booksList);
        return "main";
    }

    @GetMapping("/paper")
    public String mainPaper(Model model) {
        List<BookDto> booksList = service.getPaperBooks();
        model.addAttribute("gateway", GATEWAYURI);
        model.addAttribute("webService", WEBSERVICEURI);
        model.addAttribute("bookshopName", BOOKSHOPNAME);
        model.addAttribute("books", booksList);
        return "main";
    }

    @GetMapping("/buy")
    public String buyBook(@RequestParam("id") String id, @RequestParam("isDigit") boolean isDigit, Model model) {
        return "buyPage";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam("searchStr") String searchStr, Model model) {
        List<BookDto> booksList = service.searchBooks(searchStr);
        model.addAttribute("bookshopName", BOOKSHOPNAME);
        model.addAttribute("gateway", GATEWAYURI);
        model.addAttribute("webService", WEBSERVICEURI);
        model.addAttribute("books", booksList);
        return "main";
    }

    @ExceptionHandler(Exception.class)
    public String exHandler(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return "/errorpage";
    }

}
