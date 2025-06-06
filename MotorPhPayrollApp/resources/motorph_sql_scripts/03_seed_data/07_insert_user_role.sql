-- Inserts predefined roles for the MotorPH Payroll System

INSERT INTO user_role 
  (role_name, role_description) 

VALUES
  ('System Administrator', 'Full access to system settings and database maintenance'),
  ('HR Manager', 'Can manage employee records, approve leaves, and generate payroll reports'),
  ('Payroll Administrator', 'Responsible for salary computation and payslip generation'),
  ('Department Head', 'Supervises team members and approves requests within department'),
  ('Employee', 'Access to self-service portal to view payslips and request leaves');
