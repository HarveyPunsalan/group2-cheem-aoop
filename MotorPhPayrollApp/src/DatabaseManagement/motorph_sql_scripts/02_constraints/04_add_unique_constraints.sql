-- Enforce uniqueness across key business columns

-- 1. EmployeePersonalInformation: emails must be unique
ALTER TABLE employee_personal_information
  ADD CONSTRAINT uq_employee_email
    UNIQUE (email);
    
-- 2. EmployeeGovernmentInformation: each ID must be unique per employee
ALTER TABLE employee_government_information
  ADD CONSTRAINT uq_sss_number
    UNIQUE (sss_number);

ALTER TABLE employee_government_information
  ADD CONSTRAINT uq_philhealth_number
    UNIQUE (philhealth_number);

ALTER TABLE employee_government_information
  ADD CONSTRAINT uq_pagibig_number
    UNIQUE (pagibig_number);

ALTER TABLE employee_government_information
  ADD CONSTRAINT uq_tin_number
    UNIQUE (tax_identification_number);

-- 3. Job: prevent duplicate job titles
ALTER TABLE job
  ADD CONSTRAINT uq_job_title
    UNIQUE (job_title);

-- 4. Department: prevent duplicate department names
ALTER TABLE department
  ADD CONSTRAINT uq_department_name
    UNIQUE (department_name);

-- 5. UserRole: prevent duplicate role names
ALTER TABLE user_role
  ADD CONSTRAINT uq_user_role_name
    UNIQUE (role_name);

-- 6. Access: prevent duplicate access type names
ALTER TABLE access
  ADD CONSTRAINT uq_access_name
    UNIQUE (access_name);

-- 7. AccessCategory: prevent duplicate category names
ALTER TABLE access_category
  ADD CONSTRAINT uq_access_category_name
    UNIQUE (category_name);

-- 8. Allowance: prevent duplicate allowance names
ALTER TABLE allowance
  ADD CONSTRAINT uq_allowance_name
    UNIQUE (allowance_name);

-- 9. Deduction:     
ALTER TABLE deduction_chart_type
  ADD CONSTRAINT uq_dct_code 
    UNIQUE (chart_code);

-- 10. RoleAccess: prevent assigning the same access twice to one role
ALTER TABLE role_access
  ADD CONSTRAINT uq_role_access_mapping
    UNIQUE (role_id, access_id);