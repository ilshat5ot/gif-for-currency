FROM openjdk:11
COPY build/libs/gif-for-currency-0.0.1-SNAPSHOT.jar alfatest.jar
ENTRYPOINT ["java","-jar","/alfatest.jar"]