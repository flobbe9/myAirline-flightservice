package com.example.myAirlineFlightservice.services;

import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.AbstractEntity;
import com.example.myAirlineFlightservice.repositories.AbstractRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Validated
@Getter
@AllArgsConstructor
public abstract class AbstractService<E extends AbstractEntity> {
    
    private AbstractRepository<E> repository;

    private String entityName;


    public E getByName(@NotBlank String name) {
        
        return repository.findByName(name).orElseThrow(() ->
            new IllegalStateException("Could not find " + entityName + ": " + name + "."));
    }


    public E save(@Valid E entity) {

        String name = entity.getName();

        // should not already exist
        if (exists(name))
            throw new IllegalStateException("Failed to save entity: " + name + ". Entity already exists.");

        return repository.save(entity);
    }


    public E getById(@Min(1) long id) {

        return repository.findById(id).orElseThrow(() ->
            new IllegalStateException("Could not find " + entityName + " with id: " + id + "."));
    }


    public boolean exists(@NotBlank String name) {

        return repository.existsByName(name);
    }


    public void delete(@NotBlank String name) {
      
        repository.deleteByName(name);
    }
}