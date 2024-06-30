package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Image;
import com.BLUEGREEN.WebWatchMovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {


    List<Image> findByMovie(Movie movie);
    Image findByIsTitleTrueAndMovie(Movie movie); // Find the title image for a movie (if it exists)
}
