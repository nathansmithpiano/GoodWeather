# GoodWeather

- Gradle version 7.4.2
- Build script DSL: groovy
- JDK Compliance Level 1.8
- JPA version 2.2
- JPA implementation type: disable library configuration

## TESTING

#### Primary Keys: strings vs int ids

- Sample URI: https://api.weather.gov/points/39.9884,-105.2336 (48 characters)
- Testing String: 50 random characters
    - DB: varchar(75) to allow for longer URIs
- Testing ints: auto-increment
    - DB: contents TEXT for both
