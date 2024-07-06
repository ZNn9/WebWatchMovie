package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.UserDetailsMovieRating;
import com.BLUEGREEN.WebWatchMovie.service.UserDetailsMovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-details-movie-ratings")
public class UserDetailsMovieRatingController {

    @Autowired
    private UserDetailsMovieRatingService userDetailsMovieRatingService;

    @GetMapping
    public List<UserDetailsMovieRating> getAllUserDetailsMovieRatings() {
        return userDetailsMovieRatingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsMovieRating> getUserDetailsMovieRatingById(@PathVariable int id) {
        Optional<UserDetailsMovieRating> userDetailsMovieRating = userDetailsMovieRatingService.findById(id);
        return userDetailsMovieRating.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDetailsMovieRating createUserDetailsMovieRating(@RequestBody UserDetailsMovieRating userDetailsMovieRating) {
        return userDetailsMovieRatingService.save(userDetailsMovieRating);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsMovieRating> updateUserDetailsMovieRating(@PathVariable int id, @RequestBody UserDetailsMovieRating updatedUserDetailsMovieRating) {
        Optional<UserDetailsMovieRating> optionalUserDetailsMovieRating = userDetailsMovieRatingService.findById(id);

        if (optionalUserDetailsMovieRating.isPresent()) {
            UserDetailsMovieRating existingUserDetailsMovieRating = optionalUserDetailsMovieRating.get();
            existingUserDetailsMovieRating.setUser(updatedUserDetailsMovieRating.getUser());
            existingUserDetailsMovieRating.setMovie(updatedUserDetailsMovieRating.getMovie());
            existingUserDetailsMovieRating.setRatingPoint(updatedUserDetailsMovieRating.getRatingPoint());
            existingUserDetailsMovieRating.setTimeActive(updatedUserDetailsMovieRating.getTimeActive());

            userDetailsMovieRatingService.save(existingUserDetailsMovieRating);
            return ResponseEntity.ok(existingUserDetailsMovieRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetailsMovieRating(@PathVariable int id) {
        if (userDetailsMovieRatingService.findById(id).isPresent()) {
            userDetailsMovieRatingService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
