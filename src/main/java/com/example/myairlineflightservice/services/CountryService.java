package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Country;
import com.example.myAirlineFlightservice.repositories.CityRepository;
import com.example.myAirlineFlightservice.repositories.CountryRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


/**
 * Service class handling any logic related to {@link Country}.
 * <p>
 * Extends {@link AbstractService}.
 * 
 * @since 0.0.1
 */
@Service
@Validated
public class CountryService extends AbstractService<Country> {

    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;


    public CountryService(CountryRepository repository) {
        super(repository, "country");
        this.countryRepository = repository;
    }


    /**
     * Find country by id.
     * 
     * @param id of the country
     * @return the country with the given id
     * @throws IllegalStateException if not found.
     */
    public Country getById(@Min(1) long id) {

        return countryRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find country with id: " + id + "."));
    }


    /**
     * Save given country in db.
     * 
     * @param country to save in db
     * @return saved country
     * @throws IllegalStateException if already exists.
     */
    public Country save(@Valid Country country) {

        String countryName = country.getName();

        // country should not exist
        if (exists(countryName))
            throw new IllegalStateException("Failed to save aiport: " + countryName + ". Country already exists.");

        return countryRepository.save(country);
    }


    /**
     * Delete country by given name. 
     * 
     * @param name of the country
     * @throws IllegalStateException if country not found or related cities are still in db
     */
    @Override
    public void delete(@NotBlank String name) {

        // country should exist
        getByName(name);

        // should have no related cities
        if (cityRepository.existsByCountryName(name))
            throw new IllegalStateException("Failed to delete country: " + name + ". Delete related entities first.");

        countryRepository.deleteByName(name);
    }
}