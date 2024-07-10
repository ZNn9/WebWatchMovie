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

import java.util.List;
import java.util.Set;

import java.util.Set;

public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/{idMovie}/episodes/{idEpisode}")
    public String getEpisodeDetails(@PathVariable("idMovie") int idMovie, @PathVariable("idEpisode") int idEpisode, Model model) {
        Episode episode = episodeService.getEpisodeById(idEpisode);
        List<Episode> episodes = episodeService.getEpisodesByMovieId(idMovie);

        model.addAttribute("episode", episode);
        model.addAttribute("episodes", episodes);
        return "user/anime-watching";
    }
}
