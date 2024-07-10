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

    @Query("SELECT e FROM Episode e WHERE e.movie.idMovie = :movieId ORDER BY e.numberEpisode")
    List<Episode> findByMovieId(@Param("movieId") int movieId);

    @Query("SELECT e FROM Episode e WHERE e.idEpisode = :idEpisode AND e.movie.idMovie = :movieId")
    Episode findByIdAndMovieId(@Param("idEpisode") int idEpisode, @Param("movieId") int movieId);

    List<Episode> findByName(String name);
    Episode findByNumberEpisode(int numberEpisode);

    /*List<Episode> findByMovieId(int movieId);*/

}
