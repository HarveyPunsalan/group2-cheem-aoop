-- Creates job, salary, and department-related tables for MotorPH Payroll System

USE motorph_payroll_db;

-- Table: department
CREATE TABLE IF NOT EXISTS department (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(50),
    department_head INT
) AUTO_INCREMENT = 1;

-- Table: job
CREATE TABLE IF NOT EXISTS job (
    job_id INT AUTO_INCREMENT PRIMARY KEY,
    job_title VARCHAR(50),
    department_id INT
) AUTO_INCREMENT = 10000;

-- Table: salary
CREATE TABLE IF NOT EXISTS salary (
    salary_id INT AUTO_INCREMENT PRIMARY KEY,
    salary_name VARCHAR(50),
    amount INT
) AUTO_INCREMENT = 10000;
