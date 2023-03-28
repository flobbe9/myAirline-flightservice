package com.example.myAirlineFlightservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.myAirlineFlightservice.models.Airport;


@Repository
public interface AirportRepository extends AbstractRepository<Airport>, JpaRepository<Airport, Long> {
    
    @Transactional
    void deleteAllByCityName(String cityName);

    List<Airport> findAllByCityName(String cityName);
}