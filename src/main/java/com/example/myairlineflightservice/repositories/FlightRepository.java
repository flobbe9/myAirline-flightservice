package com.example.myAirlineFlightservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirlineFlightservice.models.Flight;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    
}