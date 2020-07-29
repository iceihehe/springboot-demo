package com.iceihehe.cm.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {

    @RequestMapping(path = "/welcome")
    public String welcome() {

        return "Welcome!";
    }
}
