# Distribute Systems Project

Distributed System that lets users interact with a Web Client GUI built with Spring Boot to do CRUD operations for a Car Hire Booking System.

The RESTful Server that is being consumed by the Client was built in JAX-RS Jersey and is connected to a database over RMI that stores all of the Car Hire Bookings.

This is a college project for the Distributed Systems module in GMIT.

## Installtation & Setup

Follow the steps below to get the Project setup on your local machine.

* First you will need to clone the repository

  ```
  git clone https://github.com/lanodburke/Distributed-Systems-Project.git
  ```
  
* After cloning the repo, change directory to the Distributed-Systems-Project folder.

  ```
  cd Distributed-Systems-Project
  ```
  
* Next you will need to setup the MySQL database by running the following command into a MySQL console
  ````
  source carbookingsystem.sql
  ````
  
* Next to setup the RMIDatabase run the database-service.jar file:
  ````
  java -jar database-service.jar
  ````
* To run the JAX-RS Jersey REST API you will need to move the booking-server.war file into your tomcat installation, the following steps will describe how to do this on a Mac.

  ````
  mv booking-server.war /Library/Tomcat/webapss/
  ````
* To run the Server, start tomcat. 
  ````
  cd /Library/Tomcat/bin/
  ````
  
  ````
  ./startup.h
  ````
  
* To run the Web Client, open eclipse and import the WebClient project into eclipse. Make sure you have Spring Boot installed first. Spring boot installation [here](http://spring.io/projects/spring-boot).

* Next right click on the prject and click ```Run as```. Select the Spring Boot App run configuration.

* Next navigate to the WebClient by typing the following into the browser:

  ```
  http://localhost:9090/
  ```

* Next to run the desktop client, follow the steps below:

  ```
  java -jar desktop-client.jar
  ```

## Project Requirements

* Simple Web Client (Java JSP/Servlet or .NET equivalent if preferred.

  * Built a Web Client with Spring Boot, allows the user to View all Bookings, Customer, Vehicles in the database and do CRUD operations on each.

* RESTful Web Service (JAX-RS/Jersey

  * Built a Web Service that follows the RESTful architecutre standard in Jersey. 
  
* Data Modelling

  * Built a SQL configuration file to setup the database
  
  * Built a XSD schema file to build the model files used for the whole project
  
* RMI Database Server
  * Built an RMI datbase Server with MySQL as the datbase used. Can make remote method invocations to do CRUD operations on the database

## Extras
* Desktop Client
  * Made a simple Desktop client in java that can be run in the terminal
  
* Deployable jar files
  * Deployable jar and war files for desktop-client, database-service and booking-server

## Authors

* **Donal Burke** - [lanodburke](https://github.com/lanodburke)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
