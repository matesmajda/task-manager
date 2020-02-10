# task-manager

This project is a simple task manager. I followed the YAGNI principle during development, so there are lots of possible improvements of the software. I included some of them in the last section of this README.

### Requirements

You need Java 8 installed to run this project.
You can replace gradle commands with `gradlew.bat` in Windows or `./gradlew` in Linux if you don't have Gradle installed.

### Running the application

`gradle bootRun`

### Running the tests
`gradle test`

### Usage

- The application exposes REST interfaces that are documented on `http://localhost:8080/swagger-ui.html`
- DB web console access: `http://localhost:8080/h2-console`
     - Driver Class: `org.h2.Driver`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - User Name: `sa`
     - Password: (leave it empty)


### Possible improvements
- Improve exception messages
- Create integration tests
- Handling DB exceptions
- Improve concurrency control: add locking to entities
- Make entities soft deletables
- Add JPA auditing to entities
- Add input sanitization to prevent XSS attacks