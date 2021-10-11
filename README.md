# **Blog webpage using Spring Boot**

## Here are **instructions** on how to run this project:

First of all, clone the repository:

`git clone https://github.com/edvinasstaupas/SPRING-blog-website.git`

Then run program from `/blog` directory:

In Linux/MacOs/Git Bash:

`./gradlew bootRun`

In Windows PowerShell:

`.\gradlew bootRun`

And now you have a working blog website

Now keep in mind that default profile is 'dev' which comes with h2 database
If you'd like to use PostgreSQL database, first you will need to create database and change `application-prod.yaml` properties file.

After initializing database, use 

In Linux/MacOs/Git Bash:

`./gradlew bootRun --args='--spring.profiles.active=prod'`

In Windows PowerShell:

`.\gradlew bootRun --args='--spring.profiles.active=prod'`

These commands can be avoided by using IntelliJ IDE. 

To run program with default properties, run `BlogApplication` method `main`

If you'd like to use profiles, change run configuration Active profiles section to your wanted profile:

# **Blog webpage using Spring Boot**

## Here are **instructions** on how to run this project:

First of all, clone the repository:

`git clone https://github.com/edvinasstaupas/blog-spring-example-MAIN-PROJECT.git`

Then generate gradle wrapper jar in project directory

`gradlew bootRun`

And now you have a working blog website

Now keep in mind that default profile is 'dev' which comes with h2 database
If you'd like to use PostgreSQL database, first you will need to create database and change `application-prod.yaml` properties file.

After initializing database, use 

`gradlew bootRun --args='--spring.profiles.active=prod'`

These commands can be avoided by using IntelliJ IDE. 

To run program with default properties, run `BlogApplication` method `main`

If you'd like to use profiles, change run configuration Active profiles section to your wanted profile:

![image](https://user-images.githubusercontent.com/73701414/136576959-9ae55ee7-9e6e-4878-9541-8ef8acd1b8a1.png)

#### This project is my main source of Spring learning.

#### Here I am using most of my knowledge regarding web development with Spring.


## Check out dev branch to see full history of developing this project

#### This project is my main source of Spring learning.

#### Here I am using most of my knowledge regarding web development with Spring.
