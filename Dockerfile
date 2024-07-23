FROM openjdk:17-alpine

ADD target/stock-data-0.0.1-SNAPSHOT.jar stock-data.jar

CMD ["java", "-jar", "stock-data.jar"]