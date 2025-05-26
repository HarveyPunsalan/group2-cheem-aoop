-- Creates tables for employee requests, overtime, and leave in the MotorPH Payroll System

USE motorph_payroll_db;

-- Table: request
CREATE TABLE IF NOT EXISTS request (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    request_date DATE,
    reason VARCHAR(255),
    request_status VARCHAR(50),
    approved_by INT,
    created_date TIMESTAMP
) AUTO_INCREMENT = 10000;

-- Table: overtime
CREATE TABLE IF NOT EXISTS overtime (
    overtime_id INT AUTO_INCREMENT PRIMARY KEY,
    request_id INT,
    employee_id INT,
    dtr_id INT
) AUTO_INCREMENT = 10000;

-- Table: employee_leave
CREATE TABLE IF NOT EXISTS employee_leave (
    leave_request_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    leave_type VARCHAR(50),
    start_date DATE,
    end_date DATE,
    total_days DECIMAL(5,2)
) AUTO_INCREMENT = 10000;
