package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Season;
import com.BLUEGREEN.WebWatchMovie.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seasons")
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping
    public List<Season> getAllSeasons() {
        return seasonService.getAllSeasons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Season> getSeasonById(@PathVariable int id) {
        Season season = seasonService.getSeasonById(id);
        if (season != null) {
            return new ResponseEntity<>(season, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Season> createSeason(@RequestBody Season season) {
        Season savedSeason = seasonService.saveSeason(season);
        return new ResponseEntity<>(savedSeason, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Season> updateSeason(@PathVariable int id, @RequestBody Season seasonDetails) {
        Season updatedSeason = seasonService.updateSeason(id, seasonDetails);
        if (updatedSeason != null) {
            return new ResponseEntity<>(updatedSeason, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable int id) {
        seasonService.deleteSeason(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
