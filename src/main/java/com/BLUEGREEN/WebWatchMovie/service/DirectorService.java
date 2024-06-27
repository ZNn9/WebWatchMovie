package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Director;
import com.BLUEGREEN.WebWatchMovie.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public Director getDirectorById(int id) {
        return directorRepository.findById(id).orElse(null);
    }

    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    public void deleteDirector(int id) {
        directorRepository.deleteById(id);
    }

    public Director updateDirector(int id, Director directorDetails) {
        Director director = directorRepository.findById(id).orElse(null);
        if (director != null) {
            director.setName(directorDetails.getName());
            director.setDescription(directorDetails.getDescription());
            director.setCountry(directorDetails.getCountry());
            return directorRepository.save(director);
        }
        return null;
    }
}
