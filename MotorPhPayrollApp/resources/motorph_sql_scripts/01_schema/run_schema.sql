-- Master runner for MotorPH schema creation with transaction wrapper
-- WARNING: MySQL treats DDL statements (CREATE/ALTER) as implicit commits and cannot be rolled back.
-- The transaction wrapper here is illustrative; it will only affect DML operations.

START TRANSACTION;

SOURCE 00_create_database.sql;
SOURCE 01_create_employee_management.sql;
SOURCE 02_create_job_salary_department.sql;
SOURCE 03_create_user_access_control.sql;
SOURCE 04_create_attendance_tracking.sql;
SOURCE 05_create_request_management.sql;
SOURCE 05_create_request_management.sql;
SOURCE 07_create_payroll_processing.sql;

-- Commit transaction (note: only DML within this block is truly rollback-capable)
COMMIT;

-- End of schema runner. Update this file as you add new scripts.
