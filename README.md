# Springboot
Repo about springboot that is use for learning

## To Do List (1.0)
- [ ] Install Java
- [ ] Install Maven
- [ ] Start Spring Boot App

## Summary

### Starting Springboot
#### 1. You can start with [**Spring Initializr**](https://start.spring.io/)
- Select the neccessary project version and metadata
- Add your basic depedencies. Recommended starter dependencies
  - Lombok (for code generator like setter, getter, toString, etc)
  - Spring Data JPA (Database model)
  - MySql Driver (Database connection for MySql)
  - Validation (Validator for request, etc)

#### 2. Boot up your project
- After you create your starter project using [**Spring Initializr**](https://start.spring.io/) you can open it using your IDEA. In this case I was using [**Intellij Community 2021.3.1**](https://www.jetbrains.com/idea/download/#section=linux)
- When you first open up the project you can try to run the app by running the Application class, but it won't run because your JPA was asking for a connection to a database which I didn't set up yet.
  - To mitigate this you can comment you JPA dependeny in your **_pom.xml_**
- So now we try to run and it suppose to run, but if it doesn't it might because of some reason below.
  - You dont have the dependacy for **_Spring boot starter web_**, if it so you can add <br/>
    > `<dependency>` \
    `<groupId>org.springframework.boot</groupId>` \
    `<artifactId>spring-boot-starter-web</artifactId>` \
    `</dependency>`
    
    to your **_pom.xml_** in dependency section
- When your app is already running you can access it via your browser with the default url [**localhost:8080**](http://localhost:8080/)

## References
1. [Spring Boot Tutorial | Full Course [2021] [NEW]](https://youtu.be/9SGDpanrc8U)
