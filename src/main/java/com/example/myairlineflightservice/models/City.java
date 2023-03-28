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
 * Class representing a city.
 * 
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class City extends AbstractEntity {
    
  @Id
  @GenericGenerator(name = "city_id_generator", 
                    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                    parameters = {
                      @Parameter(name ="initial_value", value = "7")
                    })
  @GeneratedValue(generator = "city_id_generator")
  private Long id;
  
  @NotBlank(message = "CountryName cannot be blank.")
  @Column(updatable = false)
  private String countryName;


	public City(String name, String countryName) {
		super(name, "city");
		this.countryName = countryName;
	} 
}