package com.iceihehe.cm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {
    @GetMapping(path = "/")
    public String defaultController() {
        return "index";
    }
    @GetMapping(path ="/login")
    public String loginController() {
        return "login";
    }

}
