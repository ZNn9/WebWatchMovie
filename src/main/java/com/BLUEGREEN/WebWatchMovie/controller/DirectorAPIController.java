package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Director;
import com.BLUEGREEN.WebWatchMovie.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorAPIController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable int id) {
        Director director = directorService.getDirectorById(id);
        if (director != null) {
            return new ResponseEntity<>(director, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        Director savedDirector = directorService.saveDirector(director);
        return new ResponseEntity<>(savedDirector, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(@PathVariable int id, @RequestBody Director directorDetails) {
        Director updatedDirector = directorService.updateDirector(id, directorDetails);
        if (updatedDirector != null) {
            return new ResponseEntity<>(updatedDirector, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable int id) {
        directorService.deleteDirector(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
