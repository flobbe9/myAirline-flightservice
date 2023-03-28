package com.example.myAirlineFlightservice.repositories;

import java.util.Optional;

import com.example.myAirlineFlightservice.models.AbstractEntity;
import jakarta.transaction.Transactional;


public interface AbstractRepository<E extends AbstractEntity> {

    // Object save(Object entity);

    // Optional<E> findById(long id);

    Optional<E> findByName(String name);

    Boolean existsByName(String name);

    @Transactional
    void deleteByName(String name);
}