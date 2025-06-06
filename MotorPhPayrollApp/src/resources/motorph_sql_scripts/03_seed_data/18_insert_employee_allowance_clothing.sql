-- Inserts Clothing Allowance for all employees
-- Assumes allowance_id 3 = Clothing Allowance
-- Uses fixed date '2025-01-01' as the effective_date
-- Uses CURDATE() for both created_date to reflect current date
-- Sets allowance_frequency to 'Monthly' for all entries

INSERT INTO employee_allowance (
  employee_id, allowance_id, amount, effective_date, created_date, allowance_frequency
) VALUES
  (10001, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10002, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10003, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10004, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10005, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10006, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10007, 3, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10008, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10009, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10010, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10011, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10012, 3, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10013, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10014, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10015, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10016, 3, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10017, 3, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10018, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10019, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10020, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10021, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10022, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10023, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10024, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10025, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10026, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10027, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10028, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10029, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10030, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10031, 3, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10032, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10033, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10034, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly');
