-- Fallback: Add PRIMARY KEY constraints to all tables if not defined inline

-- 1. Employee tables
ALTER TABLE employee_personal_information
  ADD CONSTRAINT pk_employee_personal_information
    PRIMARY KEY (employee_id);

ALTER TABLE employee_address
  ADD CONSTRAINT pk_employee_address
    PRIMARY KEY (address_id);

ALTER TABLE employee_government_information
  ADD CONSTRAINT pk_employee_government_information
    PRIMARY KEY (gov_info_id);

ALTER TABLE employee_employment_information
  ADD CONSTRAINT pk_employee_employment_information
    PRIMARY KEY (employment_id);

-- 2. SupervisorAssignment
ALTER TABLE supervisor_assignment
  ADD CONSTRAINT pk_supervisor_assignment
    PRIMARY KEY (supervisor_assignment_id);

-- 3. Organizational entities
ALTER TABLE job
  ADD CONSTRAINT pk_job
    PRIMARY KEY (job_id);
    
ALTER TABLE salary
  ADD CONSTRAINT pk_salary
    PRIMARY KEY (salary_id);

ALTER TABLE department
  ADD CONSTRAINT pk_department
    PRIMARY KEY (department_id);

-- 4. User & Access Control
ALTER TABLE system_user
  ADD CONSTRAINT pk_system_user
    PRIMARY KEY (user_id);

ALTER TABLE user_role
  ADD CONSTRAINT pk_user_role
    PRIMARY KEY (role_id);

ALTER TABLE access
  ADD CONSTRAINT pk_access
    PRIMARY KEY (access_id);

ALTER TABLE access_category
  ADD CONSTRAINT pk_access_category
    PRIMARY KEY (access_category_id);

ALTER TABLE role_access
  ADD CONSTRAINT pk_role_access
    PRIMARY KEY (role_access_id);

-- 5. Attendance
ALTER TABLE daily_time_record
  ADD CONSTRAINT pk_daily_time_record
    PRIMARY KEY (dtr_id);

ALTER TABLE attendance_processing
  ADD CONSTRAINT pk_attendance_processing
    PRIMARY KEY (attendance_processing_id);

-- 6. Requests
ALTER TABLE request
  ADD CONSTRAINT pk_request
    PRIMARY KEY (request_id);

-- 7. Overtime & Leave Records
ALTER TABLE overtime
  ADD CONSTRAINT pk_overtime
    PRIMARY KEY (overtime_id);

ALTER TABLE employee_leave
  ADD CONSTRAINT pk_employee_leave
    PRIMARY KEY (leave_id);

-- 8. Allowances & Deductions
ALTER TABLE employee_allowance
  ADD CONSTRAINT pk_employee_allowance
    PRIMARY KEY (employee_allowance_id);

ALTER TABLE allowance
  ADD CONSTRAINT pk_allowance
    PRIMARY KEY (allowance_id);
    
ALTER TABLE deduction_chart_type
  ADD CONSTRAINT pk_deduction_chart_type_id 
    PRIMARY KEY (chart_id);

ALTER TABLE government_deduction_chart
  ADD CONSTRAINT pk_government_deduction_chart
    PRIMARY KEY (gov_deduction_chart_id);

ALTER TABLE employee_government_deduction
  ADD CONSTRAINT pk_employee_government_deduction
    PRIMARY KEY (employee_government_deduction_id);

-- 9. Payroll Period & Payroll
ALTER TABLE pay_period
  ADD CONSTRAINT pk_pay_period
    PRIMARY KEY (pay_period_id);

ALTER TABLE payroll_record
  ADD CONSTRAINT pk_payroll_record
    PRIMARY KEY (payroll_record_id);

ALTER TABLE payslip
  ADD CONSTRAINT pk_payslip
    PRIMARY KEY (payslip_id);
