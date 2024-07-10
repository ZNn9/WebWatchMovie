package com.BLUEGREEN.WebWatchMovie.controllerAPI;

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

    @GetMapping
    public List<Episode> getAllEpisodes() {
        return episodeService.getAllEpisodes();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Episode> getEpisodeById(@PathVariable int id) {
        Optional<Episode> episode = episodeService.findById(id);
        return episode.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Episode> getEpisodeById(@PathVariable int id) {
        Episode episode = episodeService.getEpisodeById(id);
        if (episode != null) {
            return ResponseEntity.ok(episode);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/episodes/{idMovie}")
    public ResponseEntity<List<Episode>> getEpisodesByMovieId(@PathVariable int idMovie) {
        List<Episode> episodes = episodeService.findByMovieId(idMovie);
        if (episodes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(episodes);
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


    /*@GetMapping("/name/{name}")
    public ResponseEntity<List<Episode>> getEpisodesByName(@PathVariable String name) {
        List<Episode> episodes = episodeService.findByName(name);
        return new ResponseEntity<>(episodes, HttpStatus.OK);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<Episode> getEpisodeByNumber(@PathVariable int number) {
        Episode episode = episodeService.findByNumberEpisode(number);
        if (episode != null) {
            return ResponseEntity.ok(episode);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Các phương thức API khác của EpisodeAPIController

    @GetMapping("/anime-watching/{id_movie}")
    public List<Episode> animeWatching(@PathVariable("id_movie") int idMovie) {
        // Lấy danh sách các episode dựa trên id_movie
        List<Episode> episodes = episodeService.findByMovieId(idMovie);

        return episodes;
    }*/



}
