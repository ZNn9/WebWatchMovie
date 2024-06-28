package com.BLUEGREEN.WebWatchMovie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LanguageController {

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam("lang") String lang, RedirectAttributes redirectAttributes) {
        // Lưu ngôn ngữ được chọn vào session (đã được cấu hình bằng LocaleChangeInterceptor)
        redirectAttributes.addAttribute("lang", lang); // Thêm ngôn ngữ vào redirect attribute

        return "redirect:/greeting"; // Chuyển hướng về trang greeting sau khi thay đổi ngôn ngữ
    }
}