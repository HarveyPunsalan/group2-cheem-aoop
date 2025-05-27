-- Enforce relationships between tables in the MotorPH Payroll System

-- EmployeeAddress → EmployeePersonalInformation
ALTER TABLE employee_address
  ADD CONSTRAINT fk_address_employee
    FOREIGN KEY (employee_id)
      REFERENCES employee_personal_information(employee_id)
      ON DELETE CASCADE;

ALTER TABLE employee_address
  ADD CONSTRAINT fk_address_type
    FOREIGN KEY (address_type)
      REFERENCES address_type(address_type_id);

-- EmployeeGovernmentInformation → EmployeePersonalInformation
ALTER TABLE employee_government_information
  ADD CONSTRAINT fk_government_info_employee
    FOREIGN KEY (employee_id)
      REFERENCES employee_personal_information(employee_id)
      ON DELETE CASCADE;

-- EmployeeEmploymentInformation → EmployeePersonalInformation, Job, Salary
ALTER TABLE employee_employment_information
  ADD CONSTRAINT fk_employment_employee
    FOREIGN KEY (employee_id) 
      REFERENCES employee_personal_information(employee_id) 
      ON DELETE CASCADE;

ALTER TABLE employee_employment_information
ADD CONSTRAINT fk_employment_job
FOREIGN KEY (job_id) 
REFERENCES job(job_id);

ALTER TABLE employee_employment_information
ADD CONSTRAINT fk_employment_salary
FOREIGN KEY (salary_id) 
REFERENCES salary(salary_id);

-- SupervisorAssignment → EmployeePersonalInformation
ALTER TABLE supervisor_assignment
ADD CONSTRAINT fk_supervisor_employee
FOREIGN KEY (employee_id) 
REFERENCES employee_personal_information(employee_id) 
ON DELETE CASCADE;

ALTER TABLE supervisor_assignment
ADD CONSTRAINT fk_supervisor_supervisor
FOREIGN KEY (supervisor_id) 
REFERENCES employee_personal_information(employee_id) 
ON DELETE SET NULL;

-- Job → Department
ALTER TABLE job
ADD CONSTRAINT fk_job_department
FOREIGN KEY (department_id) 
REFERENCES department(department_id);

-- Department → Employee (Head)
ALTER TABLE department
ADD CONSTRAINT fk_department_head
FOREIGN KEY (department_head) 
REFERENCES employee_personal_information(employee_id) 
ON DELETE SET NULL;

-- SystemUser → EmployeePersonalInformation, UserRole
ALTER TABLE system_user
  ADD CONSTRAINT fk_system_user_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee_personal_information(employee_id)
    ON DELETE CASCADE;

ALTER TABLE system_user
  ADD CONSTRAINT fk_system_user_role
    FOREIGN KEY (role_id)
    REFERENCES user_role(role_id);
    
-- Access → AccessCategory
ALTER TABLE access
  ADD CONSTRAINT fk_access_category
    FOREIGN KEY (access_category)
    REFERENCES access_category(access_category_id);

-- RoleAccess → user_role, Access
ALTER TABLE role_access
  ADD CONSTRAINT fk_role_access_user_role
    FOREIGN KEY (role_id)
    REFERENCES user_role(role_id);

ALTER TABLE role_access
  ADD CONSTRAINT fk_role_access_access
    FOREIGN KEY (access_id)
    REFERENCES access(access_id);

-- DailyTimeRecord → EmployeePersonalInformation
ALTER TABLE daily_time_record
  ADD CONSTRAINT fk_dtr_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee_personal_information(employee_id)
    ON DELETE CASCADE;

-- AttendanceProcessing → EmployeePersonalInformation, PayPeriod, EmployeePersonalInformation
ALTER TABLE attendance_processing
  ADD CONSTRAINT fk_attendance_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee_personal_information(employee_id)
    ON DELETE CASCADE;

ALTER TABLE attendance_processing
  ADD CONSTRAINT fk_attendance_pay_period
    FOREIGN KEY (pay_period_id)
    REFERENCES pay_period(pay_period_id);

ALTER TABLE attendance_processing
  ADD CONSTRAINT fk_attendance_approver
    FOREIGN KEY (approved_by)
    REFERENCES employee_personal_information(employee_id);
    
-- Request → EmployeePersonalInformation, EmployeePersonalInformation
ALTER TABLE request
  ADD CONSTRAINT fk_request_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee_personal_information(employee_id)
    ON DELETE CASCADE;

ALTER TABLE request
  ADD CONSTRAINT fk_processed_by
    FOREIGN KEY (processed_by)
    REFERENCES employee_personal_information(employee_id);
    
-- Overtime → EmployeePersonalInformation, Request, DailyTimeRecord
ALTER TABLE overtime
  ADD CONSTRAINT fk_overtime_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee_personal_information(employee_id);

ALTER TABLE overtime
  ADD CONSTRAINT fk_overtime_request
    FOREIGN KEY (request_id)
    REFERENCES request(request_id)
    ON DELETE CASCADE;

ALTER TABLE overtime
  ADD CONSTRAINT fk_overtime_dtr
    FOREIGN KEY (dtr_id)
    REFERENCES daily_time_record(dtr_id);
    
-- EmployeeLeave → EmployeePersonalInformation, Request
ALTER TABLE employee_leave
  ADD CONSTRAINT fk_leave_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee_personal_information(employee_id);

ALTER TABLE employee_leave
  ADD CONSTRAINT fk_leave_request
    FOREIGN KEY (request_id)
    REFERENCES request(request_id)
    ON DELETE CASCADE;
    
-- EmployeeAllowance → EmployeePersonalInformation, Allowance
ALTER TABLE employee_allowance
  ADD CONSTRAINT fk_employee_allowance_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee_personal_information(employee_id);

ALTER TABLE employee_allowance
  ADD CONSTRAINT fk_employee_allowance_type
    FOREIGN KEY (allowance_id)
    REFERENCES allowance(allowance_id);

-- DeductionChart → DeductionChartType    
ALTER TABLE deduction_chart
  ADD CONSTRAINT fk_deduction_chart_type
    FOREIGN KEY (chart_type)
    REFERENCES deduction_chart_type(chart_id);
    
ALTER TABLE government_deduction_chart
   ADD CONSTRAINT fk_gov_deduction_type
     FOREIGN KEY (gov_deduction_type_id)
     REFERENCES deduction_chart_type(chart_id)
     ON UPDATE CASCADE
     ON DELETE RESTRICT;
    
-- EmployeeGovernmentDeduction → GovernmentDeductionChart, PayrollRecord
ALTER TABLE employee_government_deduction
  ADD CONSTRAINT fk_gov_deduction_chart
    FOREIGN KEY (chart_id)
    REFERENCES government_deduction_chart(gov_deduction_chart_id);

ALTER TABLE employee_government_deduction
  ADD CONSTRAINT fk_gov_deduction_payroll
    FOREIGN KEY (payroll_id)
    REFERENCES payroll_record(payroll_id);
    
-- PayrollRecord → PayPeriod, EmployeePersonalInformation, AttendanceProcessing, Allowance, GovernmentDeduction
ALTER TABLE payroll_record
  ADD CONSTRAINT fk_payroll_period
    FOREIGN KEY (pay_period_id)
    REFERENCES pay_period(pay_period_id);

ALTER TABLE payroll_record
  ADD CONSTRAINT fk_payroll_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee_personal_information(employee_id);

ALTER TABLE payroll_record
  ADD CONSTRAINT fk_payroll_attendance
    FOREIGN KEY (attendance_processing_id)
    REFERENCES attendance_processing(attendance_processing_id);

ALTER TABLE payroll_record
  ADD CONSTRAINT fk_payroll_allowance
    FOREIGN KEY (employee_allowance_id)
    REFERENCES employee_allowance(employee_allowance_id);

ALTER TABLE payroll_record
  ADD CONSTRAINT fk_payroll_deduction
    FOREIGN KEY (employee_government_deduction_id)
    REFERENCES employee_government_deduction(employee_government_deduction_id);
    
-- Payslip → PayrollRecord, EmployeePersonalInformation, Allowance, GovernmentDeduction
ALTER TABLE payslip
  ADD CONSTRAINT fk_payslip_payroll
    FOREIGN KEY (payroll_id)
    REFERENCES payroll_record(payroll_id);

ALTER TABLE payslip
  ADD CONSTRAINT fk_payslip_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee_personal_information(employee_id);

ALTER TABLE payslip
  ADD CONSTRAINT fk_payslip_allowance
    FOREIGN KEY (employee_allowance_id)
    REFERENCES employee_allowance(employee_allowance_id);

ALTER TABLE payslip
  ADD CONSTRAINT fk_payslip_deduction
    FOREIGN KEY (employee_government_deduction_id)
    REFERENCES employee_government_deduction(employee_government_deduction_id);