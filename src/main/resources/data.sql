-- Airline
INSERT INTO airline(id, name) VALUES(1, 'Ryanair');
INSERT INTO airline(id, name) VALUES(2, 'Wizz Air');
INSERT INTO airline(id, name) VALUES(3, 'Turkish Airlines');
INSERT INTO airline(id, name) VALUES(4, 'Qatar Airways');
INSERT INTO airline(id, name) VALUES(5, 'Lufthansa');
INSERT INTO airline(id, name) VALUES(6, 'Eurowings');


-- Airport
-- DONT ADD SECOND AIRPORT FOR MUNICH!!! Test wont work otherwise.
INSERT INTO airport(id, name, city_name) VALUES(1, 'Berlin airport', 'Berlin');
INSERT INTO airport(id, name, city_name) VALUES(2, 'Dortmund airport', 'Dortmund');
INSERT INTO airport(id, name, city_name) VALUES(3, 'Frankfurt airport', 'Frankfurt');
INSERT INTO airport(id, name, city_name) VALUES(4, 'Hamburg airport', 'Hamburg');
INSERT INTO airport(id, name, city_name) VALUES(5, 'Hannover airport', 'Hannover');
INSERT INTO airport(id, name, city_name) VALUES(6, 'Munich airport', 'Munich');


-- City
INSERT INTO city(id, name, country_name) VALUES(1, 'Berlin', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(2, 'Dortmund', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(3, 'Frankfurt', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(4, 'Hamburg', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(5, 'Hannover', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(6, 'Munich', 'Germany');


-- Country
INSERT INTO country(id, name) VALUES(1, 'Germany');


-- Flight
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, seats_total) VALUES(1, 1, 'Wizz Air', 'Hamburg airport', 'Munich airport', '10:00', '13:00', '2023-04-01', '2023-04-01', 12.0, 50, 15, 15, 10, 90);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, seats_total) VALUES(2, 2, 'Ryan Air', 'Dortmund airport', 'Munich airport', '13:00', '15:00', '2023-04-09', '2023-04-09', 12.0, 50, 15, 15, 10, 90);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, seats_total) VALUES(3, 3, 'Lufthansa', 'Munich airport', 'Hannover airport', '15:00', '17:00', '2023-04-23', '2023-04-23', 30.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, seats_total) VALUES(4, 4, 'Turkish Airlines', 'Hannover airport', 'Frankfurt airport', '17:00', '19:00', '2023-04-30', '2023-04-30', 30.0, 40, 10, 10, 10, 70);


-- flight_flight_classes
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(1, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(1, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(2, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(3, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(4, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(4, 'BUSINESS');