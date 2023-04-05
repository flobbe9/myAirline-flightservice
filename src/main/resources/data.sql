-- Airline
INSERT INTO airline(id, name) VALUES(1, 'Ryanair');
INSERT INTO airline(id, name) VALUES(2, 'Wizz Air');
INSERT INTO airline(id, name) VALUES(3, 'Turkish Airlines');
INSERT INTO airline(id, name) VALUES(4, 'Qatar Airways');
INSERT INTO airline(id, name) VALUES(5, 'Lufthansa');
INSERT INTO airline(id, name) VALUES(6, 'Eurowings');


-- Airport
-- DONT ADD SECOND AIRPORT IN MUNICH!!! Test wont work otherwise.
INSERT INTO airport(id, name, city_name) VALUES(1, 'Berlin airport', 'Berlin');
INSERT INTO airport(id, name, city_name) VALUES(2, 'Dortmund airport', 'Dortmund');
INSERT INTO airport(id, name, city_name) VALUES(3, 'Frankfurt airport', 'Frankfurt');
INSERT INTO airport(id, name, city_name) VALUES(4, 'Hamburg airport', 'Hamburg');
INSERT INTO airport(id, name, city_name) VALUES(5, 'Hannover airport', 'Hannover');
INSERT INTO airport(id, name, city_name) VALUES(6, 'Muenchner airport', 'Muenchen');
INSERT INTO airport(id, name, city_name) VALUES(7, 'Dresden airport', 'Dresden');
INSERT INTO airport(id, name, city_name) VALUES(8, 'Duesseldorf airport', 'Duesseldorf');
INSERT INTO airport(id, name, city_name) VALUES(9, 'Erfurt airport', 'Erfurt');
INSERT INTO airport(id, name, city_name) VALUES(10, 'Essen airport', 'Essen');
INSERT INTO airport(id, name, city_name) VALUES(11, 'Friedrichshafen airport', 'Friedrichshafen');
INSERT INTO airport(id, name, city_name) VALUES(12, 'Koeln airport', 'Koeln');
INSERT INTO airport(id, name, city_name) VALUES(13, 'Mannheim airport', 'Mannheim');
INSERT INTO airport(id, name, city_name) VALUES(14, 'Nuernberg airport', 'Nuernberg');
INSERT INTO airport(id, name, city_name) VALUES(15, 'Stuttgart airport', 'Stuttgart');


-- City
INSERT INTO city(id, name, country_name) VALUES(1, 'Berlin', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(2, 'Dortmund', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(3, 'Frankfurt', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(4, 'Hamburg', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(5, 'Hannover', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(6, 'Muenchen', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(7, 'Dresden', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(8, 'Duesseldorf', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(9, 'Erfurt', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(10, 'Essen', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(11, 'Friedrichshafen', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(12, 'Koeln', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(13, 'Mannheim', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(14, 'Nuernberg', 'Germany');
INSERT INTO city(id, name, country_name) VALUES(15, 'Stuttgart', 'Germany');


-- Country
INSERT INTO country(id, name) VALUES(1, 'Germany');


-- Flight
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(1, 1, 'Wizz Air', 'Hamburg airport', 'Muenchner airport', '10:00', '13:00', CURDATE(), CURDATE(), 12.0, 50, 15, 15, 10, 90);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(2, 2, 'Ryan Air', 'Dortmund airport', 'Muenchner airport', '13:00', '15:00', CURDATE(), CURDATE(), 12.0, 50, 15, 15, 10, 90);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(3, 3, 'Lufthansa', 'Muenchner airport', 'Hannover airport', '15:00', '17:00', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(4, 4, 'Turkish Airlines', 'Hannover airport', 'Frankfurt airport', '22:00', '23:00', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);

INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(5, 5, 'Turkish Airlines', 'Berlin airport', 'Dortmund airport', '20:00', '22:00', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(6, 6, 'Wizz Air', 'Berlin airport', 'Dortmund airport', '20:30', '22:30', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(7, 7, 'Ryan Air Airlines', 'Berlin airport', 'Dortmund airport', '21:00', '23:00', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(8, 8, 'Qatar Airways', 'Berlin airport', 'Dortmund airport', '21:30', '23:30', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);

INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(9, 9, 'Turkish Airlines', 'Muenchner airport', 'Hamburg airport', '20:00', '22:00', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(10, 10, 'Wizz Air', 'Muenchner airport', 'Hamburg airport', '20:30', '22:30', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(11, 11, 'Ryan Air Airlines', 'Muenchner airport', 'Hamburg airport', '21:00', '23:00', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(12, 12, 'Qatar Airways', 'Muenchner airport', 'Hamburg airport', '21:30', '23:30', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);

INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(13, 13, 'Turkish Airlines', 'Dresden airport', 'Muenchner airport', '20:00', '22:00', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(14, 14, 'Wizz Air', 'Dresden airport', 'Muenchner airport', '20:30', '22:30', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(15, 15, 'Ryan Air Airlines', 'Dresden airport', 'Muenchner airport', '21:00', '23:00', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(16, 16, 'Qatar Airways', 'Dresden airport', 'Muenchner airport', '21:30', '23:30', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);

INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(17, 17, 'Turkish Airlines', 'Hannover airport', 'Nuernberg airport', '20:00', '22:00', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(18, 18, 'Wizz Air', 'Hannover airport', 'Nuernberg airport', '20:30', '22:30', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(19, 19, 'Ryan Air Airlines', 'Hannover airport', 'Nuernberg airport', '21:00', '23:00', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(20, 20, 'Qatar Airways', 'Hannover airport', 'Nuernberg airport', '21:30', '23:30', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);

INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(21, 21, 'Turkish Airlines', 'Erfurt airport', 'Koeln airport', '20:00', '22:00', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(22, 22, 'Wizz Air', 'Erfurt airport', 'Koeln airport', '20:30', '22:30', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(23, 23, 'Ryan Air Airlines', 'Erfurt airport', 'Koeln airport', '21:00', '23:00', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(24, 24, 'Qatar Airways', 'Erfurt airport', 'Koeln airport', '21:30', '23:30', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);

INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(25, 25, 'Turkish Airlines', 'Friedrichshafen airport', 'Mannheim airport', '20:00', '22:00', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(26, 26, 'Wizz Air', 'Friedrichshafen airport', 'Mannheim airport', '20:30', '22:30', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(27, 27, 'Ryan Air Airlines', 'Friedrichshafen airport', 'Mannheim airport', '21:00', '23:00', CURDATE(), CURDATE(), 12.0, 40, 10, 10, 10, 70);
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date, base_price, num_normal_seats, num_corridor_seats, num_window_seats, num_foot_room_seats, num_available_seats) VALUES(28, 28, 'Qatar Airways', 'Friedrichshafen airport', 'Mannheim airport', '21:30', '23:30', CURDATE(), CURDATE(), 30.0, 40, 10, 10, 10, 70);


-- flight_flight_classes
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(1, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(1, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(2, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(3, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(3, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(4, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(4, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(4, 'FIRST');

INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(5, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(5, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(5, 'FIRST');

INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(6, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(6, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(6, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(7, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(7, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(7, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(8, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(8, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(8, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(9, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(9, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(9, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(10, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(10, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(10, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(11, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(11, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(11, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(12, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(12, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(12, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(13, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(13, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(13, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(14, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(14, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(14, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(15, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(15, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(15, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(16, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(16, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(16, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(17, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(17, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(17, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(18, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(18, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(18, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(19, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(19, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(19, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(20, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(20, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(20, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(21, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(21, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(21, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(22, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(22, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(22, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(23, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(23, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(23, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(24, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(24, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(24, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(25, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(25, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(25, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(26, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(26, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(26, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(27, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(27, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(27, 'FIRST');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(28, 'ECONOMY');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(28, 'BUSINESS');
INSERT INTO flight_flight_classes(flight_id, flight_classes) VALUES(28, 'FIRST');