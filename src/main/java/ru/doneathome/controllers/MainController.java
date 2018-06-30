package ru.doneathome.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainController.class, args);

        log.info("Start app. Args: ", args);

        // при запуске *.jar можно указать порт [ --server.port=8090 ]
        while (true);
    }









}