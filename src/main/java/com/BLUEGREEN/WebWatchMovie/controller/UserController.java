package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Episode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.BLUEGREEN.WebWatchMovie.service.EpisodeService;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

    @GetMapping("/register")
    public String register(){
        return "/user/register";
    }

    @GetMapping("/anime-detail")
    public String moviedetail(){
        return "/user/anime-details";
    }

    /*@GetMapping("/anime-watching")
    public String moviewatching(){
        return "/user/anime-watching";
    }*/


    /*@GetMapping("/anime-watching/{id}")
    public String watchEpisode(@PathVariable int id, Model model) {
        Episode episode = episodeService.getEpisodeById(id);
        model.addAttribute("episode", episode);
        return "user/anime-watching";
    }*/


    /*@GetMapping("/anime-watching")
    public String watchEpisode(@PathVariable int id, Model model) {
        Episode episode = episodeService.getEpisodeById(id);
        model.addAttribute("episode", episode);
        model.addAttribute("videoPath", "/movies/" + episode.getEpisodeAdress());
        return "user/anime-watching";
    }*/

    /*@GetMapping("/anime-watching/{idMovie}/{idEpisode}")
    public String watchEpisode(@PathVariable int idMovie, @PathVariable int idEpisode, Model model) {
        // Kiểm tra xem episode có thuộc về movie có id là idMovie không
        Episode episode = episodeService.getEpisodeByIdAndMovieId(idEpisode, idMovie);

        if (episode != null) {
            model.addAttribute("episode", episode);
            return "/user/anime-watching";
        } else {
            // Xử lý trường hợp không tìm thấy episode thuộc về movie đã cho
            return "error"; // hoặc chuyển hướng đến trang lỗi thích hợp
        }
    }*/

    /*@GetMapping("/anime-watching/{idMovie}/{idEpisode}")
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
    }*/
}
