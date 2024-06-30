package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Actor;
import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.model.MovieActorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieActorDetailsRepository extends JpaRepository<MovieActorDetails, Integer> {


    List<MovieActorDetails> findByMovie(Movie movie);
    List<MovieActorDetails> findByActor(Actor actor);
}
