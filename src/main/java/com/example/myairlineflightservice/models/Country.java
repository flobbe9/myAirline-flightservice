package com.example.myAirlineFlightservice.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class representing a country.
 * 
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Country extends AbstractEntity {
    
    @Id
    @GenericGenerator(name = "country_id_generator", 
                      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                      parameters = {
                        @Parameter(name ="initial_value", value = "2")
                      })
    @GeneratedValue(generator = "country_id_generator")
    private Long id;


	public Country(String name) {
		super(name, "country");
	}
}