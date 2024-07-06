package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Episode;
import com.BLUEGREEN.WebWatchMovie.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeAPIController {

    @Autowired
    private EpisodeService episodeService;

    /*@GetMapping
    public List<Episode> getAllEpisodes() {
        return episodeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Episode> getEpisodeById(@PathVariable int id) {
        Optional<Episode> episode = episodeService.findById(id);
        return episode.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
*/
    @GetMapping
    public List<Episode> getAllEpisodes() {
        return episodeService.getAllEpisodes();
    }

    @GetMapping("/{id}")
    public Episode getEpisodeById(@PathVariable int id) {
        return episodeService.getEpisodeById(id);
    }

    /*@PostMapping
    public Episode createEpisode(@RequestBody Episode episode) {
        return episodeService.save(episode);
    }*/

    @GetMapping("/{id}/episodes")
    public ResponseEntity<List<Episode>> getEpisodesByMovieId(@PathVariable int id) {
        List<Episode> episodes = episodeService.getEpisodesByMovieId(id);
        return new ResponseEntity<>(episodes, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Episode> createEpisode(@RequestBody Episode episode) {
        Episode createdEpisode = episodeService.save(episode);
        return new ResponseEntity<>(createdEpisode, HttpStatus.CREATED);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Episode> updateEpisode(@PathVariable int id, @RequestBody Episode updatedEpisode) {
        Optional<Episode> optionalEpisode = episodeService.findById(id);

        if (optionalEpisode.isPresent()) {
            Episode existingEpisode = optionalEpisode.get();
            existingEpisode.setName(updatedEpisode.getName());
            existingEpisode.setEpisodeAdress(updatedEpisode.getEpisodeAdress());
            existingEpisode.setDescription(updatedEpisode.getDescription());
            existingEpisode.setNumberEpisode(updatedEpisode.getNumberEpisode());
            existingEpisode.setQuantityView(updatedEpisode.getQuantityView());
            existingEpisode.setWatch(updatedEpisode.isWatch());
            existingEpisode.setMovie(updatedEpisode.getMovie());

            episodeService.save(existingEpisode);
            return ResponseEntity.ok(existingEpisode);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable int id) {
        if (episodeService.findById(id).isPresent()) {
            episodeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
