-- Inserts Phone Allowance for all employees
-- Assumes allowance_id 2 = Phone Allowance
-- Uses fixed date '2025-01-01' as the effective_date
-- Uses CURDATE() for both created_date to reflect current date
-- Sets allowance_frequency to 'Monthly' for all entries

INSERT INTO employee_allowance (
  employee_id, allowance_id, amount, effective_date, created_date, allowance_frequency
) VALUES
  (10001, 2, 2000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10002, 2, 2000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10003, 2, 2000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10004, 2, 2000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10005, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10006, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10007, 2, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10008, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10009, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10010, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10011, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10012, 2, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10013, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10014, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10015, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10016, 2, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10017, 2, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10018, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10019, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10020, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10021, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10022, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10023, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10024, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10025, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10026, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10027, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10028, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10029, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10030, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10031, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10032, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10033, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10034, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly');
