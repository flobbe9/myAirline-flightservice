package com.example.myAirlineFlightservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Airline.class, name = "airline"),
    @JsonSubTypes.Type(value = Airport.class, name = "airport"),
    @JsonSubTypes.Type(value = City.class, name = "city"),
    @JsonSubTypes.Type(value = Country.class, name = "country"),
    @JsonSubTypes.Type(value = Flight.class, name = "flight")
})
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity {

    @NotBlank
    @Column(updatable = false)
    protected String name;


    @JsonIgnore
    @JsonProperty("type")
    protected String type;
}