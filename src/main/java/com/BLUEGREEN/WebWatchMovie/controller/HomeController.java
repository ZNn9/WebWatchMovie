package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String home(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "index"; // Assuming index.html template exists in templates folder
    }

    @GetMapping("/movie/{id}")
    public String viewMovie(@PathVariable int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            model.addAttribute("movie", movie);
            return "movie-details"; // Assuming movie-details.html template exists in templates folder
        } else {
            return "error"; // Handle not found case
        }
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam("query") String query, Model model) {
        List<String> movies = movieService.searchMovies(query);
        model.addAttribute("movies", movies);
        return "search-results"; // Assuming search-results.html template exists in templates folder
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Return the name of the login HTML file in the templates folder
    }
}
