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
import com.example.myAirlineFlightservice.models.FlightDetails;
import com.example.myAirlineFlightservice.models.SeatType;
import com.example.myAirlineFlightservice.repositories.FlightRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * Service class handling any logic related to {@link Flight}.
 * 
 * @since 0.0.1
 */
@Service
@Validated
public class FlightService {
    
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportService airportService;


    /**
     * Find flight by id.
     * 
     * @param id of the flight
     * @return the flight with the given id
     * @throws IllegalStateException if not found.
     */
    public Flight getById(@Min(0) long id) {

        return flightRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find flight with id: " + id + "."));
    }


    /**
     * Find flight by number.
     * 
     * @param number of the flight
     * @return the flight with the given number
     * @throws IllegalStateException if not found.
     */
    public Flight getByNumber(@Min(0) long number) {

        return flightRepository.findByNumber(number).orElseThrow(() -> 
            new IllegalStateException("Could not find flight: " + number + "."));
    }


    /**
     * Find all flights after the given time (not considering the date).
     * 
     * @param departureTime time of departure of the flight
     * @return list of flights departing after given time
     */
    public List<Flight> getAllByDepartureTimeAfter(@NotNull LocalTime departureTime) {

        return flightRepository.findAllByDepartureTimeAfter(departureTime);
    }


    /**
     * Find all flights with the given date.
     * 
     * @param departureTime date of departure of the flight
     * @return list of flights departing on given date
     * @throws IllegalStateException if date is in the past
     */
    public List<Flight> getAllByDepartureDate(@NotNull LocalDate departureDate) {

        // should not be in past
        if (!isDateValid(departureDate)) 
            throw new IllegalStateException("Failed to find flights: Date " + departureDate + " is invalid.");

        return flightRepository.findAllByDepartureDate(departureDate);
    }


    /**
     * Find all flights either departing from or arriving at the given airport.
     * 
     * @param airportName name of airport
     * @return list of flights related to given airport
     * @throws IllegalStateException if airport does not exist
     */
    public List<Flight> getAllByAirport(@NotBlank String airportName) {

        // airport should exist
        airportService.getByName(airportName);

        return flightRepository.findAllByDepartureAirportNameOrArrivalAirportName(airportName, airportName);
    }

    
    /**
     * Find all flights with equal or cheaper base price than the given one.
     * <p>
     * Base price does not consider additional fees like a special seat or additional luggage.
     * 
     * @param basePrice beeing the maximum cost for a flight
     * @return list of flights with equal or cheaper base price than given base price
     */
    public List<Flight> getAllByBasePriceLessThanEqual(@Min(0) double basePrice) {

        return flightRepository.findAllByBasePriceLessThanEqual(basePrice);
    }


    /**
     * Find all flights with at least as many seats available as given seat number.
     * 
     * @param numAvailableSeats minimum available seats of flight
     * @return list of flights with equal or more seats available
     */
    public List<Flight> getAllByNumAvailableSeatsGreaterThanEqual(@Min(0) int numAvailableSeats) {

        return flightRepository.findAllByNumAvailableSeatsGreaterThanEqual(numAvailableSeats);
    }


    /**
     * Find all flights offering given flight class.
     * 
     * @param flightClass to be offered by the flight
     * @return list of flights offering given flight class
     */
    public List<Flight> getAllByFlightClass(@NotNull FlightClass flightClass) {

        return flightRepository.findAll().stream()
                                         .filter(flight -> flight.getFlightClasses().contains(flightClass))
                                         .collect(Collectors.toList());
    }


    /**
     * Save given flight in db.
     * 
     * @param flight to save in db
     * @return saved flight
     * @throws IllegalStateException if already exists
     */
    public Flight save(@Valid Flight flight) {

        long number = flight.getNumber();

        // should not exist
        if (exists(number))
            throw new IllegalStateException("Failed to save flight: " + number + ". Flight already exists.");

        return flightRepository.save(flight);
    }


    /**
     * Save given flight to db if exists by number. Id of new flight cannot be null.
     * 
     * @param flight to save to db
     * @return updated flight
     * @throws IllegalStateException if id is null or flight with given id does not exist
     */
    public Flight update(@Valid Flight flight) {

        Long id = flight.getId();

        // id should not be null
        if (id == null)
            throw new IllegalStateException("Failed to update flight. Id cannot be null.");

        // flight should exist
        getById(id);

        return flightRepository.save(flight);
    }

    
    /**
     * Update all flights either departing from or arriving at the given airport. 
     * 
     * @param oldAirportName name of the airport that should be updated. Has to exist in db at this point
     * @param newAirportName new airport name
     * @return list with updated flights
     * @throws IllegalStateException if old airport does not exist
     */
    public List<Flight> updateAllByAirportName(@NotBlank String oldAirportName, @NotBlank String newAirportName) {

        // find old flights
        List<Flight> relatedFlights = flightRepository.findAllByDepartureAirportNameOrArrivalAirportName(oldAirportName, oldAirportName);

        // old airport should exist
        airportService.getByName(oldAirportName);

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


    /**
     * Find out if a flight with the given number exists.
     * 
     * @param number of the flight
     * @return true if a flight exists
     */
    public boolean exists(@Min(0) long number) {

        return flightRepository.existsByNumber(number);
    }


    /**
     * Find out if a flight with either departing from or arriving at the given airport exists.
     * 
     * @param airportName name of the airport 
     * @return true if at least one flight related to given airport exists
     */
    public boolean existsByAirport(@NotBlank String airportName) {

        return flightRepository.existsByDepartureAirportNameOrArrivalAirportName(airportName, airportName);
    }


    /**
     * Delete flight by given number. 
     * 
     * @param number of the flight
     * @throws IllegalStateException if flight not found
     */
    public void delete(@Min(0) long number) {
      
        // should exist
        if (!exists(number))
            throw new IllegalStateException("Failed to delete flight: " + number + ". Not found in db before deletion.");

        flightRepository.deleteByNumber(number);
    }


     /**
     * Delete all flights either departing from or arriving at the given airport.
     * 
     * @param airportName name of the airport
     */
    public void deleteAllByAirportName(@NotBlank String airportName) {

        flightRepository.deleteAllByDepartureAirportNameOrArrivalAirportName(airportName, airportName);
    }


    public FlightDetails book(FlightDetails flightDetails) {

        SeatType seatType = flightDetails.getSeatType();
        long number = flightDetails.getNumber();
        double seatFee = seatType.getSeatFee();
        double luggageFee = flightDetails.getLuggageFee();
        FlightClass flightClass = flightDetails.getFlightClass();

        // should exist
        Flight flight = getByNumber(number);
        
        double priceTotal = flight.getBasePrice();

        // is flight booked out
        if (flight.isBookedOut())
            throw new IllegalStateException("Failed to book flight: " + number + ". Flight is booked out.");

        // is seat available
        if (!flight.isSeatAvailable(seatType))
            throw new IllegalStateException("Failed to book flight. Seat type of " + seatType.name() + " is not available anymore. Please choose a different seat type.");
        
        // reduce num seats
        flight.reduceNumSeats(seatType);

        // add seatFee, luggageFee and flightClassFee to basePrice
        priceTotal = flight.getBasePrice() + seatFee + luggageFee + flightClass.getFee();

        // update flight
        update(flight);

        // retun FlightDetails
        return new FlightDetails(number, 
                                 seatType, 
                                 luggageFee, 
                                 flightClass,
                                 priceTotal);
    }


    /**
     * Checks that given date is not in the past.
     * 
     * @param departureDate date to check
     * @return true if date is today or in the future
     */
    private boolean isDateValid(@NotNull LocalDate departureDate) {

        return departureDate.equals(LocalDate.now()) || departureDate.isAfter(LocalDate.now());
    }
}