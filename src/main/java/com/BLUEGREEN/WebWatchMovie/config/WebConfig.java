package com.BLUEGREEN.WebWatchMovie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/movies/**")
                .addResourceLocations("file:M:\\Work_Place\\School\\IT\\LapTrinhUngDungBangJava\\NguyenDaiHiep_LeHongAnh_NguyenGiaBao_VoLeAnhTien\\WebWatchMovie\\src\\movies\\");
//                .addResourceLocations("file:E:\\LHA_2180606387\\LTUD_JAVA\\1_DoAn\\WebWatchMovie\\src\\movies\\");
    }
}

