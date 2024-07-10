package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Episode;
import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.service.EpisodeService;
import com.BLUEGREEN.WebWatchMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/{idMovie}/episodes/{idEpisode}")
    public String getEpisodeDetails(@PathVariable("idMovie") int idMovie, @PathVariable("idEpisode") int idEpisode, Model model) {
        Episode episode = episodeService.getEpisodeById(idEpisode);
        Movie movie = movieService.getMovieById(idMovie);
        Set<Episode> episodes = movie.getEpisodes();

        model.addAttribute("episode", episode);
        model.addAttribute("episodes", episodes);
        return "anime-watch";
    }
}
