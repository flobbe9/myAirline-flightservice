package com.example.myAirlineFlightservice.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.myAirlineFlightservice.models.Flight;
import com.example.myAirlineFlightservice.models.FlightClass;
import com.example.myAirlineFlightservice.models.FlightDetails;
import com.example.myAirlineFlightservice.models.SeatType;


/**
 * Test class for {@link FlightService}. 
 * <p>
 * Depends on the mock data from resources/data.sql. Uses Hannover and flight 
 * number 4 as test subject.
 * 
 * @since 0.0.1
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class FlightServiceTest {
    
    @Autowired
    FlightService flightService;

    Flight mockFlight;
    long mockFlightNumber = 6l;

    FlightDetails flightDetails;


    @BeforeEach
    void setUp() {

        this.mockFlight = new Flight(5l, 
                                    mockFlightNumber, 
                                    "Mock Airline", 
                                    "Hannover airport", 
                                    "Hannover airport", 
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

        this.flightDetails = new FlightDetails(4l, 
                                               SeatType.NORMAL, 
                                               35.0, 
                                               FlightClass.ECONOMY, 
                                               null);
    }


    @Test
    @Order(0)
    void getAllByDepartureDate_shouldThrowInvalidDate() {

        assertThrows(IllegalStateException.class, () -> flightService.getAllByDepartureDate(LocalDate.of(1900, 4, 1)));
    }


    @Test
    @Order(0)
    void getAllByAirport_shouldNotFindAirport() {

        assertThrows(IllegalStateException.class, () -> flightService.getAllByAirport("super random airport"));
    }


    @Test
    @Order(0)
    void update_shouldNotFindFlight() {
        assertThrows(IllegalStateException.class, () -> flightService.update(mockFlight));
    }


    @Test
    @Order(0)
    void updateAllByAirportName_shouldNotFindAirport() {

        assertThrows(IllegalStateException.class, () -> flightService.updateAllByAirportName("oldAirport", "newAirport"));
    }

    @Test
    @Order(5)
    void delete_shouldNotFindFlight() {
        
        assertThrows(IllegalStateException.class, () -> flightService.delete(mockFlightNumber));
    }


    @Test 
    @Order(1)
    void save_shouldSaveMockFlight() {

        flightService.save(mockFlight);

        assertTrue(flightService.exists(mockFlightNumber));
    }
    

    @Test
    @Order(2)
    void save_shouldThrowDuplicateFlight() {

        assertThrows(IllegalStateException.class, () -> flightService.save(mockFlight));
    }

    
    @Test 
    @Order(2)
    void getAllByDepartureDate_shouldFindOneFlight() {

        assertEquals(1, flightService.getAllByDepartureDate(mockFlight.getDepartureDate()).size());
    }


    @Test
    @Order(2)
    void update_shouldUpdateMockFilght() {

        String newMockAirportName = "New mock airport";
        mockFlight.setDepartureAirportName(newMockAirportName);
        
        flightService.update(mockFlight);

        assertTrue(flightService.existsByAirport(newMockAirportName));
    }


    @Test
    @Order(3)
    void updateAllByAirportName_shouldUpdate() {

        String oldAirport = "Frankfurt airport";
        String newerAirportName = "Even newer airport";

        assertTrue(flightService.existsByAirport(oldAirport));
        
        flightService.updateAllByAirportName(oldAirport, newerAirportName);

        assertTrue(flightService.existsByAirport(newerAirportName));
    }


    @Test
    @Order(4)
    void delete_shouldDeleteMockFlight() {
        
        flightService.delete(mockFlightNumber);

        assertFalse(flightService.exists(mockFlightNumber));
    }


    @Test
    void book_shouldThrowNumberInvalid() {


        // flightDetails should be valid
        assertDoesNotThrow(() -> flightService.book(flightDetails));

        // set non-existing number
        flightDetails.setNumber(10000l);

        assertThrows(IllegalStateException.class, () -> flightService.book(flightDetails));
    }

    
    @Test
    void book_shouldThrowBookedOut() {

        Flight flight = flightService.getByNumber(flightDetails.getNumber());
        int numAvailableSeats = flight.getNumAvailableSeats();
        
        // flightDetails should be valid
        assertDoesNotThrow(() -> flightService.book(flightDetails));
        
        // set num available seats to 0
        flight.setNumAvailableSeats(0);
        flightService.update(flight);

        assertThrows(IllegalStateException.class, () -> flightService.book(flightDetails));

        // clean up
        flight.setNumAvailableSeats(numAvailableSeats);
        flightService.update(flight);
    }
    
    
    @Test
    void book_shouldThrowSeatTypeNotAvailable() {

        Flight flight = flightService.getByNumber(flightDetails.getNumber());
        int numNormalSeats = flight.getNumNormalSeats();

        // flightDetails should be valid
        assertDoesNotThrow(() -> flightService.book(flightDetails));

        // set num available seats to 0
        flight.setNumNormalSeats(0);
        flightService.update(flight);

        assertThrows(IllegalStateException.class, () -> flightService.book(flightDetails));

        // clean up
        flight.setNumNormalSeats(numNormalSeats);
        flightService.update(flight);
    }


    @Test
    void book_shouldUpdateFlight() {
        
        Flight flight = flightService.getByNumber(flightDetails.getNumber());

        // old values
        int numNormalSeatsOld = flight.getNumNormalSeats();
        int numAvailableSeatsOld = flight.getNumAvailableSeats();

        flightService.book(flightDetails);

        // get updated flight
        flight = flightService.getByNumber(flightDetails.getNumber());

        // new values
        int numNormalSeatsNew = flight.getNumNormalSeats();
        int numAvailableSeatsNew = flight.getNumAvailableSeats();

        // should be one less
        assertEquals(numNormalSeatsOld - 1, numNormalSeatsNew);
        assertEquals(numAvailableSeatsOld - 1, numAvailableSeatsNew);
    }
}