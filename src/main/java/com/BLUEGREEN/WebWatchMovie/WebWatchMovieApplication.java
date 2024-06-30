package com.BLUEGREEN.WebWatchMovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.BLUEGREEN.WebWatchMovie")
/*@SpringBootApplication*/
public class WebWatchMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebWatchMovieApplication.class, args);
	}

}
