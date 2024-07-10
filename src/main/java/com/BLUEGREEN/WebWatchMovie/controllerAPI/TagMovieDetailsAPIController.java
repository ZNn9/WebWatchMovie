package com.BLUEGREEN.WebWatchMovie.controllerAPI;

import com.BLUEGREEN.WebWatchMovie.model.TagMovieDetails;
import com.BLUEGREEN.WebWatchMovie.service.TagMovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tag-movie-details")
public class TagMovieDetailsAPIController {

    @Autowired
    private TagMovieDetailsService tagMovieDetailsService;

    @GetMapping
    public List<TagMovieDetails> getAllTagMovieDetails() {
        return tagMovieDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagMovieDetails> getTagMovieDetailsById(@PathVariable int id) {
        Optional<TagMovieDetails> tagMovieDetails = tagMovieDetailsService.findById(id);
        return tagMovieDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TagMovieDetails createTagMovieDetails(@RequestBody TagMovieDetails tagMovieDetails) {
        return tagMovieDetailsService.save(tagMovieDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagMovieDetails> updateTagMovieDetails(@PathVariable int id, @RequestBody TagMovieDetails updatedTagMovieDetails) {
        Optional<TagMovieDetails> optionalTagMovieDetails = tagMovieDetailsService.findById(id);

        if (optionalTagMovieDetails.isPresent()) {
            TagMovieDetails existingTagMovieDetails = optionalTagMovieDetails.get();
            existingTagMovieDetails.setMovie(updatedTagMovieDetails.getMovie());
            existingTagMovieDetails.setTag(updatedTagMovieDetails.getTag());
            tagMovieDetailsService.save(existingTagMovieDetails);
            return ResponseEntity.ok(existingTagMovieDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTagMovieDetails(@PathVariable int id) {
        if (tagMovieDetailsService.findById(id).isPresent()) {
            tagMovieDetailsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
