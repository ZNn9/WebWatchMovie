package com.BLUEGREEN.WebWatchMovie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class test {
    @GetMapping("/admin")
    public String adminForm() {
        return "/admin/index";
    }

    @GetMapping("/")
    public String listMovie() {
        return "/user/index";
    }
}
