package com.BLUEGREEN.WebWatchMovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class GreetingController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/greeting")
    public String greeting(Model model, Locale locale) {
        String greetingMessage = messageSource.getMessage("greeting.message", null, locale);
        model.addAttribute("message", greetingMessage);
        return "greeting-template"; // Tên của template Thymeleaf
    }
}

