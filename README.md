# College Placement Tracker

A Java Swing-based college placement management system integrated with MySQL using JDBC. The application provides an interactive interface to view, search, filter, and analyze college placement records.

## Features

- Admin login interface
- View all placement records
- Filter students by placement status
- Search students by name
- Search students by company
- Filter placements by package range
- Find highest package offered
- Calculate average package for selected students
- View participating companies and their package details
- MySQL database integration using JDBC

## Technologies Used

- Java
- Java Swing
- MySQL
- JDBC
- SQL

## Database

The project uses a MySQL database named `college_placement_management`.

Main tables include:

- `students`
- `companies`
- `applications`
- `selections`
- `placements`

The `college_placement.sql` file contains the database schema, sample company data, and SQL queries.

## Project Structure

```text
college-placement-tracker/
│
├── PlacementGUI.java
├── college_placement.sql
└── README.md

##How to Run
1. Install Java JDK and MySQL.
2. Create the database using college_placement.sql.
3. Add the MySQL Connector/J JDBC driver to the Java project.
4. Update the MySQL password in PlacementGUI.java.
5. Compile and run PlacementGUI.java.
6. Login using the demo credentials.
##Demo Login
Username: admin
Password: admin123

##Project Highlights
This project demonstrates Java GUI development, JDBC-based database connectivity, SQL queries, data filtering, searching, and basic placement analytics.

