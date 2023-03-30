FROM gradle:jdk17-alpine

ADD / .

ENTRYPOINT gradle bootRun