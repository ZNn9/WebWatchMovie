package com.BLUEGREEN.WebWatchMovie.controller;

<<<<<<< Updated upstream
=======
import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.service.MovieService;
import com.BLUEGREEN.WebWatchMovie.model.Episode;
import com.BLUEGREEN.WebWatchMovie.service.EpisodeService;
>>>>>>> Stashed changes
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MovieController {

<<<<<<< Updated upstream
=======
    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private MovieService movieService;

>>>>>>> Stashed changes
    @GetMapping("/")
    public String showMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "user/index";
    }
}
