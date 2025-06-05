-- Inserts Rice Subsidy for all employees
-- Assumes allowance_id 1 = Rice Subsidy
-- Uses fixed date '2025-01-01' as the effective_date
-- Uses CURDATE() for both created_date to reflect current date
-- Sets allowance_frequency to 'Monthly' for all entries

INSERT INTO employee_allowance (
  employee_id, allowance_id, amount, effective_date, created_date, allowance_frequency
) VALUES
  (10001, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10002, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10003, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10004, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10005, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10006, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10007, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10008, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10009, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10010, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10011, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10012, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10013, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10014, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10015, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10016, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10017, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10018, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10019, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10020, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10021, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10022, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10023, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10024, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10025, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10026, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10027, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10028, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10029, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10030, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10031, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10032, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10033, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10034, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly');
