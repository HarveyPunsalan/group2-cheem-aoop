-- Insert sample data into user_access table for action-resource combinations
-- NOTE: Assumes valid foreign key values already exist in related tables

INSERT INTO user_access 
  (access_name, access_category_id, resource_id, action_id, requires_approval) 
VALUES
  -- Employee Management
  ('View Employee Record', 1, 2, 2, 0),
  ('Create Employee Record', 1, 2, 1, 1),
  ('Update Employee Record', 1, 2, 3, 1),
  ('Delete Employee Record', 1, 2, 4, 1),

  -- Time and Attendance Tracking
  ('View Attendance Record', 2, 6, 2, 0),
  ('Upload Attendance Record', 2, 6, 11, 1),
  ('Edit Attendance Record', 2, 6, 3, 1),
  ('Generate Daily Time Record', 2, 6, 10, 1),

  -- Leave Request
  ('Submit Leave Request', 2, 7, 7, 0),
  ('View Leave Request', 2, 7, 2, 0),
  ('Approve Leave Request', 2, 7, 5, 1),
  ('Reject Leave Request', 2, 7, 6, 1),

  -- Overtime Request
  ('Submit Overtime Request', 2, 8, 7, 0),
  ('View Overtime Request', 2, 8, 2, 0),
  ('Approve Overtime Request', 2, 8, 5, 1),
  ('Reject Overtime Request', 2, 8, 6, 1),

  -- Payroll Management
  ('View Payroll Record', 3, 9, 2, 0),
  ('Generate Payroll Record', 3, 9, 10, 1),
  ('View Payslip', 3, 10, 2, 0),
  ('Generate Payslip', 3, 10, 10, 1),

  -- Report Management
  ('Generate Report', 3, 12, 10, 1),
  ('View Report', 3, 12, 2, 0),

  -- System Configuration
  ('Configure System Settings', 4, 16, 15, 1),
  ('Backup System Data', 4, 17, 16, 1),
  ('Restore System Data', 4, 17, 17, 1);