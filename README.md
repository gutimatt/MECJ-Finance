# MECJ Finance

## Video
https://youtu.be/oUD4TUd-4wE

## 1. Database
1. Create a new database using MySQL Workbench.
2. Run `SQLScripts/MECJ-Finance_6_create.sql` and `SQLScripts/MECJ-Finance_6_insert.sql` on your new database.

## 2. Java Program
1. The required MySQL Connector can be found in `lib/mysql-connector-java-8.0.28.jar`

2. Compile the project:
```
javac -d classes -cp classes src/ser322/*.java
```

3. Run the program:
```
java ser322.BudgetEngine <url> <user> <passwd> <driver>
```

## 3. Video URL
[Youtube]()

## Contributions
1. Matthew Gutierrez
    1. Code structure creation, repository management, INSERT method and walkthrough, and video compilation/upload
2. Eric Martinez
    1. SELECT, UPDATE methods and walkthrough
3. Chris Harrel
    1. Delete method, walkthrough, README.txt
4. John Kim
    1. PDF and Word doc containing requirements, ER diagram, and relational model