package com.example.myAirlineFlightservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.models.City;
import com.example.myAirlineFlightservice.repositories.AirportRepository;
import com.example.myAirlineFlightservice.repositories.CityRepository;

import jakarta.validation.Valid;
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


    @Override
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

        // no related cities should exist
        if (!airportRepository.findAllByCityName(name).isEmpty())
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

        // airports
        for (City city : cities) {
            List<Airport> airports = airportRepository.findAllByCityName(city.getName());
            
            if (!airports.isEmpty())
                return true;
        }

        return false;
    }
}