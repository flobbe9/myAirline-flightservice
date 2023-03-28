package com.example.myAirlineFlightservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirlineFlightservice.models.Country;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, AbstractRepository<Country> {
    
}