### Project Title

Top Score Ranking

### Getting Started

This instruction will give some details insight of how to install, run and test the Top Score Ranking sample project. Both Unit tests and Integration tests have been executed on Postman and curl. Please have a look deployment note to know how to import and run the project and conduct test cases locally.

### Tools and Technologies

1.	Java 8 (1.8.0_211)
2.	Gradle 6 (6.7.1)
3.	IntelliJ ultimate edition 2020.3
4.	Spring  boot 2.3.2
5.	MySQL DB.
6.	JUnit 5 
7.	Postman (v.7.36.1)
8.	MacOS (Big Sur)

### Deployment

1.	Download / Clone “Score Project” from GitHub.
2.	Run IntelliJ and “open” project from IntelliJ
3.	Right click on “build.gradle” and click on “build”. If any problem, please close and open the project to ensure build is performed properly.
4.	Create a MySQL DB “playscore” and create a table “score”. Table columns details are described on “DB Table” section.
5.	Right click on “GameApplication.java” class under “src -> main -> java -> com -> sample -> play” packages and click on “run”. Application will be started on local tomcat with port 8080.
6.	To run Unit test, please right click on “ScoreServiceTests” class under “test -> java -> com -> sample -> play” packages and click on “run”. Also, individual test case can be run inside the “ScoreServiceTests” file.
7.	To run Controller Integration test, please right click on “ScoreControllerIntegrationTest” class under “test -> java -> com -> sample -> play” packages and click on “run”.

### DB Table

DB Name: playscore

Table Name: score

| Syntax      | Description | Syntax      | Description |Syntax      | Description | Syntax      | Description |Description |
| ----------- | ----------- | ----------- | ----------- |----------- | ----------- | ----------- | ----------- |----------- |
| Header      | Title       | Header      | Title       |Header      | Title       | Header      | Title       |Title       |
| Paragraph   | Text        |Paragraph   | Text        |Paragraph   | Text        |Paragraph   | Text        |Text        |


|Column Name|Data Type|	Size |Null |Default|Auto Increment |Primary key |Comments|
|---|---|---|---|---|---|---|---|---|
|score_id   | Int	11	 		


score	int		 	0			
name	Varchar	100	 
			
created_at	datetime		 	CURRENT_TIMESTAMP			
delete_flg	tinyInt	1	 	0			


====
[source,java]
----
include::complete/src/main/java/com/example/restservice/GreetingController.java[]
----
====

This controller is concise and simple, but there is plenty going on under the hood. We
break it down step by step.

The `@GetMapping` annotation ensures that HTTP GET requests to `/greeting` are mapped to the `greeting()` method.

NOTE: There are companion annotations for other HTTP verbs (e.g. `@PostMapping` for POST). There is also a `@RequestMapping` annotation that they all derive from, and can serve as a synonym (e.g. `@RequestMapping(method=GET)`).

`@RequestParam` binds the value of the query string parameter `name` into the `name`
parameter of the `greeting()` method. If the `name` parameter is absent in the request,
the `defaultValue` of `World` is used.

The implementation of the method body creates and returns a new `Greeting` object with
`id` and `content` attributes based on the next value from the `counter` and formats the
given `name` by using the greeting `template`.

A key difference between a traditional MVC controller and the RESTful web service
controller shown earlier is the way that the HTTP response body is created. Rather than
relying on a view technology to perform server-side rendering of the greeting data to
HTML, this RESTful web service controller populates and returns a `Greeting` object. The
object data will be written directly to the HTTP response as JSON.

This code uses Spring
https://docs.spring.io/spring/docs/{spring_version}/javadoc-api/org/springframework/web/bind/annotation/RestController.html[`@RestController`]
annotation, which marks the class as a controller where every method returns a domain
object instead of a view. It is shorthand for including both `@Controller` and
`@ResponseBody`.

The `Greeting` object must be converted to JSON. Thanks to Spring's HTTP message converter
support, you need not do this conversion manually. Because
https://github.com/FasterXML/jackson[Jackson 2] is on the classpath, Spring's
https://docs.spring.io/spring/docs/{spring_version}/javadoc-api/org/springframework/http/converter/json/MappingJackson2HttpMessageConverter.html[`MappingJackson2HttpMessageConverter`]
is automatically chosen to convert the `Greeting` instance to JSON.

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/spring-boot-application-new-path.adoc[]

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/build_an_executable_jar_subhead.adoc[]

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/build_an_executable_jar_with_both.adoc[]

Logging output is displayed. The service should be up and running within a few seconds.


== Test the Service

Now that the service is up, visit `http://localhost:8080/greeting`, where you should see:

====
[source,json]
----
{"id":1,"content":"Hello, World!"}
----
====

Provide a `name` query string parameter by visiting
`http://localhost:8080/greeting?name=User`. Notice how the value of the `content`
attribute changes from `Hello, World!` to `Hello, User!`, as the following listing shows:

====
[source,json]
----
{"id":2,"content":"Hello, User!"}
----
====

This change demonstrates that the `@RequestParam` arrangement in `GreetingController` is
working as expected. The `name` parameter has been given a default value of `World` but
can be explicitly overridden through the query string.

Notice also how the `id` attribute has changed from `1` to `2`. This proves that you are
working against the same `GreetingController` instance across multiple requests and that
its `counter` field is being incremented on each call as expected.

== Note

Congratulations! You have just developed a RESTful web service with Spring.
