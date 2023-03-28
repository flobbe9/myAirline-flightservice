package com.example.myAirlineFlightservice.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class representing a flight.
 * 
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Flight {

    @Id
    @GenericGenerator(name = "flight_id_generator", 
                    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                    parameters = {
                        @Parameter(name ="initial_value", value = "2")
                    })
    @GeneratedValue(generator = "flight_id_generator")
    private Long id;

    @NotNull(message = "Number cannot be null.")
    @Min(value = 0, message = "Number cannot be negative.")
    @Column(unique = true, updatable = false)
    @EqualsAndHashCode.Include
    private Long number;

    @NotBlank(message = "Cannot leave airlineName blank.")
    private String airlineName;

    @NotBlank(message = "Cannot leave departureAirportName blank.")
    private String departureAirportName;

    @NotBlank(message = "Cannot leave arrivalAirportName blank.")
    private String arrivalAirportName;

    @NotNull(message = "DepartureTime cannot be null.")
    private LocalTime departureTime;

    @NotNull(message = "ArrivalTime cannot be null.")
    private LocalTime arrivalTime;

    @NotNull(message = "DepartureDate cannot be null.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @NotNull(message = "ArrivalDate cannot be null.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;


    @Override
    public String toString() {

        return this.number.toString();
    }
}