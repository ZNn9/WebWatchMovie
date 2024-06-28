package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Season;
import com.BLUEGREEN.WebWatchMovie.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    public List<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }

    public Season getSeasonById(int id) {
        return seasonRepository.findById(id).orElse(null);
    }

    public Season saveSeason(Season season) {
        return seasonRepository.save(season);
    }

    public void deleteSeason(int id) {
        seasonRepository.deleteById(id);
    }

    public Season updateSeason(int id, Season seasonDetails) {
        Season season = seasonRepository.findById(id).orElse(null);
        if (season != null) {
            season.setName(seasonDetails.getName());
            season.setDescription(seasonDetails.getDescription());
            season.setYear(seasonDetails.getYear());
            return seasonRepository.save(season);
        }
        return null;
    }
}
