# GoodWeather

- Gradle version 7.4.2
- Build script DSL: groovy
- JDK Compliance Level 1.8
- JPA version 2.2
- JPA implementation type: disable library configuration

## TESTING (db_tests branch)

#### Primary Keys: strings vs int ids

- Sample URI: https://api.weather.gov/points/39.9884,-105.2336 (48 characters)
- Testing String: https://temp.resource.loc/type/stringentity/ index from loop
    - DB: varchar(75) to allow for longer URIs
- Testing ints: auto-increment
    - DB: contents TEXT for both, approx. 500 characters of Lorem

1. Creating entities
- 1,000,000 simple entities
- difference: 5.37%
- STORAGE: String entities + 300 mb **(59% larger)**

2. Retrieving entities
- 100,000 of each, random ids added to HashSet
- difference: 1.33%

**RESULTS**
- Difference is negligible for the scale of this application.
- Storage of data is required with either PK setup.
- Storage would actually be LESS with String ids as an additional column is not needed.
- **Normalization** is important but **string vs int PK** is not for this application.
