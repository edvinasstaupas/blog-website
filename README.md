Blog webpage using Spring Boot
=============================


## Instructions on how to run this project:

First of all, clone the repository:

`git clone https://github.com/edvinasstaupas/SPRING-blog-website.git`

## CLI approach

If you would like to run the program from IntelliJ jump to [Gui approach](#gui-approach)

If not, run program from `/blog` directory:

In Linux/MacOs/Git Bash:

`./gradlew bootRun`

In Windows PowerShell:

`.\gradlew bootRun`

Now you have a working blog website:sparkler::sparkler:

Now keep in mind that default profile is 'dev' which comes with h2 database
If you'd like to use not in-memmory database, first you will need to create database and change `application-prod.yaml` properties file (checkout .

After initializing database, use 

In Linux/MacOs/Git Bash:

`./gradlew bootRun --args='--spring.profiles.active=prod'`

In Windows PowerShell:

`.\gradlew bootRun --args='--spring.profiles.active=prod'`

## Gui approach

If you would like to work with GUI, latter commands can be avoided by using IntelliJ IDE. 

To run program with default properties, run `BlogApplication` method `main`

If you'd like to use profiles, change run configuration Active profiles section to your wanted profile:

![image](https://user-images.githubusercontent.com/73701414/136576959-9ae55ee7-9e6e-4878-9541-8ef8acd1b8a1.png)

###And that's it, enjoy blogging:postal_horn:
