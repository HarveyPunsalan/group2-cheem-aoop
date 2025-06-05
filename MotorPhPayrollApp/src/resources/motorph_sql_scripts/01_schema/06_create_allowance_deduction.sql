-- Creates allowance and government deduction-related tables for the MotorPH Payroll System

USE motorph_payroll_db;

-- Table: allowance
CREATE TABLE IF NOT EXISTS allowance (
    allowance_id INT AUTO_INCREMENT PRIMARY KEY,
    allowance_name VARCHAR(100),
    description VARCHAR(150)
) AUTO_INCREMENT = 1;

-- Table: employee_allowance
CREATE TABLE IF NOT EXISTS employee_allowance (
    employee_allowance_id INT AUTO_INCREMENT PRIMARY KEY,
    allowance_id INT,
    employee_id INT,
    amount DECIMAL(10,2),
    effective_date DATE,
    created_date DATE,
    allowance_frequency VARCHAR(50)
) AUTO_INCREMENT = 10000;

CREATE TABLE IF NOT EXISTS deduction_chart_type (
    chart_id            INT AUTO_INCREMENT PRIMARY KEY,
    chart_code          VARCHAR(50),
    chart_name          VARCHAR(100),
    chart_description   TEXT,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP
) AUTO_INCREMENT = 1;
/*
-- Discontinued Table
-- Table: government_deduction_chart
CREATE TABLE IF NOT EXISTS government_deduction_chart (
    gov_deduction_chart_id INT AUTO_INCREMENT PRIMARY KEY,
    gov_deduction_chart_name VARCHAR(255),
    minimum_range DECIMAL(10,2),
    maximum_range DECIMAL(10,2),
    employee_deduction_rate DECIMAL(5,2),
    employer_contribution_rate DECIMAL(5,2),
    fixed_amount DECIMAL(10,2)
) AUTO_INCREMENT = 1;
*/
CREATE TABLE IF NOT EXISTS government_deduction_chart (
  gov_deduction_chart_id      INT               NOT NULL AUTO_INCREMENT PRIMARY KEY,
  gov_deduction_type_id       INT UNSIGNED      NOT NULL,
  minimum_range               DECIMAL(10,2)     NOT NULL,
  maximum_range               DECIMAL(10,2)     DEFAULT NULL,
  fixed_amount                DECIMAL(10,2)     DEFAULT NULL,
  employee_deduction_rate     DECIMAL(5,2)      DEFAULT NULL,
  employer_contribution_share DECIMAL(5,2)      DEFAULT NULL,
  has_contribution_cap        BOOLEAN           NOT NULL DEFAULT FALSE,
  min_contribution            DECIMAL(10,2)     DEFAULT NULL,
  max_contribution            DECIMAL(10,2)     DEFAULT NULL,
  effective_at                DATE              NOT NULL,
  updated_at                  DATE              DEFAULT NULL,
  notes                       VARCHAR(255)      DEFAULT NULL
) AUTO_INCREMENT = 1;

-- Table: employee_government_deduction
CREATE TABLE IF NOT EXISTS employee_government_deduction (
    employee_government_deduction_id INT AUTO_INCREMENT PRIMARY KEY,
    chart_id INT,
    payroll_id INT,
    amount DECIMAL(10,2),
    effective_date DATE,
    created_date DATE
) AUTO_INCREMENT = 10000;

/*
-- Discontinued Tables
CREATE TABLE IF NOT EXISTS deduction_chart (
  deduction_chart_id INT AUTO_INCREMENT PRIMARY KEY,
  chart_type         INT            NOT NULL,
  min_salary         DECIMAL(10,2)  NOT NULL,
  max_salary         DECIMAL(10,2)  DEFAULT NULL,
  employee_rate      DECIMAL(5,2)   DEFAULT NULL,
  employer_rate      DECIMAL(5,2)   DEFAULT NULL,
  base_tax_amount    DECIMAL(10,2)  DEFAULT NULL,
  tax_rate           DECIMAL(5,2)   DEFAULT NULL,
  fixed_amount       DECIMAL(10,2)  DEFAULT NULL,
  effective_date     DATE           NOT NULL,
  notes              VARCHAR(255)   DEFAULT NULL
)  AUTO_INCREMENT = 10000;

-- Create the SSS Contribution Chart table
CREATE TABLE IF NOT EXISTS sss_contribution_chart (
  sss_chart_id INT AUTO_INCREMENT PRIMARY KEY,
  min_salary DECIMAL(10,2) NOT NULL,
  max_salary DECIMAL(10,2),
  contribution_amount DECIMAL(10,2) NOT NULL,
  effective_date DATE NOT NULL,
  notes VARCHAR(255)
) AUTO_INCREMENT = 1;

-- Create the Pag-IBIG Contribution Chart table
CREATE TABLE IF NOT EXISTS pag_ibig_contribution_chart (
  pag_ibig__chart_id INT AUTO_INCREMENT PRIMARY KEY,
  min_salary DECIMAL(10,2)        NOT NULL,
  max_salary DECIMAL(10,2),
  employee_rate DECIMAL(5,2)      NOT NULL,
  employer_rate DECIMAL(5,2)      NOT NULL,
  max_contribution DECIMAL(10,2)  NOT NULL,
  effective_date DATE             NOT NULL,
  notes VARCHAR(255)
) AUTO_INCREMENT = 1;

-- Create the PhilHealth Contribution Chart table
CREATE TABLE IF NOT EXISTS philhealth_contribution_chart (
  philhealth_chart_id INT AUTO_INCREMENT PRIMARY KEY,
  min_salary     DECIMAL(10,2) NOT NULL,
  max_salary     DECIMAL(10,2),
  premium_rate   DECIMAL(5,2)  NOT NULL,
  min_premium    DECIMAL(10,2) NOT NULL,
  max_premium    DECIMAL(10,2) NOT NULL,
  effective_date DATE          NOT NULL,
  notes          VARCHAR(255)
) AUTO_INCREMENT = 1;

-- Create the Withholding Tax Bracket table
 CREATE TABLE IF NOT EXISTS withholding_tax_bracket (
  withholding_tax_bracket_id INT AUTO_INCREMENT PRIMARY KEY,
  min_salary        DECIMAL(10,2)  NOT NULL,
  max_salary        DECIMAL(10,2),
  base_tax_amount   DECIMAL(10,2)  NOT NULL,
  tax_rate          DECIMAL(5,2)   NOT NULL,
  effective_date    DATE           NOT NULL,
  notes             VARCHAR(255)
) AUTO_INCREMENT = 1;
*/