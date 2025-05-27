-- Creates user accounts, roles, and access control-related tables for MotorPH Payroll System

USE motorph_payroll_db;

-- Table: role
CREATE TABLE IF NOT EXISTS user_role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(30),
    role_description VARCHAR(150)
) AUTO_INCREMENT = 1;

-- Table: access_category
CREATE TABLE IF NOT EXISTS access_category (
    access_category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50),
    description VARCHAR(150)
) AUTO_INCREMENT = 1;

-- Table: access
CREATE TABLE IF NOT EXISTS access (
    access_id INT AUTO_INCREMENT PRIMARY KEY,
    access_name VARCHAR(100),
    access_category_id INT,
    requires_approval BOOLEAN
) AUTO_INCREMENT = 1;

-- Table: role_access
CREATE TABLE IF NOT EXISTS role_access (
    role_access_id INT AUTO_INCREMENT PRIMARY KEY,
    role_id INT,
    access_id INT
) AUTO_INCREMENT = 1;

-- Table: user
CREATE TABLE IF NOT EXISTS system_user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100), 
    password_hashed CHAR(60),
    employee_id INT,
    role_id INT,
    account_created TIMESTAMP,
    last_login TIMESTAMP,
    is_active BOOLEAN
);
