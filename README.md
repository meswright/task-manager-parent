## Build Requirements
Java JDK version 21 installed.
Maven version 3.14.1 installed.
Ensure both Java and Maven are in your PATH.
## Build Instructions
Clone the codebase.
Open a terminal in the top directory of the project and run `mvn clean install`.
see the JARs in:
- `[...]/task-manager-parent/task-manager-ui/target`
- `[...]/task-manager-parent/task-manager-ui/target`
## Run Instructions
Ensure that ports 8080 and 8081 are free on the machine running this app.
For each JAR, run `java -jar  [JAR FILE NAME GOES HERE]`
on a web browser, visit `http://localhost:8080/task-manager/ui/home.jsf`
## If there was more time
### - UI does not handle errors in the backend.
When the backend throws non-2xx http responses, the UI should show a useful error message / redirection to an error page.
### - The UI needs character limits on the fields.
### - REST API on backend needs Swagger API Integration.
### - More Unit tests, also integration tests.
### - Backend to be configurable for a standalone database.
The h2 in-memory database is ok for small proof of concepts, but needs to be put into a development spring profile, with production pointing to something like PostgeSQL.
### - Pagination and filtering.
If there are a high amount of tasks, pagination will stop a user from potentially requesting an unnecessary amount of tasks at once (not very performative) Spring provides this quite well. Just needed to time to understand and implement it.
### - User management.
At the moment everyone can see all tasks. To improve, tasks could have another field for task owner (or task owners?). The current implemented screen could then be put behind a login screen. Maybe a separate function to "give" tasks to others.
### - Different frontend.
I am not a frontend developer and choosing Primefaces (JSF) was a case of choosing the only technology I (barely) know. React is seems widely used, but I heard htmx is lightweight and seems suitable for this project.
### - Maybe a Task Business object?
In the backend I do not like the direct mapping between the DTO and the entity. a "plain, business" Task should of been put between them architecturally speaking. However, because there was not really any business logic, there was no value-add with the project as this size, so I decided against it.
### - Task Status Improvement
The status field does not feel right, but was not sure what typical use may be. If the status progression of tasks is known and rigid, then it could be some kind of enum and we enforce state transitions between status as necessary. For more complexity the user/team could define their on status workflow from within the app itself.