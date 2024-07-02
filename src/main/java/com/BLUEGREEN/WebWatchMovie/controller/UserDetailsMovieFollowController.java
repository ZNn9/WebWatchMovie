package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.UserDetailsMovieFollow;
import com.BLUEGREEN.WebWatchMovie.service.UserDetailsMovieFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-details-movie-follows")
public class UserDetailsMovieFollowController {

    @Autowired
    private UserDetailsMovieFollowService userDetailsMovieFollowService;

    @GetMapping
    public List<UserDetailsMovieFollow> getAllUserDetailsMovieFollows() {
        return userDetailsMovieFollowService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsMovieFollow> getUserDetailsMovieFollowById(@PathVariable int id) {
        Optional<UserDetailsMovieFollow> userDetailsMovieFollow = userDetailsMovieFollowService.findById(id);
        return userDetailsMovieFollow.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDetailsMovieFollow createUserDetailsMovieFollow(@RequestBody UserDetailsMovieFollow userDetailsMovieFollow) {
        return userDetailsMovieFollowService.save(userDetailsMovieFollow);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsMovieFollow> updateUserDetailsMovieFollow(@PathVariable int id, @RequestBody UserDetailsMovieFollow updatedUserDetailsMovieFollow) {
        Optional<UserDetailsMovieFollow> optionalUserDetailsMovieFollow = userDetailsMovieFollowService.findById(id);

        if (optionalUserDetailsMovieFollow.isPresent()) {
            UserDetailsMovieFollow existingUserDetailsMovieFollow = optionalUserDetailsMovieFollow.get();
            existingUserDetailsMovieFollow.setUser(updatedUserDetailsMovieFollow.getUser());
            existingUserDetailsMovieFollow.setMovie(updatedUserDetailsMovieFollow.getMovie());
            existingUserDetailsMovieFollow.setTimeActive(updatedUserDetailsMovieFollow.getTimeActive());

            userDetailsMovieFollowService.save(existingUserDetailsMovieFollow);
            return ResponseEntity.ok(existingUserDetailsMovieFollow);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetailsMovieFollow(@PathVariable int id) {
        if (userDetailsMovieFollowService.findById(id).isPresent()) {
            userDetailsMovieFollowService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
