package com.example.myAirlineFlightservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.City;
import com.example.myAirlineFlightservice.repositories.AirportRepository;
import com.example.myAirlineFlightservice.repositories.CityRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Service
@Validated
public class CityService extends AbstractService<City> {
    
    private CityRepository cityRepository;
    
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private AirportRepository airportRepository;

    
    public CityService(CityRepository repository) {
        super(repository, "city");
        this.cityRepository = repository;
    }


    public City getById(@Min(1) long id) {

        return cityRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find city with id: " + id + "."));
    }


    public City save(@Valid City city) {

        String cityName = city.getName();

        // city should not exist
        if (exists(cityName))
            throw new IllegalStateException("Failed to save city: " + cityName + ". City already exists.");

        // country should exist
        countryService.getByName(city.getCountryName());

        return cityRepository.save(city);
    }


    @Override
    public void delete(@NotBlank String name) {

        // should exist
        getByName(name);

        // no related airports should exist
        if (airportRepository.existsByCityName(name))
            throw new IllegalStateException("Failed to delete city: " + name + ". Delete related entities first.");
      
        cityRepository.deleteByName(name);
    }


    public void deleteAllByCountryName(@NotBlank String countryName) {

        // country should exist
        countryService.getByName(countryName);

        List<City> cities = cityRepository.findAllByCountryName(countryName);

        // should'nt have related entites
        if (hasRelatedEntites(cities))
            throw new IllegalStateException("Failed to delete cities in country: " + countryName + ". Delete related entities first.");

        cityRepository.deleteAllByCountryName(countryName);
    }


    private boolean hasRelatedEntites(List<City> cities) {

        // find related airports
        for (City city : cities) {

            if (!airportRepository.existsByCityName(city.getName()))
                return true;
        }

        return false;
    }
}