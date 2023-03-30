package com.example.myAirlineFlightservice.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.ConnectException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.ResourceAccessException;

import com.example.myAirlineFlightservice.models.Airport;
import com.example.myAirlineFlightservice.repositories.FlightRepository;


/**
 * Test class for {@link AirportService}. 
 * <p>
 * Depends on the mock data from resources/data.sql.
 * 
 * @since 0.0.1
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class AirportServiceTest {

    @Autowired
    AirportService airportService;

    @Autowired
    FlightRepository flightRepository;

    Airport munichAirport;
    String munichAirportName;

    Airport mockAirport;
    String mockAirportName;


    @BeforeEach
    void setUp() {

        this.munichAirport = new Airport("Munich airport", "Munich");
        this.munichAirport.setId(6l);
        this.munichAirportName = munichAirport.getName();

        this.mockAirport = new Airport("Mock airport", "Mock city");
        this.mockAirport.setId(7l);
        this.mockAirportName = mockAirport.getName();
    }


    @Test
    @Order(0)
    void save_shouldNotFindCity() {
        
        assertThrows(IllegalStateException.class, () -> airportService.save(mockAirport));
    }
        

    @Test
    @Order(0)
    void update_shouldNotFindAirport() {
        
        assertThrows(IllegalStateException.class, () -> airportService.update(mockAirport));
    }

    
    @Test
    @Order(1)
    void save_shouldSaveMockAirport() {

        // set valid city
        mockAirport.setCityName("Munich");

        assertDoesNotThrow(() -> airportService.save(mockAirport));
    }
        

    @Test
    @Order(2)
    void delete_shouldFindRelatedEntites() {

        assertThrows(IllegalStateException.class, () -> airportService.delete(munichAirportName));
    }
            
    
    @Test
    @Order(3)
    void deleteAllByCityName_shouldFindRelatedEntites() {
        
        assertThrows(IllegalStateException.class, () -> airportService.deleteAllByCityName(munichAirportName));
    }


    @Test
    @Order(3)
    void update_shouldUpdateMockAirport() {

        // change mockAirport name
        String newAirportName = "NewName";
        mockAirport.setName(newAirportName);

        // catch exception from HttpRequestSender, when pipeline runs tests
        try {
            airportService.update(mockAirport);

        } catch (ResourceAccessException e) {
            mockAirport.setCityName("Berlin");
            airportService.save(mockAirport);
        }

        assertEquals(mockAirport, airportService.getByName(newAirportName));
    }

    
    @Test
    @Order(4)
    void delete_shouldNotFindAirportAfterwards() {
        
        String newAirportName = "NewName";
        airportService.delete(newAirportName);
        
        assertFalse(airportService.exists(newAirportName));
    }
    
    
    @Test
    @Order(4)
    void save_shouldFindDuplicateAirport() {
        
        assertThrows(IllegalStateException.class, () -> airportService.save(munichAirport));
    }


    @Test
    @Order(5)
    void deleteAllByCityName_shouldNotFindAirportAfterwards() {

        // delete related entites
        flightRepository.deleteAllByDepartureAirportNameOrArrivalAirportName(munichAirportName, munichAirportName);

        System.out.println(flightRepository.existsByDepartureAirportNameOrArrivalAirportName(munichAirportName, munichAirportName));
        airportService.deleteAllByCityName(munichAirport.getCityName());

        assertFalse(airportService.exists(munichAirportName));
    }

    
    @Test
    void update_shouldThrowIdNull() {
        
        // set id null
        mockAirport.setId(null);

        assertThrows(IllegalStateException.class, () -> airportService.update(mockAirport));
    }
    
    
    @Test
    void delete_shouldNotFindAirport() {
        
        assertThrows(IllegalStateException.class, () -> airportService.delete("Some non existent airport name."));
    }
    
    
    @Test
    void deleteAllByCityName_shouldNotFindCity() {
        
        assertThrows(IllegalStateException.class, () -> airportService.delete("Some non existent city name."));
    }
}
