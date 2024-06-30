package com.BLUEGREEN.WebWatchMovie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class test {
    @GetMapping("/")
    public String listProduct() {
        return "/user/index";
    }
}
