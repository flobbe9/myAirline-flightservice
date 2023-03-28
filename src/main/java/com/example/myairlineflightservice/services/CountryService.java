package com.example.myAirlineFlightservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Country;
import com.example.myAirlineFlightservice.repositories.CityRepository;
import com.example.myAirlineFlightservice.repositories.CountryRepository;

import jakarta.validation.constraints.NotBlank;


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


    @Override
    public void delete(@NotBlank String name) {

        // country should exist
        getByName(name);

        // should have no related cities
        if (!cityRepository.findAllByCountryName(name).isEmpty())
            throw new IllegalStateException("Failed to delete country: " + name + ". Delete related entities first.");

        countryRepository.deleteByName(name);
    }
}