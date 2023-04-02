package com.example.myAirlineFlightservice.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.repositories.AirportRepository;
import com.example.myAirlineFlightservice.repositories.FlightRepository;
import com.example.myAirlineFlightservice.utils.HttpRequestSender;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


/**
 * Service class handling any logic related to {@link Airport}.
 * <p>
 * Extends {@link AbstractService}.
 * 
 * @since 0.0.1
 */
@Service
@Validated
public class AirportService extends AbstractService<Airport> {

    private AirportRepository airportRepository;
    
    @Autowired
    private CityService cityService;

    @Autowired
    private FlightRepository flightRepository;

    
    public AirportService(AirportRepository repository) {
        
        super(repository, "airport");
        this.airportRepository = repository;
    }

    
    /**
     * Find airport by id.
     * 
     * @param id of the airport
     * @return the airport with the given id
     * @throws IllegalStateException if not found.
     */
    public Airport getById(@Min(1) long id) {

        return airportRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find airport with id: " + id + "."));
    }


    /**
     * Find all airports in db.
     * 
     * @return list with airports (might be empty)
     */
    public List<Airport> getAll() {

        return airportRepository.findAll();
    }


    /**
     * Save given airport in db.
     * 
     * @param airport to save in db
     * @return saved airport
     * @throws IllegalStateException if airport already exists or city does not exist.
     */
    public Airport save(@Valid Airport airport) {

        String airportName = airport.getName();

        // airport should not exist
        if (exists(airportName))
            throw new IllegalStateException("Failed to save aiport: " + airportName + ". Airport already exists.");

        // city should exist
        cityService.getByName(airport.getCityName());

        return airportRepository.save(airport);
    }


    public void update(@Valid Airport newAirport) {

        Long id = newAirport.getId();

        // id should not be null
        if (id == null)
            throw new IllegalStateException("Failed to update airport: Id cannot be null.");

        // should exist
        String oldName = getById(id).getName();
        String newName = newAirport.getName();

        // update related flights
        String url = HttpRequestSender.BASE_URL + "/flight/updateAllByAirportName?oldAirportName=" + oldName + "&newAirportName=" + newName;
        HttpRequestSender.sendSimpleRequest(url, HttpMethod.PUT);
        
        airportRepository.save(newAirport);
    }


    /**
     * Delete airport by given name. 
     * 
     * @param name of the airport
     * @throws IllegalStateException if airport not found or related flights are still in db.
     */
    @Override
    public void delete(@NotBlank String name) {

        // should exist
        getByName(name);

        // no related flights should exist
        if (flightRepository.existsByDepartureAirportNameOrArrivalAirportName(name, name))
            throw new IllegalStateException("Failed to delete airport: " + name + ". Delete related entities first.");
      
        airportRepository.deleteByName(name);
    }


    /**
     * Delete all airports in given city. 
     * 
     * @param cityName name of the city.
     * @throws IllegalStateException if city does not exist or airport has still related flights in db.
     */
    public void deleteAllByCityName(@NotBlank String cityName) {

        // city should exist
        cityService.getByName(cityName);

        List<Airport> airports = airportRepository.findAllByCityName(cityName);

        // should'nt have related entites
        if (hasRelatedEntites(airports))
            throw new IllegalStateException("Failed to delete airports in city: " + cityName + ". Delete related entities first.");

        airportRepository.deleteAllByCityName(cityName);
    }


    /**
     * Iterates given list of airports and looks for related flights.
     * 
     * @param airports to check for related flights
     * @return true if at least one airoprt has at least one related flight
     */
    private boolean hasRelatedEntites(List<Airport> airports) {

        // find related flights
        for (Airport airport : airports) {
            String airportName = airport.getName();

            if (flightRepository.existsByDepartureAirportNameOrArrivalAirportName(airportName, airportName))
                return true;
        }

        return false;
    }
}