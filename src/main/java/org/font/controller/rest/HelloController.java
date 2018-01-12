package org.font.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String index() {
        System.out.println("HelloController.index");
        return "Hello World";
    }
}

