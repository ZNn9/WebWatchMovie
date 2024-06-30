package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.model.MovieDetailsLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDetailsLanguageRepository extends JpaRepository<MovieDetailsLanguage, Integer> {

    List<MovieDetailsLanguage> findByMovie(Movie movie);
}
