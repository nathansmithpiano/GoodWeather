# GoodWeather

- Gradle version 7.4.2
- Build script DSL: groovy
- JDK Compliance Level 1.8
- JPA version 2.2
- JPA implementation type: disable library configuration

## TESTING

#### Primary Keys: strings vs int ids

- Sample URI: https://api.weather.gov/points/39.9884,-105.2336 (48 characters)
- Testing String: https://temp.resource.loc/type/stringentity/ index from loop
    - DB: varchar(75) to allow for longer URIs
- Testing ints: auto-increment
    - DB: contents TEXT for both, approx. 500 characters of Lorem

1. Creating entities
- 1,000,000 simple entities
- int entities: 3 min 6 sec
- string entities: 3 min 16 sec
- difference: 10 sec (negligible)
