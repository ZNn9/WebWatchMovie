package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.OriginMovie;
import com.BLUEGREEN.WebWatchMovie.repository.OriginMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginMovieService {

    @Autowired
    private OriginMovieRepository originMovieRepository;

    public List<OriginMovie> getAllOriginMovies() {
        return originMovieRepository.findAll();
    }

    public OriginMovie getOriginMovieById(int id) {
        return originMovieRepository.findById(id).orElse(null);
    }

    public OriginMovie saveOriginMovie(OriginMovie originMovie) {
        return originMovieRepository.save(originMovie);
    }

    public void deleteOriginMovie(int id) {
        originMovieRepository.deleteById(id);
    }

    public OriginMovie updateOriginMovie(int id, OriginMovie originMovieDetails) {
        OriginMovie originMovie = originMovieRepository.findById(id).orElse(null);
        if (originMovie != null) {
            originMovie.setName(originMovieDetails.getName());
            originMovie.setDescription(originMovieDetails.getDescription());
            return originMovieRepository.save(originMovie);
        }
        return null;
    }
}
