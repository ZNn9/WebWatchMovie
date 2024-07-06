package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Studio;
import com.BLUEGREEN.WebWatchMovie.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studios")
public class StudioAPIController {

    @Autowired
    private StudioService studioService;

    @GetMapping
    public List<Studio> getAllStudios() {
        return studioService.getAllStudios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Studio> getStudioById(@PathVariable int id) {
        Studio studio = studioService.getStudioById(id);
        if (studio != null) {
            return new ResponseEntity<>(studio, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Studio> createStudio(@RequestBody Studio studio) {
        Studio savedStudio = studioService.saveStudio(studio);
        return new ResponseEntity<>(savedStudio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Studio> updateStudio(@PathVariable int id, @RequestBody Studio studioDetails) {
        Studio updatedStudio = studioService.updateStudio(id, studioDetails);
        if (updatedStudio != null) {
            return new ResponseEntity<>(updatedStudio, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudio(@PathVariable int id) {
        studioService.deleteStudio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
