# task-manager

This project is a simple task manager.

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