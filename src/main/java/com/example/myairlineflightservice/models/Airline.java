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
 * Class defining an airline.
 * 
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Airline extends AbstractEntity {

	@Id
	@GenericGenerator(name = "airline_id_generator", 
					  strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
					  parameters = {
						@Parameter(name ="initial_value", value = "7")
					  }) 
	@GeneratedValue(generator = "airline_id_generator")    
	private Long id;


	public Airline(String name) {

		super(name, "airline");
	}


	@Override
	public String toString() {

		return super.name;
	}
}