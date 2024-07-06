package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.UserDetailsWatchedEpisodes;
import com.BLUEGREEN.WebWatchMovie.service.UserDetailsWatchedEpisodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-details-watched-episodes")
public class UserDetailsWatchedEpisodesController {

    @Autowired
    private UserDetailsWatchedEpisodesService userDetailsWatchedEpisodesService;

    @GetMapping
    public List<UserDetailsWatchedEpisodes> getAllUserDetailsWatchedEpisodes() {
        return userDetailsWatchedEpisodesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsWatchedEpisodes> getUserDetailsWatchedEpisodesById(@PathVariable int id) {
        Optional<UserDetailsWatchedEpisodes> userDetailsWatchedEpisodes = userDetailsWatchedEpisodesService.findById(id);
        return userDetailsWatchedEpisodes.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDetailsWatchedEpisodes createUserDetailsWatchedEpisodes(@RequestBody UserDetailsWatchedEpisodes userDetailsWatchedEpisodes) {
        return userDetailsWatchedEpisodesService.save(userDetailsWatchedEpisodes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsWatchedEpisodes> updateUserDetailsWatchedEpisodes(@PathVariable int id, @RequestBody UserDetailsWatchedEpisodes updatedUserDetailsWatchedEpisodes) {
        Optional<UserDetailsWatchedEpisodes> optionalUserDetailsWatchedEpisodes = userDetailsWatchedEpisodesService.findById(id);

        if (optionalUserDetailsWatchedEpisodes.isPresent()) {
            UserDetailsWatchedEpisodes existingUserDetailsWatchedEpisodes = optionalUserDetailsWatchedEpisodes.get();
            existingUserDetailsWatchedEpisodes.setUser(updatedUserDetailsWatchedEpisodes.getUser());
            existingUserDetailsWatchedEpisodes.setEpisode(updatedUserDetailsWatchedEpisodes.getEpisode());
            existingUserDetailsWatchedEpisodes.setTimeEndWatch(updatedUserDetailsWatchedEpisodes.getTimeEndWatch());
            existingUserDetailsWatchedEpisodes.setTimeActive(updatedUserDetailsWatchedEpisodes.getTimeActive());

            userDetailsWatchedEpisodesService.save(existingUserDetailsWatchedEpisodes);
            return ResponseEntity.ok(existingUserDetailsWatchedEpisodes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetailsWatchedEpisodes(@PathVariable int id) {
        if (userDetailsWatchedEpisodesService.findById(id).isPresent()) {
            userDetailsWatchedEpisodesService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
