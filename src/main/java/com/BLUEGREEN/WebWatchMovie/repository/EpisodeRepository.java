package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Episode;
import com.BLUEGREEN.WebWatchMovie.model.Movie;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

    @Query("SELECT e FROM Episode e WHERE e.movie.idMovie = :movieId")
    List<Episode> findByMovieId(@Param("movieId") int movieId);
}
