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

    public List<Episode> getAllEpisodes() {
        return episodeRepository.findAll();
    }

    public Episode getEpisodeById(int idEpisode) {
        return episodeRepository.findById(idEpisode).orElse(null);
    }

    public List<Episode> findByMovieId(int idMovie) {
        return episodeRepository.findByMovieId(idMovie);
    }


    public List<Episode> getEpisodesByMovieId(int idMovie) {
        return episodeRepository.findByMovieId(idMovie);
    }

    // Phương thức lấy episode dựa trên id của episode và id của movie
    public Episode getEpisodeByIdAndMovieId(int idEpisode, int idMovie) {
        return episodeRepository.findByIdAndMovieId(idEpisode, idMovie);
    }

    public List<Episode> findByName(String name) {
        return episodeRepository.findByName(name);
    }

    public Episode findByNumberEpisode(int numberEpisode) {
        return episodeRepository.findByNumberEpisode(numberEpisode);
    }

    // Mới


}
