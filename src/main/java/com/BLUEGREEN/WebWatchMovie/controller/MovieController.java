package com.BLUEGREEN.WebWatchMovie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class MovieController {

    @GetMapping("/")
    public String showMovies() {
        return "user/index";
    }
}
