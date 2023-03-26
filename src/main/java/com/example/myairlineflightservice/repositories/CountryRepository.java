package com.example.myAirlineFlightservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirlineFlightservice.models.Country;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    
    Optional<Country> findByName(String name);
}