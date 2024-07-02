package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.UserDetailsMovieFollow;
import com.BLUEGREEN.WebWatchMovie.repository.UserDetailsMovieFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsMovieFollowService {

    @Autowired
    private UserDetailsMovieFollowRepository userDetailsMovieFollowRepository;

    public List<UserDetailsMovieFollow> findAll() {
        return userDetailsMovieFollowRepository.findAll();
    }

    public Optional<UserDetailsMovieFollow> findById(int id) {
        return userDetailsMovieFollowRepository.findById(id);
    }

    public UserDetailsMovieFollow save(UserDetailsMovieFollow userDetailsMovieFollow) {
        return userDetailsMovieFollowRepository.save(userDetailsMovieFollow);
    }

    public void deleteById(int id) {
        userDetailsMovieFollowRepository.deleteById(id);
    }
}
