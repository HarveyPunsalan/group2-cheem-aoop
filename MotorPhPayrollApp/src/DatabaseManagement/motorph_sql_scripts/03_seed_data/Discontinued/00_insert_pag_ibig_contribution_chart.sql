-- Seed Pag-IBIG Contribution Chart data (effective 2025-01-01)

INSERT INTO pag_ibig_contribution_chart
  (min_salary, max_salary, employee_rate, employer_rate, max_contribution, effective_date, notes)
VALUES
  (1000.00, 1500.00, 0.01, 0.02, 100.00, '2025-01-01', 'Contribution capped at 100 PHP'),
  (1500.01,    NULL, 0.02, 0.02, 100.00, '2025-01-01', 'Max contribution amount');
