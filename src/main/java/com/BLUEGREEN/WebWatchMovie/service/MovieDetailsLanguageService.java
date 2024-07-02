package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.MovieDetailsLanguage;
import com.BLUEGREEN.WebWatchMovie.repository.MovieDetailsLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieDetailsLanguageService {

    @Autowired
    private MovieDetailsLanguageRepository repository;

    public List<MovieDetailsLanguage> findAll() {
        return repository.findAll();
    }

    public Optional<MovieDetailsLanguage> findById(int id) {
        return repository.findById(id);
    }

    public MovieDetailsLanguage save(MovieDetailsLanguage movieDetailsLanguage) {
        return repository.save(movieDetailsLanguage);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
