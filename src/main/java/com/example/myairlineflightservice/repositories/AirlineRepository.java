package com.example.myAirlineFlightservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirlineFlightservice.models.Airline;


/**
 * Repository interface for {@link Airline}. Extends {@link AbstractRepository}.
 */
@Repository
public interface AirlineRepository extends AbstractRepository<Airline>, JpaRepository<Airline, Long> {
    
}