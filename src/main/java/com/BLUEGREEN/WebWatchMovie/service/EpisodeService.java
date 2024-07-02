package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Episode;
import com.BLUEGREEN.WebWatchMovie.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpisodeService {

    @Autowired
    private EpisodeRepository episodeRepository;

    public List<Episode> findAll() {
        return episodeRepository.findAll();
    }

    public Optional<Episode> findById(int id) {
        return episodeRepository.findById(id);
    }

    public Episode save(Episode episode) {
        return episodeRepository.save(episode);
    }

    public void deleteById(int id) {
        episodeRepository.deleteById(id);
    }
}
