package ru.kirius.admin_web_service.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${app.gatewayUri}")
    private String GATEWAY;
    @Value("${app.adminServiceUri}")
    private String WEBSERVICE;

    @Autowired
    private OAuth2AuthorizedClientService authService;
    private final Logger logger;

    public MainController() {
        this.logger = LoggerFactory.getLogger(MainController.class);
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("webService", WEBSERVICE);
        return "addBook";
    }

    @PostMapping("/save")
    public String saveBook(@RequestBody String bodyStr, HttpServletRequest request, Principal principal) {
        Iterator<String> headers = request.getHeaderNames().asIterator();
        while (headers.hasNext()) {
            logger.info(headers.next());
        }
        logger.info("----------------");
        headers = request.getHeaders("content-length").asIterator();
        while (headers.hasNext()) {
            logger.info(headers.next());
        }
        try {
            logger.info("There is a body");
            String body = request.getReader().lines().collect(Collectors.joining(" "));
            logger.info(body.isEmpty() ? "true" : "false");
            logger.info(body);
            logger.info(bodyStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String accessToken = authService
                .loadAuthorizedClient("adminService", principal.getName())
                .getAccessToken().getTokenValue();
        logger.info(accessToken);
        return "success";
    }

    @ExceptionHandler(Exception.class)
    public String exHandler(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return "/errorpage";
    }
}
