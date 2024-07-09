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

    public List<Movie> recommendMovies(Integer userId) {
        Set<Tag> userTags = new HashSet<>();
        Set<Movie> recommendedMovies = new HashSet<>();
        Set<Movie> watchedMovies = new HashSet<>();

        if (userId != null) {
            List<UserDetailsMovieFollow> userMovies = userDetailsMovieFollowRepository.findByUserId(userId);
            List<UserDetailsMovieRating> userRatings = userDetailsMovieRatingRepository.findByUserId(userId);

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

            /*watchedMovies.addAll(userMovies.stream().map(UserDetailsMovieFollow::getMovie).collect(Collectors.toSet()));
            watchedMovies.addAll(userRatings.stream().map(UserDetailsMovieRating::getMovie).collect(Collectors.toSet()));*/
        }

        if (userTags.isEmpty()) {
            // If user has no tags or no account, recommend based on popular movies or ratings
            List<UserDetailsMovieRating> popularRatings = userDetailsMovieRatingRepository.findTop10ByOrderByRatingPointDesc();
            recommendedMovies.addAll(popularRatings.stream().map(UserDetailsMovieRating::getMovie).collect(Collectors.toSet()));
        } else {
            for (Tag tag : userTags) {
                List<TagMovieDetails> tagDetails = tagMovieDetailsRepository.findByTagId(tag.getIdTag());
                for (TagMovieDetails tagDetail : tagDetails) {
                    recommendedMovies.add(tagDetail.getMovie());
                }
            }
        }

        recommendedMovies.removeAll(watchedMovies);

        return new ArrayList<>(recommendedMovies);
    }
}
