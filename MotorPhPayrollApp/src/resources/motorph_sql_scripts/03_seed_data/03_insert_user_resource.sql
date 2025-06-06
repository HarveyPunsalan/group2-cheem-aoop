-- Insert predefined resource types used for RBAC into user_resource table
-- Each resource corresponds to a protected data component in the system

INSERT INTO user_resource 
  (resource_name, resource_description) 

VALUES
  -- User-related data
  ('user_account', 'User login and profile data'),

  -- Employee and organizational structure
  ('employee_record', 'Employee personal/employment info'),
  ('department', 'Departmental structure'),
  ('job', 'Job titles and designations'),
  ('salary_structure', 'Basic and detailed salary info'),

  -- Attendance and scheduling
  ('attendance_record', 'Time-in/out and work hours'),
  ('leave_request', 'Filed leave requests'),
  ('overtime_request', 'Filed overtime requests'),

  -- Payroll processing
  ('payroll_record', 'Payroll calculations and summaries'),
  ('payslip', 'Generated payslips'),

  -- Government compliance and deductions
  ('government_deduction', 'SSS, PhilHealth, Pag-IBIG rates'),

  -- System outputs and reporting
  ('report', 'Various system-generated reports'),

  -- Role-based access and control
  ('role', 'User roles and access groups'),
  ('permission', 'Action-resource pairs granted'),

  -- Monitoring and system configuration
  ('audit_log', 'System logs and activity trail'),
  ('system_settings', 'General configuration settings'),
  ('backup_file', 'DB backup files');
