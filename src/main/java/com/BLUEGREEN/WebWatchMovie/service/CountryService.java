package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Country;
import com.BLUEGREEN.WebWatchMovie.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(int id) {
        return countryRepository.findById(id).orElse(null);
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountry(int id) {
        countryRepository.deleteById(id);
    }

    public Country updateCountry(int id, Country countryDetails) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country != null) {
            country.setName(countryDetails.getName());
            country.setDescription(countryDetails.getDescription());
            return countryRepository.save(country);
        }
        return null;
    }
}
