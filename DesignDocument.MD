# Design Document
### Author: Andrew Holden
## Language: Open JDK 17
I have chosen to use Java as the programming language for this demo as it is the language I am most familiar with for a project of this type. Specifically, Open JDK version 17.0.2 which can be found here: `https://jdk.java.net/17/` as it is the latest stable version available.
## Framework: SpringBoot
I have chosen SpringBoot as the framework for this demo as again I am familiar with it, and it is quick and easy to code with whilst providing several excellent libraries for security and various other things.
## Data Persistence: H2 in-memory database
I have chosen to use an in-memory h2 database as it is only a demo so data will not need to be persisted long term. Due to the ease of use of the SpringBoot framework this can easily be changed in the future if needed. If I were to create this project for real use, I would choose MySQL as the database persistence technology. It is very easy to use, well maintained, and widely used in the Software Engineering community. This provides a level of security as bugs and vulnerabilities are more likely to be discovered early and patched before they can be exploited.
## Dependencies
- **Data JPA**: This library makes data persistence a breeze. It provides a way to directly convert class objects to database entities. Also, it creates the database schema for you to save time.
- **Project Lombok**: This dependency is not essential, but I have chosen to include it as it removes the need for getters and setters. This cleans up your code by removing clutter and speeds up the development process.
- **Model Mapper**: I have included this library to help convert entities to data transfer objects with ease. This library helps to convert one class to another by mapping fields that have the same name. It changes long conversion methods into a simple easy to read one line of code.
## Shortcomings
- **Data Types**: I have used the Double data type to represent the price field of a product. This is because Double has sufficient accuracy for the intended purpose of this demo. This is because there are no complex calculations performed on the price just simple addition for a total. If a % discount or something along those lines was introduced then this could be changed to accommodate more precision.
- **Authentication/Authorisation**: I have not implemented stringent authentication and authorisation due to time constraints. I would have opted to use Spring Security to handle this as it provides several advantages. Spring security provides several password encoders to hash passwords before persisting them, it makes handling authorisation within an app easy, and as we have roles in this demo it is a perfect fit for separating what is allowed depending on the user type.
## Design Patterns
I have separated entities, requests and responses into three different packages in the model class. This adds a layer of security between the database repository and what is sent/received in the body of any requests. It is probably unnecessary for this project, however, it is good practice preventing unnecessary data being sent.
## Extras
I have included some basic manual tests that can be imported into postman. See `Hardware Demo.postman_collection.json`.