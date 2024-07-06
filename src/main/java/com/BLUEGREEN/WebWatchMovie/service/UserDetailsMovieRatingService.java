package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.UserDetailsMovieRating;
import com.BLUEGREEN.WebWatchMovie.repository.UserDetailsMovieRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsMovieRatingService {

    @Autowired
    private UserDetailsMovieRatingRepository userDetailsMovieRatingRepository;

    public List<UserDetailsMovieRating> findAll() {
        return userDetailsMovieRatingRepository.findAll();
    }

    public Optional<UserDetailsMovieRating> findById(int id) {
        return userDetailsMovieRatingRepository.findById(id);
    }

    public UserDetailsMovieRating save(UserDetailsMovieRating userDetailsMovieRating) {
        return userDetailsMovieRatingRepository.save(userDetailsMovieRating);
    }

    public void deleteById(int id) {
        userDetailsMovieRatingRepository.deleteById(id);
    }
}
