# Jackson Demo for Exception Reproduction

> This is a simple reproduction of a sample Spring Boot/JPA application to show that `@JsonIgnore` does not work with
> `java.util.Set`, but does with `java.util.List`, when using the current latest version of Jackson and Spring Boot.
>
> This project implements the `@ManyToMany` example on <https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/>.

## Github Issue

This reproduction is for the following issue: <https://github.com/FasterXML/jackson-databind/issues/2782>.

## Problem to Reproduce

* When using a `@ManyToMany` or `@OneToMany` annotation on a `List`, when using Jackson to serialize the owning entity,
  Jackson will recursively serialize the back-referenced entity and throw an exception.
* To fix this, you can add `@JsonIgnore` on the back part of the reference, as described in
  <https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion#json-ignore>
* This works when the back-reference is a `List`, but not a `Set`.
* You may want to use a `Set` to optimize Hibernate queries, as explained in
  [this vladmihalcea article](<https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/>).

## Technologies Used

* Maven 3.6.3 (bundled as wrapper)
* Java 13.0.2
* Spring Boot Starter Data JPA 2.3.1
* Spring Boot Starter Web 2.3.1
* H2 Database 1.4.200
* Lombok 1.18.12
* Jackson dependencies (transitive) 2.11.0
* IntelliJ 2020.1.2 Ultimate (if it matters)

## Installing and Running

### Pre-requisites

You must have Java 13 installed.

If using IntelliJ, you must have the [Lombok Plugin](https://plugins.jetbrains.com/plugin/6317-lombok) installed.

* In **File > Settings > Other Settings > Lombok Plugin**, ensure that **Enable Lombok plugin** is checked.
* In **File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors**, ensure that
  **Enable annotation processing** is checked.

### With IntelliJ

1. Import the root `pom.xml` as a Maven project and use default options.
1. Right-click `DemoApplication` in `src/main/java/com/example/demo` and click `Run DemoApplication`.
1. The application will build and run on <http://localhost:8080>.

### With Command Line

```bash
# Navigate to demo directory
cd C:\<path-to-demo-directory>\jackson-demo

# Build project
mvnw clean install -DskipTests

# Run application
java -jar target\demo-0.0.1-SNAPSHOT.jar
```

## Testing APIs

### API with `List` Bidirectional Mappings

1. Make a GET request to <http://localhost:8080/api/posts-with-list>.
1. This should return: 

    ```json
    [
      {
        "id": 1,
        "title": "Post 1",
        "tagsWithList": [
          {
            "id": 1,
            "name": "foo"
          },
          {
            "id": 2,
            "name": "bar"
          }
        ]
      }
    ]
    ```
   
### API with `Set` Bidirectional Mappings

1. Make a GET request to <http://localhost:8080/api/posts-with-set>.
1. It should return an HTTP 500 error page with the following error logged to the console:

    ```
    Resolved [org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON:
    Infinite recursion (StackOverflowError);
      nested exception is com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion (StackOverflowError)
        (through reference chain: java.util.ArrayList[0]->com.example.demo.models.PostWithSet["tagsWithSet"])]
    ```
   
## Running Unit Tests

### With IntelliJ

1. Right-click `PostControllerTest` in `src/test/java/com/example/demo/controllers` and click
   **Run 'PostControllerTest'**.

### With Command Line

```bash
mvnw test
```