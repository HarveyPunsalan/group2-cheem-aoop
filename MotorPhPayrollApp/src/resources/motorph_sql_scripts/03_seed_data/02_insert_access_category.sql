-- Inserts access categories used to group related access permissions

INSERT INTO access_category (category_name, description) VALUES
  ('Employee Management', 'Permissions related to employee profile and records management'),
  ('Payroll Processing', 'Permissions for salary computation, payslip generation, and payroll approval'),
  ('Time and Attendance', 'Access to leave requests, approvals, and attendance monitoring'),
  ('User Management', 'Access to system user account creation and role assignment'),
  ('Reporting', 'View and generate various system reports'),
  ('System Settings', 'Administrative access for configuration and maintenance of system features');
