## Project Title

Top Score Ranking

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

1.	Download / Clone “Score Project” from GitHub.
2.	Run IntelliJ and “open” project from IntelliJ
3.	Right click on “build.gradle” and click on “build”. If any problem, please close and open the project to ensure build is performed properly.
4.	Create a MySQL DB “playscore” and create a table “score”. Table columns details are described on “DB Table” section.
5.	Right click on “GameApplication.java” class under “src -> main -> java -> com -> sample -> play” packages and click on “run”. Application will be started on local tomcat with port 8080.
6.	To run Unit test, please right click on “ScoreServiceTests” class under “test -> java -> com -> sample -> play” packages and click on “run”. Also, individual test case can be run inside the “ScoreServiceTests” file.
7.	To run Controller Integration test, please right click on “ScoreControllerIntegrationTest” class under “test -> java -> com -> sample -> play” packages and click on “run”.
8. lombok may be required to enable annotation in IntelliJ

## DB Table

DB Name: playscore

Table Name: score

| Column Name | Data Type | Size      | Null        |Default     | Auto Increment | Primary Key | Comments |
| ----------- | ----------| ----------| ----------- |----------- | -----------    | ----------- | -----------|
| score_id      | int       | 11      | -           |-           | Yes            | Yes         |  -          |
| score         | int       |         | -           |0           | -              | --          |   -         |
| name	        | Varchar   |100      | Yes         |-           | -              |-            | -           |
| created_at	| DateTime  |-        | -           |-            | -              |-            | CURRENT_DATETIME|
| delete_flg	| tinyint   |1        | -           |0| -              |-            |        -    | -             |

“application.property” file contains the configuration information for DB setup. Please change password if needed. Property file resides inside resource package.

## API Documentation

### 1.	GET Score by ID

METHOD  :	POST

URL	    : `http://localhost:8080/play/score/`

Comment : Requirement document describes that request and response should be delivered on payload.  So, method is POST and request id will be given in payload.

Request Json : 
```
{
   "id": 1
}
```

Response Json :
```
{
    "code": 200,
    "errCode": null,
    "messages": [
        "OK"
    ],
    "errItems": null,
    "result": {
        "scoreId": 1,
        "name": "Rashed",
        "score": 500
    }
}
```

### 2.   Create Score

METHOD  :	POST

URL	    : `http://localhost:8080/play/score/create`

Request Json : 
```
{
   "player" : "Rashed",
    "score" : 10,
    "createdAt": "2020-12-28 12:40:50"
}
```

Response Json :
```
{
    
    "code": 200,
    "errCode": null,
    "messages": [
        "OK"
    ],
    "errItems": null,
    "result": null
}
```

### 3.   Delete Score

METHOD  :	DELETE

URL	    : `http://localhost:8080/play/score/delete`

Request Json : 
```
{
   "id" : 100
}
```

Response Json :
```
{
     "code": 200,
    "errCode": null,
    "messages": [
        "OK"
    ],
    "errItems": null,
    "result": null
}
```

### 4.   Get Score List

METHOD  :	POST  

URL	    : `http://localhost:8080/play/score/list?pageNum=1&pageSize=10`

Comment : Partial value can be inputted. 

Request Json : 
```
{
   "player" : ["rash", "tes","Har"],
    "beforeTime" : "2022-02-12",
    "afterTime" : "2019-12-12"
}
```

Response Json :
```
{
     "code": 200,
    "errCode": null,
    "messages": [
        "OK"
    ],
    "errItems": null,
    "result": {
        "pageNum": 1,
        "pageSize": 10,
        "totalPage": 1,
        "total": 6,
        "list": [
            {
                "scoreId": 1,
                "name": "Rashed",
                "score": 500
            },
            {
                "scoreId": 3,
                "name": "Rashed",
                "score": 300
            },
            {
                "scoreId": 5,
                "name": "Rashed",
                "score": 270
            },
            {
                "scoreId": 6,
                "name": "Rashed",
                "score": 10
            },
            {
                "scoreId": 8,
                "name": "Rashed",
                "score": 10
            },
            {
                "scoreId": 100,
                "name": "testDelete",
                "score": 300
            }
        ]
    }
}
```

### 5.  Player Score History

METHOD  :	POST

URL	    : `http://localhost:8080/play/score/history`

Comment : Partial value can be inputted. 

Request Json : 
```
{
   "player" : "rashed"
}
```

Response Json :
```
{
     "code": 200,
    "errCode": null,
    "messages": [
        "OK"
    ],
    "errItems": null,
    "result": {
        "topScore": 500,
        "topScoreTime": "2020-12-25 00:00:00",
        "lowScore": 10,
        "lowScoreTime": "2020-12-28 12:40:50",
        "averageScore": 218,
        "allScores": [
            500,
            300,
            270,
            10,
            10
        ],
        "player": "rashed"
    }
}
```

## Note
1.	To keep simplicity auto increment number is used as id starting from 1. In use case, UUID might be used for ID.
2.	Trying to keep simplicity of code to follow K.I.S.S
3.	DateTime format “yyyy-MM-dd hh:mm:ss” is implemented.
4.	In sample project, player name is used to fetch player score history. In real project it is assumed that player ID will be used to fetch history and other relevant cases.
5.	Integration test conducted on existing score table. No separate configuration files or tables are used due to sample project.

#### Thank you very much for your time!!



