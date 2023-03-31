package com.example.myAirlineFlightservice.repositories;

import java.util.Optional;

import com.example.myAirlineFlightservice.models.AbstractEntity;
import jakarta.transaction.Transactional;


/**
 * Parent interface for all repositories with an {@link AbstractEntity}.
 * 
 * @since 0.0.1
 */
public interface AbstractRepository<E extends AbstractEntity> {

    Optional<E> findByName(String name);

    Boolean existsByName(String name);

    @Transactional
    void deleteByName(String name);
}