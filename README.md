# Steps to run locally

1. mvn clean package --file service-1/pom.xml -DskipTests
2. mvn clean package --file service-2/pom.xml -DskipTests
3. docker compose up --build