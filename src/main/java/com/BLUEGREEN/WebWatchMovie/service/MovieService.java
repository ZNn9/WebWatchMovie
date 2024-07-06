package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.model.TagMovieDetails;
import com.BLUEGREEN.WebWatchMovie.repository.MovieRepository;
import com.BLUEGREEN.WebWatchMovie.repository.TagMovieDetailsRepository;
import com.BLUEGREEN.WebWatchMovie.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMovieDetailsRepository tagMovieDetailsRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }



    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    public Movie updateMovie(int id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie.setName(movieDetails.getName());
            movie.setDescription(movieDetails.getDescription());

            // Update other fields as needed
            return movieRepository.save(movie);
        }
        return null;
    }

    public List<String> searchMovies(String query) {
        List<Movie> movies = movieRepository.findByNameContainingIgnoreCase(query);
        return movies.stream().map(Movie::getName).collect(Collectors.toList());
    }

}
