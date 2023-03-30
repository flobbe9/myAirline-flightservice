package com.example.myAirlineFlightservice.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.myAirlineFlightservice.models.City;
import com.example.myAirlineFlightservice.models.Country;
import com.example.myAirlineFlightservice.repositories.AirportRepository;
import com.example.myAirlineFlightservice.repositories.CountryRepository;


/**
 * Test class for {@link CityService}. 
 * <p>
 * Depends on the mock data from resources/data.sql.
 * 
 * @since 0.0.1
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class CityServiceTest {

    @Autowired
    CityService cityService;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    CountryRepository countryRepository;

    City munich;
    String munichName;

    City mockCity;
    String mockCityName;


    @BeforeEach
    void setUp() {

        this.munich = new City("Munich", "Germany");
        this.munich.setId(6l);
        this.munichName = munich.getName();

        this.mockCity = new City("Mock city", "Mock country");
        this.mockCity.setId(7l);
        this.mockCityName = mockCity.getName();
    }


    @Test
    @Order(0)
    void save_shouldNotFindCity() {
        
        assertThrows(IllegalStateException.class, () -> cityService.save(mockCity));
    }

    
    @Test
    @Order(1)
    void save_shouldSaveMockCity() {

        // set valid city
        mockCity.setCountryName("Germany");

        assertDoesNotThrow(() -> cityService.save(mockCity));
    }
        

    @Test
    @Order(2)
    void delete_shouldFindRelatedEntities() {

        assertThrows(IllegalStateException.class, () -> cityService.delete(munichName));
    }
            
    
    @Test
    @Order(3)
    void deleteAllByCityName_shouldFindRelatedEntites() {
        
        assertThrows(IllegalStateException.class, () -> cityService.deleteAllByCountryName(munich.getCountryName()));
    }

    
    @Test
    @Order(4)
    void delete_shouldNotFindCityAfterwards() {
        
        cityService.delete(mockCityName);
        
        assertFalse(cityService.exists(mockCityName));
    }


    @Test
    @Order(5)
    void deleteAllByCountryName_shouldNotFindCityAfterwards() {

        // save mock country
        countryRepository.save(new Country(mockCity.getCountryName()));

        // delete related entites
        airportRepository.deleteAllByCityName(mockCityName);

        cityService.deleteAllByCountryName(mockCity.getCountryName());

        assertFalse(cityService.exists(mockCityName));
    }

    
    @Test
    void save_shouldFindDuplicateCity() {
        
        assertThrows(IllegalStateException.class, () -> cityService.save(munich));
    }
    
    
    @Test
    void delete_shouldNotFindCity() {
        
        assertThrows(IllegalStateException.class, () -> cityService.delete("Some non existent city name."));
    }
    
    
    @Test
    void deleteAllByCityName_shouldNotFindCity() {
        
        assertThrows(IllegalStateException.class, () -> cityService.delete("Some non existent city name."));
    }
}