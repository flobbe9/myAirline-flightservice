package com.example.myAirlineFlightservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirlineFlightservice.models.City;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    
    Optional<City> findByName(String name);
}