package com.example.myAirlineFlightservice.services;

import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.AbstractEntity;
import com.example.myAirlineFlightservice.repositories.AbstractRepository;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Super class for all services related to an {@link AbstractEntity}.
 * 
 * @since 0.0.1
 */
@Validated
@Getter
@AllArgsConstructor
public abstract class AbstractService<E extends AbstractEntity> {
    
    private AbstractRepository<E> repository;

    /** For error messages. */
    private String entityName;


    /**
     * Find entity by name.
     * 
     * @param name of the entity
     * @return the entity
     * @throws IllegalStateException if not found.
     */
    public E getByName(@NotBlank String name) {
        
        return repository.findByName(name).orElseThrow(() ->
            new IllegalStateException("Could not find " + entityName + ": " + name + "."));
    }


    /**
     * Find out if at least one entity with the given name exists.
     * 
     * @param name of the entity
     * @return true if at least one entity exists
     */
    public boolean exists(@NotBlank String name) {

        return repository.existsByName(name);
    }


    /**
     * Delete entity by given name.
     * 
     * @param name of the entity
     * @throws IllegalStateException if not found.
     */
    public void delete(@NotBlank String name) {

        // should exist
        getByName(name);
      
        repository.deleteByName(name);
    }
}