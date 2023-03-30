package com.example.myAirlineFlightservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.myAirlineFlightservice.models.Airline;


/**
 * Test class for {@link AirlineService}. Also servers as test class for {@link AbstractService} 
 * with AirlineService as implementation.
 * <p>
 * Depends on the mock data from resources/data.sql.
 * 
 * @since 0.0.1
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class AirlineServiceTest {
    
    @Autowired
    AirlineService airlineService;

    Airline ryanAir;
    

    Airline mockAirline;
    
    
    @BeforeEach
    void setUp() {

        this.ryanAir = new Airline("Ryanair");
        this.ryanAir.setId(1l);

        this.mockAirline = new Airline("Mockair");
        this.mockAirline.setId(7l);
    }
    
    
    @Test
    @Order(0)
    void exists_shouldFindAirline() {

        assertTrue(airlineService.exists(ryanAir.getName()));
        assertFalse(airlineService.exists(mockAirline.getName()));
    }
    

    @Test
    @Order(1)
    void save_shouldSaveAirline() {

        assertEquals(mockAirline, airlineService.save(mockAirline));
    }

    
    @Test
    @Order(2)
    void delete_shouldNotFindAirlineAfterwards() {

        airlineService.delete(mockAirline.getName());
        
        assertFalse(airlineService.exists(mockAirline.getName()));
    }

    
    @Test
    void getById_shouldFindAirline() {

        assertEquals(ryanAir, airlineService.getById(ryanAir.getId()));
    }


    @Test
    void getByName_shouldFindAirline() {

        assertEquals(ryanAir, airlineService.getByName(ryanAir.getName()));
    }


    @Test
    void save_shouldNotSaveDuplicateAirline() {

        assertThrows(IllegalStateException.class, () -> airlineService.save(ryanAir));
    }
}