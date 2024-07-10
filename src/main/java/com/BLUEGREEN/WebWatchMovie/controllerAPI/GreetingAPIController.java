package com.BLUEGREEN.WebWatchMovie.controllerAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GreetingAPIController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/greeting")
    public Map<String, String> greeting(@RequestParam(name = "lang", required = false, defaultValue = "en") String lang, Locale locale) {
        String greetingMessage = messageSource.getMessage("greeting.message", null, locale);
        Map<String, String> response = new HashMap<>();
        response.put("message", greetingMessage);
        return response;
    }

    @GetMapping("/change-language")
    public Map<String, String> changeLanguage(@RequestParam("lang") String lang) {
        // Normally you would handle changing language logic here
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Language changed to " + lang);
        return response;
    }
}
