package com.example.myAirlineFlightservice.services;

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

import com.example.myAirlineFlightservice.models.Country;


/**
 * Test class for {@link CountryService}. 
 * <p>
 * Depends on the mock data from resources/data.sql.
 * 
 * @since 0.0.1
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    Country germany;
    String germanyName;

    Country mockCountry;
    String mockCountryName;


    @BeforeEach
    void setUp() {

        this.germany = new Country("Germany");
        this.germany.setId(1l);
        this.germanyName = germany.getName();

        this.mockCountry = new Country("Mock country");
        this.mockCountry.setId(2l);
        this.mockCountryName = mockCountry.getName();
    }


    @Test
    @Order(0)
    void save_shouldFindDuplicate() {

        assertThrows(IllegalStateException.class, () -> countryService.save(germany));
    }


    @Test
    @Order(0)
    void delete_shouldNotFindMockCountry() {

        assertThrows(IllegalStateException.class, () -> countryService.delete(mockCountryName));
    }


    @Test
    @Order(1)
    void save_shouldSaveMockCountry() {
        
        countryService.save(mockCountry);
        
        assertTrue(countryService.exists(mockCountryName));
    }


    @Test
    @Order(1)
    void delete_shouldFindRelatedEntities() {

        assertThrows(IllegalStateException.class, () -> countryService.delete(germanyName));
    }


    @Test
    @Order(2)
    void delete_shouldDeleteMockCountry() {

        countryService.delete(mockCountryName);

        assertFalse(countryService.exists(mockCountryName));
    }
}