package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Movie;
import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.model.UserDetailsMovieRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsMovieRatingRepository extends JpaRepository<UserDetailsMovieRating, Integer> {

    // Find the rating a user gave to a specific movie (assuming a user can only rate a movie once)
    Optional<UserDetailsMovieRating> findByUserAndMovie(User user, Movie movie);

    // You can add other custom methods here (optional)


    @Query("SELECT r FROM UserDetailsMovieRating r WHERE r.user.id = :userId")
    List<UserDetailsMovieRating> findByUserId(int userId);


    @Query("SELECT r FROM UserDetailsMovieRating r ORDER BY r.ratingPoint DESC")
    List<UserDetailsMovieRating> findTop10ByOrderByRatingPointDesc();
}
