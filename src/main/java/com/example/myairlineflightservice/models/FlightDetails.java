package com.example.myAirlineFlightservice.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Wrapper class used to exchange only neccessary information on a flight between front-end
 * and back-end.
 * 
 * @since 0.0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FlightDetails {
    
    @Min(value = 1, message = "Id cannot be negative.")
    private long id;

    @NotNull(message = "Seat type cannot be null.")
    private SeatType seatType;

    @DecimalMin(value = "0.0", message = "Luggage fee cannot be negative.")
    private double luggageFee;

    @NotNull(message = "Flight class cannot be null.")
    private FlightClass flightClass;

    @DecimalMin(value = "0.0", message = "Price total cannot be negative.")
    private Double priceTotal;
}