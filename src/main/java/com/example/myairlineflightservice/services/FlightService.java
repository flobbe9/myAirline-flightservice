package com.example.myAirlineFlightservice.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.models.FlightClass;
import com.example.myAirlineFlightservice.repositories.FlightRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Service
@Validated
public class FlightService {
    
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportService airportService;


    public Flight getById(@Min(0) long id) {

        return flightRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find flight with id: " + id + "."));
    }


    public Flight getByNumber(@Min(0) long number) {

        return flightRepository.findByNumber(number).orElseThrow(() -> 
            new IllegalStateException("Could not find flight: " + number + "."));
    }


    public List<Flight> getAllByDepartureTimeAfter(@NotNull LocalTime departureTime) {

        return flightRepository.findAllByDepartureTimeAfter(departureTime);
    }


    public List<Flight> getAllByDepartureDate(@NotNull LocalDate departureDate) {

        // should not be in past
        if (!isDateValid(departureDate)) 
            throw new IllegalStateException("Failed to find flights: Date " + departureDate + " is invalid.");

        return flightRepository.findAllByDepartureDate(departureDate);
    }


    public List<Flight> getAllByAirport(@NotBlank String airportName) {

        // airport should exist
        airportService.getByName(airportName);

        return flightRepository.findAllByDepartureAirportNameOrArrivalAirportName(airportName, airportName);
    }

    
    public List<Flight> getAllByBasePriceLessThanEqual(@Min(0) double basePrice) {

        return flightRepository.findAllByBasePriceLessThanEqual(basePrice);
    }


    public List<Flight> getAllBySeatsTotalGreaterThanEqual(@Min(0) int seatsTotal) {

        return flightRepository.findAllBySeatsTotalGreaterThanEqual(seatsTotal);
    }


    public List<Flight> getAllByFlightClass(@NotNull FlightClass flightClass) {

        return flightRepository.findAll().stream()
                                         .filter(flight -> flight.getFlightClasses().contains(flightClass))
                                         .collect(Collectors.toList());
    }


    public Flight save(@Valid Flight flight) {

        long number = flight.getNumber();

        // should not exist
        if (exists(number))
            throw new IllegalStateException("Failed to save flight: " + number + ". Flight already exists.");

        return flightRepository.save(flight);
    }


    public Flight update(@Valid Flight flight) {

        Long id = flight.getId();

        // id should not be null
        if (id == null)
            throw new IllegalStateException("Failed to update flight. Id cannot be null.");

        // flight should exist
        getById(id);

        return flightRepository.save(flight);
    }

    
    public List<Flight> updateAllByAirportName(@NotBlank String oldAirportName, @NotBlank String newAirportName) {

        // find old flights
        List<Flight> relatedFlights = flightRepository.findAllByDepartureAirportNameOrArrivalAirportName(oldAirportName, oldAirportName);

        // set new airport names
        relatedFlights.forEach(flight -> {
                        // set departureAirportName
                        if (flight.getDepartureAirportName().equals(oldAirportName)) {
                            flight.setDepartureAirportName(newAirportName);

                        // set arrivalAirportName
                        } else if (flight.getArrivalAirportName().equals(oldAirportName))
                            flight.setArrivalAirportName(newAirportName);

                        // save to db
                        update(flight);
                    });

        return relatedFlights;
    }


    public boolean exists(@Min(0) long number) {

        return flightRepository.existsByNumber(number);
    }


    public boolean existsByAirport(@NotBlank String airportName) {

        return flightRepository.existsByDepartureAirportNameOrArrivalAirportName(airportName, airportName);
    }


    public void delete(@Min(0) long number) {
      
        // should exist
        if (!exists(number))
            throw new IllegalStateException("Failed to delete flight: " + number + ". Not found in db before deletion.");

        flightRepository.deleteByNumber(number);
    }


    public void deleteAllByAirportName(@NotBlank String airportName) {

        flightRepository.deleteAllByDepartureAirportNameOrArrivalAirportName(airportName, airportName);
    }


    /**
     * Checks that given date is not in the past.
     * @param departureDate date to check.
     * @return true if date is today or in the future.
     */
    private boolean isDateValid(@NotNull LocalDate departureDate) {

        return departureDate.equals(LocalDate.now()) || departureDate.isAfter(LocalDate.now());
    }
}