package com.BLUEGREEN.WebWatchMovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
public class GreetingController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/greeting")
    public String greeting(Model model, Locale locale) {
        String greetingMessage = messageSource.getMessage("greeting.message", null, locale);
        model.addAttribute("message", greetingMessage);
        return "greeting-template"; // Thymeleaf template name
    }

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam("lang") String lang, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("lang", lang);
        return "redirect:/greeting"; // Chuyển hướng về trang greeting sau khi thay đổi ngôn ngữ
    }
}
