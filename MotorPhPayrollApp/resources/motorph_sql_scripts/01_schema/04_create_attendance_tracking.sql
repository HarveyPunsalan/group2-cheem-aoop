-- Creates tables related to employee attendance for MotorPH Payroll System

USE motorph_payroll_db;

-- Table: daily_time_record
CREATE TABLE IF NOT EXISTS daily_time_record (
    dtr_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    date DATE,
    login TIME,
    logout TIME,
    late_hours DECIMAL(5,2),
    overtime_hours DECIMAL(5,2),
    worked_hours DECIMAL(5,2)
) AUTO_INCREMENT = 10000;

-- Table: attendance_processing
CREATE TABLE IF NOT EXISTS attendance_processing (
    attendance_processing_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    pay_period_id INT,
    total_late_hours DECIMAL(5,2),
    total_approved_overtime_hours DECIMAL(5,2),
    total_worked_hours DECIMAL(5,2),
    total_paid_leave_hours DECIMAL(5,2),
    payable_hours DECIMAL(5,2),
    status VARCHAR(50),
    approved_by INT
) AUTO_INCREMENT = 10000;
