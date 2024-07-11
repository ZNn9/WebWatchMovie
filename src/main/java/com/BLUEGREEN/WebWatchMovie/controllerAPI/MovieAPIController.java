package com.BLUEGREEN.WebWatchMovie.controllerAPI;

import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.model.Episode;
import com.BLUEGREEN.WebWatchMovie.repository.EpisodeRepository;
import com.BLUEGREEN.WebWatchMovie.repository.MovieRepository;
import com.BLUEGREEN.WebWatchMovie.service.EpisodeService;
import com.BLUEGREEN.WebWatchMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieAPIController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieService.getMovieById(id);
    }


    @GetMapping("/{id}/episodes")
    public ResponseEntity<List<Episode>> getEpisodesByMovieId(@PathVariable int id) {
        List<Episode> episodes = episodeService.getEpisodesByMovieId(id);
        return ResponseEntity.ok(episodes);
    }


    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.saveMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }



    @PutMapping("/{id}/image")
    public ResponseEntity<?> updateMovieImage(@PathVariable int id, @RequestParam("image") MultipartFile imageFile) {
        try {
            movieService.updateImage(id, imageFile);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<?> deleteMovieImage(@PathVariable int id) {
        try {
            movieService.deleteImage(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete image: " + e.getMessage());
        }
    }

}