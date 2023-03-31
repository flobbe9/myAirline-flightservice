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


/**
 * Service class handling any logic related to {@link City}.
 * <p>
 * Extends {@link AbstractService}.
 * 
 * @since 0.0.1
 */
@Service
@Validated
public class CityService extends AbstractService<City> {
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private AirportRepository airportRepository;

    
    public CityService(CityRepository repository) {
        super(repository, "city");
    }


    /**
     * Find city by id.
     * 
     * @param id of the city
     * @return the city with the given id
     * @throws IllegalStateException if not found.
     */
    public City getById(@Min(1) long id) {

        return cityRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find city with id: " + id + "."));
    }


    /**
     * Save given city in db.
     * 
     * @param city to save in db
     * @return saved city
     * @throws IllegalStateException if city already exists or country does not exist.
     */
    public City save(@Valid City city) {

        String cityName = city.getName();

        // city should not exist
        if (exists(cityName))
            throw new IllegalStateException("Failed to save city: " + cityName + ". City already exists.");

        // country should exist
        countryService.getByName(city.getCountryName());

        return cityRepository.save(city);
    }
 

    /**
     * Delete city by given name. 
     * 
     * @param name of the city
     * @throws IllegalStateException if city not found or related airports are still in db.
     */
    @Override
    public void delete(@NotBlank String name) {

        // should exist
        getByName(name);

        // no related airports should exist
        if (airportRepository.existsByCityName(name))
            throw new IllegalStateException("Failed to delete city: " + name + ". Delete related entities first.");
      
        cityRepository.deleteByName(name);
    }


    /**
     * Delete all cities in given country. 
     * 
     * @param countryName name of the country.
     * @throws IllegalStateException if country does not exist or city has still related airports in db.
     */
    public void deleteAllByCountryName(@NotBlank String countryName) {

        // country should exist
        countryService.getByName(countryName);

        List<City> cities = cityRepository.findAllByCountryName(countryName);

        // should'nt have related entites
        if (hasRelatedEntites(cities))
            throw new IllegalStateException("Failed to delete cities in country: " + countryName + ". Delete related entities first.");

        cityRepository.deleteAllByCountryName(countryName);
    }


    /**
     * Iterates given list of cites and looks for related airports (meaning an airport located in that city).
     * 
     * @param cities to check for related airports
     * @return true if at least one city has at least one related airport
     */
    private boolean hasRelatedEntites(List<City> cities) {

        // find related airports
        for (City city : cities) {
            if (airportRepository.existsByCityName(city.getName()))
                return true;
        }

        return false;
    }
}