package com.example.myAirlineFlightservice.services;

import org.springframework.validation.annotation.Validated;

import com.example.myAirlineFlightservice.models.AbstractEntity;
import com.example.myAirlineFlightservice.repositories.AbstractRepository;

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


    public boolean exists(@NotBlank String name) {

        return repository.exists(name);
    }


    public void delete(@NotBlank String name) {
      
        repository.deleteByName(name);
    }
}