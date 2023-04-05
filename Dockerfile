FROM gradle:jdk17-alpine

WORKDIR /app/flightservice

ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/myAirline \
    SPRING_DATASOURCE_USERNAME=mysql \
    SPRING_DATASOURCE_PASSWORD=mysql \
    SPRING_JPA_HIBERNATE_DDL_AUTO=create

COPY . /app/flightservice/

ENTRYPOINT gradle bootRun