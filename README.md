
# Lotto

Lottery application I am currently working on for educational purposes.

It is being developed using the Test Driven Development process. It is based on a modular architecture. I used the JUnit library to create unit tests and Spring MockMVC for integration tests. 
I used the non-relational database MongoDB. For communication over HTTP, I built a REST API in Spring Boot. The application and database are hosted using docker images . 



## Scenario

![App Screenshot](https://bucko.com.pl/img/projects/screen_6.jpg)
![App Screenshot](https://bucko.com.pl/img/projects/screen_5.jpg)

## REST API
#### @PostMapping("/inputNumbers") 
To participate in the draw enter six numbers.
The response returns the date of the drawing and the individual coupon number.

![App Screenshot](https://bucko.com.pl/img/projects/POST_inputNumbers.png)

#### @PostMapping - Error message
If the numbers entered are out of range or repeated, an error message is returned in response.  

![App Screenshot](https://bucko.com.pl/img/projects/POST_error.png)


#### @GetMapping("/winners/{uniqueId}")
To check your winnings, you need to enter your individual coupon number. In response, the drawn numbers and information about the winnings are returned. 

![App Screenshot](https://bucko.com.pl/img/projects/GET_winners.png)

#### @GetMapping - Error message
If the drawing has not yet taken place or an incorrect coupon number has been entered, an error message is returned in response. 

![App Screenshot](https://bucko.com.pl/img/projects/GET_error.png)


## Tech Stack
- Java 18
- Maven
- Spring Boot

Data Bases
- MongoDB

Unit Test
- JUnit
- Mockito
- AssertJ

Integration Tests
- Testcontainers
- Spring MockMVC

Deployment
- Docker
