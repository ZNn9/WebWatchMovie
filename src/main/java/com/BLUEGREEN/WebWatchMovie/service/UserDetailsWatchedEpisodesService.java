package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.UserDetailsWatchedEpisodes;
import com.BLUEGREEN.WebWatchMovie.repository.UserDetailsWatchedEpisodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsWatchedEpisodesService {

    @Autowired
    private UserDetailsWatchedEpisodesRepository userDetailsWatchedEpisodesRepository;

    public List<UserDetailsWatchedEpisodes> findAll() {
        return userDetailsWatchedEpisodesRepository.findAll();
    }

    public Optional<UserDetailsWatchedEpisodes> findById(int id) {
        return userDetailsWatchedEpisodesRepository.findById(id);
    }

    public UserDetailsWatchedEpisodes save(UserDetailsWatchedEpisodes userDetailsWatchedEpisodes) {
        return userDetailsWatchedEpisodesRepository.save(userDetailsWatchedEpisodes);
    }

    public void deleteById(int id) {
        userDetailsWatchedEpisodesRepository.deleteById(id);
    }
}
