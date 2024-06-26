package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Episode;
import com.BLUEGREEN.WebWatchMovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

}
