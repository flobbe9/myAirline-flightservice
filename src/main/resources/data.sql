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