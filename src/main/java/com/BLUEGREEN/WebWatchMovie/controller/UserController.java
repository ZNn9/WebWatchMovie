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

}
