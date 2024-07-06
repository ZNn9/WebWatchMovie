package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.*;
import com.BLUEGREEN.WebWatchMovie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserDetailsMovieFollowRepository userDetailsMovieFollowRepository;


    @Autowired
    private UserDetailsMovieRatingRepository userDetailsMovieRatingRepository;

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

    public List<Movie> recommendMovies(int userId) {
        // Lấy danh sách các phim mà user đã xem
        List<UserDetailsMovieFollow> userMovies = userDetailsMovieFollowRepository.findByUserId(userId);

        // Lấy danh sách các phim mà user đã đánh giá
        List<UserDetailsMovieRating> userRatings = userDetailsMovieRatingRepository.findByUserId(userId);

        Set<Tag> userTags = new HashSet<>();
        for (UserDetailsMovieFollow userMovie : userMovies) {
            List<TagMovieDetails> tagDetails = tagMovieDetailsRepository.findByMovieId(userMovie.getMovie().getIdMovie());
            for (TagMovieDetails tagDetail : tagDetails) {
                userTags.add(tagDetail.getTag());
            }
        }

        for (UserDetailsMovieRating userRating : userRatings) {
            List<TagMovieDetails> tagDetails = tagMovieDetailsRepository.findByMovieId(userRating.getMovie().getIdMovie());
            for (TagMovieDetails tagDetail : tagDetails) {
                userTags.add(tagDetail.getTag());
            }
        }

        Set<Movie> recommendedMovies = new HashSet<>();
        for (Tag tag : userTags) {
            List<TagMovieDetails> tagDetails = tagMovieDetailsRepository.findByTagId(tag.getIdTag());
            for (TagMovieDetails tagDetail : tagDetails) {
                recommendedMovies.add(tagDetail.getMovie());
            }
        }

        // Loại bỏ các phim mà user đã xem hoặc đã đánh giá
        Set<Movie> watchedMovies = userMovies.stream().map(UserDetailsMovieFollow::getMovie).collect(Collectors.toSet());
        watchedMovies.addAll(userRatings.stream().map(UserDetailsMovieRating::getMovie).collect(Collectors.toSet()));

        recommendedMovies.removeAll(watchedMovies);

        return new ArrayList<>(recommendedMovies);
    }

}
