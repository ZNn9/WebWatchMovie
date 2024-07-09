package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Actor;
import com.BLUEGREEN.WebWatchMovie.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/actors")
public class ActorAPIController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable int id) {
        Optional<Actor> actor = actorService.getActorById(id);
        return actor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        Actor savedActor = actorService.saveActor(actor);
        return new ResponseEntity<>(savedActor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable int id, @RequestBody Actor actorDetails) {
        Actor updatedActor = actorService.updateActor(id, actorDetails);
        if (updatedActor != null) {
            return new ResponseEntity<>(updatedActor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable int id) {
        actorService.deleteActor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
