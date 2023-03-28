-- Airline
INSERT INTO airline(id, name) VALUES(1, 'Ryanair');
INSERT INTO airline(id, name) VALUES(2, 'Wizz Air');
INSERT INTO airline(id, name) VALUES(3, 'Turkish Airlines');
INSERT INTO airline(id, name) VALUES(4, 'Qatar Airways');
INSERT INTO airline(id, name) VALUES(5, 'Lufthansa');
INSERT INTO airline(id, name) VALUES(6, 'Eurowings');


-- Airport
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
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date) VALUES(1, 1, "Wizz Air", "Hamburg airport", "Munich airport", "10:00:00", "13:00:00", "2023-04-01", "2023-04-01");
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date) VALUES(2, 2, "Ryan Air", "Dortmund airport", "Munich airport", "13:00:00", "15:00:00", "2023-04-09", "2023-04-09");
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date) VALUES(3, 3, "Lufthansa", "Munich airport", "Hannover airport", "15:00:00", "17:00:00", "2023-04-23", "2023-04-23");
INSERT INTO flight(id, number, airline_name, departure_airport_name, arrival_airport_name, departure_time, arrival_time, departure_date, arrival_date) VALUES(4, 4, "Turkish Airlines", "Hannover airport", "Frankfurt airport", "17:00:00", "19:00:00", "2023-04-30", "2023-04-30");
