package com.example.myAirlineFlightservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test class for {@link Flight}.
 * 
 * @since 0.0.1
 */
public class FlightTest {

    Flight flight;

    @BeforeEach
    void setUp() {

        this.flight = new Flight(5l, 
                                6l, 
                                "Mock Airline", 
                                "Some airport", 
                                "Some other airport", 
                                LocalTime.of(10, 0), 
                                LocalTime.of(13, 0), 
                                LocalDate.of(3000, 4, 1), 
                                LocalDate.of(3000, 4, 1), 
                                new HashSet<>(Set.of(FlightClass.ECONOMY)), 
                                30.0, 
                                40, 
                                10, 
                                10, 
                                10, 
                                70);
    }
    
    
    @Test
    void reduceNumSeats_shouldReduceByOne() {

        // values before reduction
        int numNormalSeats = flight.getNumNormalSeats();
        int numAvailableSeats = flight.getNumAvailableSeats();

        flight.reduceNumSeats(SeatType.NORMAL);

        assertEquals(numNormalSeats - 1, flight.getNumNormalSeats());        
        assertEquals(numAvailableSeats - 1, flight.getNumAvailableSeats());        
    }


    @Test
    void isSeatAvailable_shouldNotbeAvailable() {
        
        // should be available
        assertTrue(flight.isSeatAvailable(SeatType.NORMAL));

        flight.setNumNormalSeats(0);

        // should not be available
        assertFalse(flight.isSeatAvailable(SeatType.NORMAL));
    }


    @Test
    void isBookedOut_shouldBeBookedOut() {
        
        // should not be booked out
        assertFalse(flight.isBookedOut());

        flight.setNumAvailableSeats(0);

        // should be booked out
        assertTrue(flight.isBookedOut());
    }
}