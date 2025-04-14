<<<<<<< HEAD
# SQLAndHibernate
=======
# SQL and Hibernate Projects

This repository contains two practical works demonstrating different approaches to database interaction in Java.

## Project Structure

The repository consists of two main projects:
1. `PracticalWorkNumberOne` - JDBC-based implementation
2. `PracticalWorkNumberTwo` - Hibernate ORM implementation

## Practical Work #1: JDBC Implementation

This project demonstrates direct database interaction using JDBC (Java Database Connectivity). It connects to a MySQL database and performs analysis of course subscription data.

### Key Features:
- Direct SQL query execution
- Connection management using JDBC
- Analysis of course subscription patterns
- Calculation of subscription coefficients

### Technologies Used:
- Java
- JDBC
- MySQL Connector/J
- MySQL Database

## Practical Work #2: Hibernate Implementation

This project showcases the use of Hibernate ORM (Object-Relational Mapping) for database operations. It includes a more complex data model with relationships between entities.

### Key Features:
- Object-Relational Mapping using Hibernate
- Entity relationships (One-to-Many, Many-to-One)
- Automatic schema updates
- Complex data model implementation

### Technologies Used:
- Java
- Hibernate ORM
- MySQL Connector/J
- Lombok for boilerplate code reduction
- MySQL Database

### Entity Model:
- Course
- Student
- Teacher
- Subscription
- Purchase
- LinkedPurchaseList

## Database Configuration

Both projects connect to a MySQL database with the following settings:
- Database name: skillbox
- Username: root
- Password: testtest
- Host: localhost
- Port: 3306

## Requirements
- Java 19
- MySQL Server
- Maven for dependency management

## Getting Started

1. Ensure MySQL server is running
2. Create a database named 'skillbox'
3. Import the required schema
4. Update database credentials in hibernate.cfg.xml if needed
5. Run the projects using Maven

## License

This project is open source and available for educational purposes.
>>>>>>> 9441cb9 (new project is ready)
