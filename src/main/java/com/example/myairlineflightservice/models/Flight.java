package com.example.myAirlineFlightservice.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * Class representing a country.
 * 
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Flight {

    @Id
    @GenericGenerator(name = "flight_id_generator", 
                    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                    parameters = {
                        @Parameter(name ="initial_value", value = "1")
                    })
    @GeneratedValue(generator = "flight_id_generator")
    private Long id;

    @NotNull
    private LocalTime departureTime;

    @NotNull
    private LocalTime arrivalTime;

    @NotNull
    private LocalDate departureDate;

    @NotNull
    private LocalDate arrivalDate;



    
    public Flight() {}
}