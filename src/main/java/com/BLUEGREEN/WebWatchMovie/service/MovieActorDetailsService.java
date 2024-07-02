package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.MovieActorDetails;
import com.BLUEGREEN.WebWatchMovie.repository.MovieActorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieActorDetailsService {

    @Autowired
    private MovieActorDetailsRepository movieActorDetailsRepository;

    public List<MovieActorDetails> getAllMovieActorDetails() {
        return movieActorDetailsRepository.findAll();
    }

    public Optional<MovieActorDetails> getMovieActorDetailsById(int id) {
        return movieActorDetailsRepository.findById(id);
    }

    public MovieActorDetails saveMovieActorDetails(MovieActorDetails movieActorDetails) {
        return movieActorDetailsRepository.save(movieActorDetails);
    }

    public void deleteMovieActorDetails(int id) {
        movieActorDetailsRepository.deleteById(id);
    }

    public MovieActorDetails updateMovieActorDetails(int id, MovieActorDetails movieActorDetailsDetails) {
        Optional<MovieActorDetails> optionalMovieActorDetails = movieActorDetailsRepository.findById(id);
        if (optionalMovieActorDetails.isPresent()) {
            MovieActorDetails movieActorDetails = optionalMovieActorDetails.get();
            movieActorDetails.setMovie(movieActorDetailsDetails.getMovie());
            movieActorDetails.setActor(movieActorDetailsDetails.getActor());
            return movieActorDetailsRepository.save(movieActorDetails);
        } else {
            // Handle the case where the movie actor details is not found
            return null;
        }
    }
}
