package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.TagMovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMovieDetailsRepository extends JpaRepository<TagMovieDetails, Integer> {

    @Query("SELECT tmd FROM TagMovieDetails tmd WHERE tmd.movie.idMovie IN :movieIds")
    List<TagMovieDetails> findByMovieIdIn(List<Integer> movieIds);
}