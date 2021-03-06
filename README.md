## Project Title

user management project

## Getting Started

This instruction will give some details insight of how to install, run and test the Top Score Ranking sample project. Both Unit tests and Integration tests have been executed on Postman and curl. Please have a look deployment note to know how to import and run the project and conduct test cases locally.

## Tools and Technologies

1.	Java 8 (1.8.0_211)
2.	Gradle 6 (6.7.1)
3.	IntelliJ ultimate edition 2020.3
4.	Spring  boot 2.3.2
5.	MySQL DB.
6.	JUnit 5 
7.	Postman (v.7.36.1)
8.	MacOS (Big Sur)

## Deployment

1.	Download / Clone “user management” from GitHub.
2.	Run IntelliJ and “open” project from IntelliJ
3.	Right click on “build.gradle” and click on “build”. If any problem, please close and open the project to ensure build is performed properly.
4.	Create a MySQL DB “playscore” and create a table “score”. Table columns details are described on “DB Table” section.
5.	Right click on “UserManagementApplication.java” class under “src -> main -> java -> com -> sample -> user” packages and click on “run”. Application will be started on local tomcat with port 8080.
8. lombok may be required to enable annotation in IntelliJ

## DB Table

DB Name: users

Table Name: score

| Column Name | Data Type | Size      | Null        |Default     | Auto Increment | Primary Key | Comments |
| ----------- | ----------| ----------| ----------- |----------- | -----------    | ----------- | -----------|
| id      | varchar       | 20      | -           |-           | Yes            | Yes         |  -          |
| password         | varchar       |    20     | -           |0           | -              | --          |   -         |
| name	        | Text   |100      | Yes         |-           | -              |-            | -           |
| commetns	| Text  |-        | -           |-            | -              |-            | CURRENT_DATETIME|
| delete_flg	| tinyint   |1        | -           |0| -              |-            |        -    | -             |

“application.property” file contains the configuration information for DB setup. Please change password if needed. Property file resides inside resource package.

## API Documentation

### 1.	Sample API endpiont

METHOD  :	POST

URL	    : `http://localhost:8080/users/{user_id`



```

## Note
1.	Trying to keep simplicity of code to follow K.I.S.S

#### Thank you very much for your time!!



