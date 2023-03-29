package com.example.myAirlineFlightservice.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.Flight;
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
    private AirlineService airlineService;

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


    public Flight save(@Valid Flight flight) {

        long number = flight.getNumber();

        // should be valid 
        if (!isFlightValid(flight))
            throw new IllegalStateException("Failed to save flight: " + number + ". Flight invalid.");

        // should not exist
        if (exists(number))
            throw new IllegalStateException("Failed to save flight: " + number + ". Flight already exists.");

        return flightRepository.save(flight);
    }


    // TODO: make update method, consider updatetable=false for flight entity


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


    private boolean isFlightValid(@Valid Flight flight) {

        return 
            // airline should exist
            airlineService.exists(flight.getAirlineName()) &&

            // airports should exist
            airportService.exists(flight.getDepartureAirportName()) &&
            airportService.exists(flight.getArrivalAirportName()) &&

            // dates should be in order
            flight.areDatesInOrder() &&

            // time should be in order
            flight.isTimeInOrder();
    }


    /**
     * Checks that given date is not in the past.
     * @param departureDate date to check.
     * @return true if date is today or in the future.
     */
    private boolean isDateValid(@NotNull LocalDate departureDate) {

        return departureDate.equals(departureDate) || departureDate.isAfter(LocalDate.now());
    }
}