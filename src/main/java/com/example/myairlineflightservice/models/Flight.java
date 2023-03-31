package com.example.myAirlineFlightservice.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.example.myAirlineFlightservice.annotations.ValidFlight;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class defining a flight.
 * 
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ValidFlight(groups = {ValidFlight.class})
public class Flight {

    @Id
    @GenericGenerator(name = "flight_id_generator", 
                    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                    parameters = {
                        @Parameter(name ="initial_value", value = "5")
                    })
    @GeneratedValue(generator = "flight_id_generator")
    private Long id;

    @NotNull(message = "Number cannot be null.", groups = {ValidFlight.class})
    @Min(value = 0, message = "Number cannot be negative.", groups = {ValidFlight.class})
    @Column(unique = true, updatable = false)
    @EqualsAndHashCode.Include
    private Long number;

    @NotBlank(message = "Cannot leave airlineName blank.", groups = {ValidFlight.class})
    private String airlineName;

    @NotBlank(message = "Cannot leave departureAirportName blank.", groups = {ValidFlight.class})
    private String departureAirportName;

    @NotBlank(message = "Cannot leave arrivalAirportName blank.", groups = {ValidFlight.class})
    private String arrivalAirportName;

    @NotNull(message = "DepartureTime cannot be null.", groups = {ValidFlight.class})
    private LocalTime departureTime;

    @NotNull(message = "ArrivalTime cannot be null.", groups = {ValidFlight.class})
    private LocalTime arrivalTime;

    @NotNull(message = "DepartureDate cannot be null.", groups = {ValidFlight.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @NotNull(message = "ArrivalDate cannot be null.", groups = {ValidFlight.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id")
    @NotEmpty(message = "Flight class cannot be emtpy.", groups = {ValidFlight.class})
    @Enumerated(EnumType.STRING)
    private Set<FlightClass> flightClasses;

    @NotNull(message = "Base price cannot be null.", groups = {ValidFlight.class})
    @Min(value = 0, message = "Base price cannot be negative.", groups = {ValidFlight.class})
    private double basePrice;

    @NotNull(message = "Number of normal seats cannot be null.", groups = {ValidFlight.class})
    @Min(value = 0, message = "Number of normal seats cannot be negative.", groups = {ValidFlight.class})
    private int numNormalSeats;

    @NotNull(message = "Number of corridor seats cannot be null.", groups = {ValidFlight.class})
    @Min(value = 0, message = "Number of corridor seats cannot be negative.", groups = {ValidFlight.class})
    private int numCorridorSeats;

    @NotNull(message = "Number of window seats cannot be null.", groups = {ValidFlight.class})
    @Min(value = 0, message = "Number of window seats cannot be negative.", groups = {ValidFlight.class})
    private int numWindowSeats;

    @NotNull(message = "Number of foot room seats cannot be null.", groups = {ValidFlight.class})
    @Min(value = 0, message = "Number of foot room seats cannot be negative.", groups = {ValidFlight.class})
    private int numFootRoomSeats;

    @NotNull(message = "Number of seats available cannot be null.", groups = {ValidFlight.class})
    @Min(value = 0, message = "Number of seats available cannot be negative.", groups = {ValidFlight.class})
    private int numAvailableSeats;


    @Override
    public String toString() {

        return this.number.toString();
    }
}