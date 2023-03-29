package com.example.myAirlineFlightservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirlineFlightservice.models.City;

import jakarta.transaction.Transactional;


@Repository
public interface CityRepository extends AbstractRepository<City>, JpaRepository<City, Long> {

    boolean existsByCountryName(String countryName);
    
    @Transactional
    void deleteAllByCountryName(String countryName);

    List<City> findAllByCountryName(String countryName);
}