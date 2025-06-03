-- Creates pay period, payroll, and payslip-related tables for the MotorPH Payroll System

USE motorph_payroll_db;

-- Table: pay_period
CREATE TABLE IF NOT EXISTS pay_period (
    pay_period_id INT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    pay_day DATE,
    payroll_due DATE
) AUTO_INCREMENT = 1;

-- Table: payroll_record
CREATE TABLE IF NOT EXISTS payroll_record (
    payroll_id INT AUTO_INCREMENT PRIMARY KEY,
    pay_period_id INT,
    employee_id INT,
    attendance_processing_id INT,
    employee_allowance_id INT,
    employee_government_deduction_id INT,
    created_date TIMESTAMP,
    submitted_date DATE
) AUTO_INCREMENT = 10000;

-- Table: payslip
CREATE TABLE IF NOT EXISTS payslip (
    payslip_id INT AUTO_INCREMENT PRIMARY KEY,
    payroll_id INT,
    employee_id INT,
    position VARCHAR(100),
    department VARCHAR(100),
    monthly_rate DECIMAL(10,2),
    daily_rate DECIMAL(10,2),
    days_worked INT,
    overtime DECIMAL(5,2),
    gross_salary DECIMAL(10,2),
    employee_allowance_id INT,
    total_allowance DECIMAL(10,2),
    employee_government_deduction_id INT,
    total_deductions DECIMAL(10,2),
    net_salary DECIMAL(10,2),
    generated_date DATE
) AUTO_INCREMENT = 10000;
