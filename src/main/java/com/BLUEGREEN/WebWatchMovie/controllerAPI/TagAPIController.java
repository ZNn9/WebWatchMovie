package com.BLUEGREEN.WebWatchMovie.controllerAPI;

import com.BLUEGREEN.WebWatchMovie.model.Tag;
import com.BLUEGREEN.WebWatchMovie.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags")
public class TagAPIController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable int id) {
        Optional<Tag> tag = tagService.findById(id);
        return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.save(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable int id, @RequestBody Tag updatedTag) {
        Optional<Tag> optionalTag = tagService.findById(id);

        if (optionalTag.isPresent()) {
            Tag existingTag = optionalTag.get();
            existingTag.setName(updatedTag.getName());
            existingTag.setDescription(updatedTag.getDescription());
            tagService.save(existingTag);
            return ResponseEntity.ok(existingTag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable int id) {
        if (tagService.findById(id).isPresent()) {
            tagService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
