package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Episode;
import com.BLUEGREEN.WebWatchMovie.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/")
    public String showMovies() {
        return "/user/index";
    }

    @GetMapping("/movies/{idMovie}/{idEpisode}")
    public String watchEpisode(@PathVariable int idMovie, @PathVariable int idEpisode, Model model) {
        // Kiểm tra xem episode có thuộc về movie có id là idMovie không
        Episode episode = episodeService.getEpisodeByIdAndMovieId(idEpisode, idMovie);

        if (episode != null) {
            List<Episode> episodes = episodeService.getEpisodesByMovieId(idMovie);
            model.addAttribute("episode", episode);
            model.addAttribute("episodes", episodes);
            return "/user/anime-watching";
        } else {
            // Xử lý trường hợp không tìm thấy episode thuộc về movie đã cho
            return "error"; // hoặc chuyển hướng đến trang lỗi thích hợp
        }
    }
}
