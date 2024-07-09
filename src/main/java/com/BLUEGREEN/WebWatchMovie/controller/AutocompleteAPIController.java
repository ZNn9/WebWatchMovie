package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/autocomplete")
public class AutocompleteAPIController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<String> autocomplete(@RequestParam("query") String query) {
        return movieService.searchMovies(query);
    }

    @GetMapping("/recommend")
    public List<Movie> recommendMovies(@RequestParam(value = "userId", required = false) int userId) {
        return movieService.recommendMovies(userId);
    }
}
