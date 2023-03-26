package com.example.myAirlineFlightservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.myAirlineFlightservice.models.Airport;


@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    
    Optional<Airport> findByName(String name);

    Boolean existsByName(String name);

    @Transactional
    void deleteByName(String name);
}