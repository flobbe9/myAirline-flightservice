FROM gradle:jdk17-alpine

WORKDIR /app/flightservice

COPY . /app/flightservice/

ENTRYPOINT gradle bootRun