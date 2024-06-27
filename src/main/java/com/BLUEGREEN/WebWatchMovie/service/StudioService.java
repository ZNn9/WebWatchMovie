package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Studio;
import com.BLUEGREEN.WebWatchMovie.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;

    public List<Studio> getAllStudios() {
        return studioRepository.findAll();
    }

    public Studio getStudioById(int id) {
        return studioRepository.findById(id).orElse(null);
    }

    public Studio saveStudio(Studio studio) {
        return studioRepository.save(studio);
    }

    public void deleteStudio(int id) {
        studioRepository.deleteById(id);
    }

    public Studio updateStudio(int id, Studio studioDetails) {
        Studio studio = studioRepository.findById(id).orElse(null);
        if (studio != null) {
            studio.setName(studioDetails.getName());
            studio.setDescription(studioDetails.getDescription());
            studio.setCountry(studioDetails.getCountry());
            return studioRepository.save(studio);
        }
        return null;
    }
}
