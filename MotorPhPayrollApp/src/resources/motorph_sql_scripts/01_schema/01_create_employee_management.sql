-- Defines core employee-related tables for MotorPH Payroll System

USE motorph_payroll_db;

-- Table: employee_personal_information
CREATE TABLE IF NOT EXISTS employee_personal_information (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    birthday DATE,
    phone_number VARCHAR(15),
    email VARCHAR(80)
) AUTO_INCREMENT = 10000;

-- Table: employee_address
CREATE TABLE IF NOT EXISTS address_type (
  address_type_id INT AUTO_INCREMENT PRIMARY KEY,
  address_type_name VARCHAR(50) NOT NULL,
  address_type_description VARCHAR(255)
) AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS employee_address (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    house_number VARCHAR(10),
    street VARCHAR(50),
    barangay VARCHAR(50),
    municipality VARCHAR(50),
    province VARCHAR(50),
    postal_code VARCHAR(10),
    country VARCHAR(50),
    address_type VARCHAR(20)
) AUTO_INCREMENT = 1;

-- Table: employee_government_information
CREATE TABLE IF NOT EXISTS employee_government_information (
    gov_info_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    sss_number VARCHAR(20),
    philhealth_number VARCHAR(20),
    pagibig_number VARCHAR(20),
    tax_identification_number VARCHAR(20),
    withholding_tax_status VARCHAR(50)
) AUTO_INCREMENT = 10000;

-- Table: employee_employment_information
CREATE TABLE IF NOT EXISTS employee_employment_information (
    employment_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    job_id INT,
    salary_id INT,
    employment_type VARCHAR(50),
    employment_status VARCHAR(50),
    date_hired DATE
) AUTO_INCREMENT = 10000;

-- Table: supervisor_assignment
CREATE TABLE IF NOT EXISTS supervisor_assignment (
    supervisor_assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    supervisor_id INT,
    start_date DATE,
    end_date DATE
) AUTO_INCREMENT = 1000;
