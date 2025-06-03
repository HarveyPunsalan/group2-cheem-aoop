-- Inserts employee-specific allowance data into the employee_allowance table
-- Assumes allowance_id 1 = Rice Subsidy, 2 = Phone Allowance, 3 = Clothing Allowance
-- Uses fixed date '2025-01-01' as the effective_date
-- Uses CURDATE() for both created_date to reflect current date
-- Sets allowance_frequency to 'Monthly' for all entries

INSERT INTO employee_allowance (
  employee_id,        -- ID of the employee receiving the allowance
  allowance_id,       -- ID of the allowance type (1 = Rice Subsidy, 2 = Phone Allowance)
  amount,             -- Amount given for the allowance
  effective_date,     -- When the allowance takes effect
  created_date,       -- When the record was inserted
  allowance_frequency -- Frequency of the allowance application
) 
VALUES
  -- Employee 10001
  (10001, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10001, 2, 2000.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10001, 3, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10002
  (10002, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10002, 2, 2000.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10003
  (10003, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10003, 2, 2000.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10004
  (10004, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10004, 2, 2000.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10005
  (10005, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10005, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10006
  (10006, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10006, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10007
  (10007, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10007, 2, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10008
  (10008, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10008, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10009
  (10009, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10009, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10010
  (10010, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10010, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10011
  (10011, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10011, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10012
  (10012, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10012, 2, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10013
  (10013, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10013, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10014
  (10014, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10014, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10015
  (10015, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10015, 2, 1000.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10016
  (10016, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10016, 2, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10017
  (10017, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10017, 2, 800.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10018
  (10018, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10018, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10019
  (10019, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10019, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10020
  (10020, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10020, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  -- Employee 10021 to 10034 (same allowance pattern as above)
  (10021, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10021, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10022, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10022, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10023, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10023, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10024, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10024, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10025, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10025, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10026, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10026, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10027, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10027, 2, 500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10028, 1, 1500.00, '2025-01-01', CURDATE(), 'Monthly'),
  (10028, 2, 500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10029, 1, 1500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10029, 2, 500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10030, 1, 1500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10030, 2, 500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10031, 1, 1500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10031, 2, 500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10032, 1, 1500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10032, 2, 1000.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10033, 1, 1500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10033, 2, 1000.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10034, 1, 1500.00, '2025-01-01'(), CURDATE(), 'Monthly'),
  (10034, 2, 1000.00, '2025-01-01'(), CURDATE(), 'Monthly');