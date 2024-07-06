package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.MovieActorDetails;
import com.BLUEGREEN.WebWatchMovie.service.MovieActorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie-actor-details")
public class MovieActorDetailsAPIController {

    @Autowired
    private MovieActorDetailsService movieActorDetailsService;

    @GetMapping
    public List<MovieActorDetails> getAllMovieActorDetails() {
        return movieActorDetailsService.getAllMovieActorDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieActorDetails> getMovieActorDetailsById(@PathVariable int id) {
        Optional<MovieActorDetails> movieActorDetails = movieActorDetailsService.getMovieActorDetailsById(id);
        return movieActorDetails.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MovieActorDetails> createMovieActorDetails(@RequestBody MovieActorDetails movieActorDetails) {
        MovieActorDetails savedMovieActorDetails = movieActorDetailsService.saveMovieActorDetails(movieActorDetails);
        return new ResponseEntity<>(savedMovieActorDetails, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieActorDetails> updateMovieActorDetails(@PathVariable int id, @RequestBody MovieActorDetails movieActorDetailsDetails) {
        MovieActorDetails updatedMovieActorDetails = movieActorDetailsService.updateMovieActorDetails(id, movieActorDetailsDetails);
        if (updatedMovieActorDetails != null) {
            return new ResponseEntity<>(updatedMovieActorDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieActorDetails(@PathVariable int id) {
        movieActorDetailsService.deleteMovieActorDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
