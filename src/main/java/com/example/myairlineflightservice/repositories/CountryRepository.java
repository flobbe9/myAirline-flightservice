package com.example.myAirlineFlightservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirlineFlightservice.models.Country;


/**
 * Repository interface for {@link Country}. Extends {@link AbstractRepository}.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, AbstractRepository<Country> {
    
}