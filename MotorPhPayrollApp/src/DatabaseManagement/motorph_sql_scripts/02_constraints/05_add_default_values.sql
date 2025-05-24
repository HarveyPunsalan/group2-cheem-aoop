-- Define default values for common columns
/*
-- 1. EmployeePersonalInformation
  ALTER TABLE employee_personal_information
  ALTER COLUMN employee_status SET DEFAULT 'Active',
  ALTER COLUMN created_at SET DEFAULT CURRENT_TIMESTAMP;

-- 2. SupervisorAssignment
  ALTER TABLE supervisor_assignment
  ALTER COLUMN is_active SET DEFAULT TRUE;
*/
-- 3. Request
ALTER TABLE request
  ALTER COLUMN request_status SET DEFAULT 'Pending',
  MODIFY created_date TIMESTAMP NOT NULL
    DEFAULT CURRENT_TIMESTAMP;
/*
-- 4. Overtime
ALTER TABLE overtime
  ALTER COLUMN is_approved SET DEFAULT FALSE;
*/
-- 5. Deduction
ALTER TABLE deduction_chart_type
  MODIFY created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  MODIFY updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP 
         ON UPDATE CURRENT_TIMESTAMP;

-- 6. PayrollRecord
ALTER TABLE payroll_record
  MODIFY created_date TIMESTAMP NOT NULL
    DEFAULT CURRENT_TIMESTAMP;