package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.OriginMovie;
import com.BLUEGREEN.WebWatchMovie.service.OriginMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/originmovies")
public class OriginMovieAPIController {

    @Autowired
    private OriginMovieService originMovieService;

    @GetMapping
    public List<OriginMovie> getAllOriginMovies() {
        return originMovieService.getAllOriginMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OriginMovie> getOriginMovieById(@PathVariable int id) {
        OriginMovie originMovie = originMovieService.getOriginMovieById(id);
        if (originMovie != null) {
            return new ResponseEntity<>(originMovie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<OriginMovie> createOriginMovie(@RequestBody OriginMovie originMovie) {
        OriginMovie savedOriginMovie = originMovieService.saveOriginMovie(originMovie);
        return new ResponseEntity<>(savedOriginMovie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OriginMovie> updateOriginMovie(@PathVariable int id, @RequestBody OriginMovie originMovieDetails) {
        OriginMovie updatedOriginMovie = originMovieService.updateOriginMovie(id, originMovieDetails);
        if (updatedOriginMovie != null) {
            return new ResponseEntity<>(updatedOriginMovie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOriginMovie(@PathVariable int id) {
        originMovieService.deleteOriginMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
