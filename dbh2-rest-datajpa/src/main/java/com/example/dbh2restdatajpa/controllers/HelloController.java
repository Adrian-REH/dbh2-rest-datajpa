package com.example.dbh2restdatajpa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     *
     * @return Hello World
     */
    @GetMapping("/api/Hello")
    public String helloWorld( ){
        return """
                {"value": "Hello world"}
                """;
    }

}
