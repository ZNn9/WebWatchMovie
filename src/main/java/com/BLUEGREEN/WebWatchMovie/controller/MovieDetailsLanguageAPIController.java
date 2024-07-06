package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.MovieDetailsLanguage;
import com.BLUEGREEN.WebWatchMovie.service.MovieDetailsLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie-details-languages")
public class MovieDetailsLanguageAPIController {

    @Autowired
    private MovieDetailsLanguageService service;

    @GetMapping
    public List<MovieDetailsLanguage> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsLanguage> getById(@PathVariable int id) {
        Optional<MovieDetailsLanguage> movieDetailsLanguage = service.findById(id);
        return movieDetailsLanguage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MovieDetailsLanguage create(@RequestBody MovieDetailsLanguage movieDetailsLanguage) {
        return service.save(movieDetailsLanguage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDetailsLanguage> update(@PathVariable int id, @RequestBody MovieDetailsLanguage updatedMovieDetailsLanguage) {
        Optional<MovieDetailsLanguage> optionalMovieDetailsLanguage = service.findById(id);

        if (optionalMovieDetailsLanguage.isPresent()) {
            MovieDetailsLanguage existingMovieDetailsLanguage = optionalMovieDetailsLanguage.get();
            existingMovieDetailsLanguage.setMovie(updatedMovieDetailsLanguage.getMovie());
            existingMovieDetailsLanguage.setLanguage(updatedMovieDetailsLanguage.getLanguage());
            service.save(existingMovieDetailsLanguage);
            return ResponseEntity.ok(existingMovieDetailsLanguage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
