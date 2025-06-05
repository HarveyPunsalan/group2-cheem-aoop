-- Creates user accounts, roles, and access control-related tables for MotorPH Payroll System

USE motorph_payroll_db;

-- Table: system_user
CREATE TABLE IF NOT EXISTS system_user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100), 
    password_hashed CHAR(60),
    employee_id INT,
    role_id INT,
    account_created TIMESTAMP,
    last_login TIMESTAMP,
    is_active BOOLEAN
) AUTO_INCREMENT = 10000;

-- Table: role
CREATE TABLE IF NOT EXISTS user_role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(30),
    role_description VARCHAR(150)
) AUTO_INCREMENT = 1;

-- Table: role_access
CREATE TABLE IF NOT EXISTS role_access (
    role_access_id INT AUTO_INCREMENT PRIMARY KEY,
    role_id INT,
    access_id INT
) AUTO_INCREMENT = 1;

-- Table: access_category
CREATE TABLE IF NOT EXISTS access_category (
    access_category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50),
    access_description VARCHAR(150)
) AUTO_INCREMENT = 1;
/*
-- Table: access
CREATE TABLE IF NOT EXISTS access (
    access_id INT AUTO_INCREMENT PRIMARY KEY,
    access_name VARCHAR(100),
    access_category_id INT,
    requires_approval BOOLEAN
) AUTO_INCREMENT = 1;
*/
-- Revised schema for access table including references to resource and action
-- This table links specific actions to resources and assigns them to categorized access points
CREATE TABLE IF NOT EXISTS user_access (
    access_id INT AUTO_INCREMENT PRIMARY KEY,   -- Unique identifier for each access entry
    access_name VARCHAR(100),          			-- Descriptive label for access point (used in UI or logs); now enforced as unique
    access_category_id INT,                   	-- Foreign key linking to access_category table (e.g., Payroll, HR)
    resource_id INT,                          	-- Foreign key linking to user_resource table (defines the target data entity)
    action_id INT,                            	-- Foreign key linking to user_action table (defines the action performed on resource)
    requires_approval TINYINT,            		-- Indicates whether this access requires approval (0 = No, 1 = Yes)
    is_active BOOLEAN,                   		-- Flag to enable/disable access without deleting the row
    created_at TIMESTAMP			   			-- Timestamp for audit/logging purposes
) AUTO_INCREMENT = 1;


-- Table: user_action
CREATE TABLE IF NOT EXISTS user_action (
    action_id INT AUTO_INCREMENT PRIMARY KEY,
    action_name VARCHAR(50),
    user_description TEXT,
    created_at TIMESTAMP
) AUTO_INCREMENT = 1;

-- Table: user_resource
CREATE TABLE IF NOT EXISTS user_resource (
    resource_id INT AUTO_INCREMENT PRIMARY KEY,
    resource_name VARCHAR(50),
    resource_description TEXT,
    created_at TIMESTAMP
);



