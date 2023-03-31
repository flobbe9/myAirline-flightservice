package com.example.myAirlineFlightservice.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirlineFlightservice.models.Flight;

import jakarta.transaction.Transactional;


/**
 * Repository interface for {@link Flight}.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    
    Optional<Flight> findByNumber(long number);

    boolean existsByNumber(long number);
    
    boolean existsByDepartureAirportNameOrArrivalAirportName(String departureAirportName, String arrivalAirportName);
    
    List<Flight> findAllByDepartureTimeAfter(LocalTime departureTime);
    
    List<Flight> findAllByArrivalTimeBefore(LocalTime arrivalTime);
    
    List<Flight> findAllByDepartureTimeAfterAndArrivalTimeBefore(LocalTime departureTime, LocalTime arrivalTime);
    
    List<Flight> findAllByDepartureDate(LocalDate departureDate);
    
    List<Flight> findAllByDepartureAirportNameOrArrivalAirportName(String departureAirportName, String arrivalAirportName);
    
    List<Flight> findAllByDepartureAirportNameAndArrivalAirportName(String departureAirportName, String arrivalAirportName);

    List<Flight> findAllByBasePriceLessThanEqual(double basePrice);

    List<Flight> findAllByNumAvailableSeatsGreaterThanEqual(int numSeatsAvailable);

    @Transactional
    void deleteByNumber(long number);

    @Transactional
    void deleteAllByDepartureAirportNameOrArrivalAirportName(String departureAirportName, String arrivalAirportName);
}