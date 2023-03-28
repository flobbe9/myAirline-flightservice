package com.example.myAirlineFlightservice.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class representing an airport.
 * 
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Airport extends AbstractEntity {
    
    @Id
    @GenericGenerator(name = "airport_id_generator", 
                      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                      parameters = {
                        @Parameter(name ="initial_value", value = "7")
                      })
    @GeneratedValue(generator = "airport_id_generator")
    private Long id;

    @NotBlank(message = "CityName cannot be blank.")
	  @Column(updatable = false)
    private String cityName;

	
	public Airport(String name, String cityName) {
		super(name, "airport");
		this.cityName = cityName;
	}
}