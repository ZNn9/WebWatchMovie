package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.TagMovieDetails;
import com.BLUEGREEN.WebWatchMovie.repository.TagMovieDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagMovieDetailsService {

    @Autowired
    private TagMovieDetailsRepository tagMovieDetailsRepository;

    public List<TagMovieDetails> findAll() {
        return tagMovieDetailsRepository.findAll();
    }

    public Optional<TagMovieDetails> findById(int id) {
        return tagMovieDetailsRepository.findById(id);
    }

    public TagMovieDetails save(TagMovieDetails tagMovieDetails) {
        return tagMovieDetailsRepository.save(tagMovieDetails);
    }

    public void deleteById(int id) {
        tagMovieDetailsRepository.deleteById(id);
    }
}
