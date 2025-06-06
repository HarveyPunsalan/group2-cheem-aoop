-- Seed PhilHealth Contribution Chart data (effective 2025-01-01)

INSERT INTO philhealth_contribution_chart
  (min_salary,  max_salary,   premium_rate,  min_premium,  max_premium, effective_date, notes)
VALUES
  (10000.00,    99999.99,      0.03,          300.00,        1800.00, '2025-01-01', 'Premium varies with salary'),
  (60000.00,        NULL,      0.03,          1800.00,       1800.00, '2025-01-01', 'Max premium capped at 1800');
