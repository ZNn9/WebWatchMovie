package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.model.UserDetailsMovieFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsMovieFollowRepository extends JpaRepository<UserDetailsMovieFollow, Integer> {

    // Find all movies followed by a specific user
    List<UserDetailsMovieFollow> findByUser(User user);

    // Find if a user follows a specific movie (assuming a user can only follow a movie once)
    boolean existsByUserAndMovie(User user, Movie movie);

    // You can add other custom methods here (optional)

}
