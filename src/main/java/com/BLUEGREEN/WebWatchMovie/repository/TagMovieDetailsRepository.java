package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.model.Tag;
import com.BLUEGREEN.WebWatchMovie.model.TagMovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMovieDetailsRepository extends JpaRepository<TagMovieDetails, Integer> {

    List<TagMovieDetails> findByMovie(Movie movie);
    List<TagMovieDetails> findByTag(Tag tag);
}
