# timeCapsule

An API for scheduling email delivery.

This project was developed in Java (Spring Boot) as a study of REST APIs, task scheduling, basic security, and email sending.

## Description

**timeCapsule** is a web application that allows users to write a message, specify a recipient email address, and schedule the message to be sent at a future date — like a digital "time capsule". Messages are stored in a local H2 database, and a daily routine checks and sends scheduled messages via email.

**Status**: Project under development, running locally for study purposes.

## Features

* REST API built with Java (Spring Boot)
* Simple frontend (HTML + JavaScript) for message creation
* Capsule storage using H2 database
* Double-layered data validation: the form submitted from the frontend generates a DTO (Data Transfer Object) that re-validates the data on the backend before persisting it
* Daily routine to check and send scheduled messages
* Automatic email sending on the scheduled date
* Request rate limiting per IP, controlled via a specific mechanism

## How to Run the Project

### Prerequisites:

* Java 11+
* Maven

### Clone the repository:

```bash
git clone https://github.com/RodrigoVzg/timeCapsule.git
cd timeCapsule
```

### Build and run the project:

```bash
mvn spring-boot:run
```

### Access the interfaces:

* Frontend: [http://localhost:8080/index.html](http://localhost:8080/index.html)
* H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## Tech Stack

* **Backend**: Java (Spring Boot)
* **Frontend**: HTML + JavaScript + CSS
* **Database**: H2 (in-memory/local)
* **Validation Layer**: Backend-side DTO to ensure data integrity
* **Security**: IP-based request rate limiting

## Notes

* There is no authentication/authorization (this is a study-focused project).
* Email sending requires prior SMTP configuration in the `application.properties` file.
* This project is not deployed in production.

## Contribution

Feel free to open issues or pull requests to suggest improvements or report bugs!

## License

This project is open and free for educational purposes.
