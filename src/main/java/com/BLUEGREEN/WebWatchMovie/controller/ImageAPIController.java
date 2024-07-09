package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Image;
import com.BLUEGREEN.WebWatchMovie.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageAPIController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Image> getAllImages() {
        return imageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable int id) {
        Optional<Image> image = imageService.findById(id);
        return image.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Image createImage(@RequestBody Image image) {
        return imageService.save(image);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable int id, @RequestBody Image updatedImage) {
        Optional<Image> optionalImage = imageService.findById(id);

        if (optionalImage.isPresent()) {
            Image existingImage = optionalImage.get();
            existingImage.setName(updatedImage.getName());
            existingImage.setImageAddress(updatedImage.getImageAddress());
            existingImage.setIsTitle(updatedImage.getIsTitle());
            existingImage.setUser(updatedImage.getUser());
            existingImage.setMovie(updatedImage.getMovie());
            imageService.save(existingImage);
            return ResponseEntity.ok(existingImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable int id) {
        if (imageService.findById(id).isPresent()) {
            imageService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
