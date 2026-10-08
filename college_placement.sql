CREATE DATABASE college_placement_management;
USE college_placement_management;

CREATE TABLE admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100),
    email VARCHAR(100),
    cgpa DECIMAL(3,2),
    branch VARCHAR(20),
    skills VARCHAR(200)
);

CREATE TABLE companies (
    company_id INT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(100),
    role_name VARCHAR(100),
    package_lpa DECIMAL(5,2),
    min_cgpa DECIMAL(3,2)
);

INSERT INTO companies(company_name,role_name,package_lpa,min_cgpa) VALUES
('TCS','System Engineer',4,6.0),
('Infosys','Digital Specialist Engineer',6.5,6.5),
('Wipro','Project Engineer',5.5,6.0),
('Accenture','Application Development Associate',8,6.5),
('Cognizant','Programmer Analyst',6.75,6.5),
('Capgemini','Software Engineer',7.5,6.5),
('IBM','Associate System Engineer',9,7.0),
('Deloitte','Analyst',12,7.0),
('Amazon','SDE',18,8.0),
('Google','Software Engineer',25,8.5);

CREATE TABLE applications (
    application_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    company_id INT,
    status VARCHAR(30),
    applied_date DATE,
    FOREIGN KEY(student_id) REFERENCES students(student_id),
    FOREIGN KEY(company_id) REFERENCES companies(company_id)
);

CREATE TABLE selections (
    selection_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    company_id INT,
    package_offered DECIMAL(5,2),
    FOREIGN KEY(student_id) REFERENCES students(student_id),
    FOREIGN KEY(company_id) REFERENCES companies(company_id)
);

CREATE TABLE placements (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100),
    branch VARCHAR(20),
    company_applied VARCHAR(100),
    status VARCHAR(30),
    package_lpa DECIMAL(5,2)
);

SELECT * FROM placements;

SELECT COUNT(*) AS total_students
FROM placements;

SELECT * FROM placements
WHERE status = 'Selected';

SELECT * FROM placements
WHERE status = 'Rejected';

SELECT * FROM placements
WHERE status = 'Applied';

SELECT MAX(package_lpa) AS highest_package
FROM placements;

SELECT company_applied,
COUNT(*) AS total_students
FROM placements
GROUP BY company_applied;

SELECT branch,
COUNT(*) AS total
FROM placements
GROUP BY branch;

SHOW DATABASES;

DESC placements;

DESC companies;

SELECT * FROM companies;
