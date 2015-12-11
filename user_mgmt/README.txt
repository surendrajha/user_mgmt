# User Manage Sample Web App
# Technologies Used: Spring, Hibernate, JSTL, Maven, JSP, Bootstrap, MySQL

# Prerequisite:
1. Jdk 1.7
2. Maven
3. MYSql Database
4. Tomcat 7
5. Extract the Zip file and (E.g., C:\user_mgmt)


# Database
1. Find "user_db.sql" and run script to create database.
2. Check the database creadentials. Go to "\user_mgmt\src\main\resources\jdbc.properties". Change If required.

# Compile:
1. Open command prompt and type command "cd C:\user_mgmt" and hit enter
2. Hit command "mvn clean package", it will download all the required jars.
Note: If Build Fails then plz try to run the command again.


# Run (Feeling lazy, try using command prompt itself)
1. Hit command "mvn jetty:run".
Note: If Build Fails then plz try to run the command again.
2. Try to hit the url "http://localhost:8080/user_mgmt".


# Run (Using eclipse)
1. Import project using "Existing Maven Project"
2. Clean and Build the project.
3. Run on Tomcat 7
4. Try to hit the url "http://localhost:8080/user_mgmt".



NOTE: If you find any issue/dificulty to compile/run the project,
please feel free to contact me
Surendra Jha
surendra.jha@outlook.com
+919907465516
