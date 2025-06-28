-- Seed Withholding Tax Bracket data (effective 2025-01-01)

INSERT INTO withholding_tax_bracket
  (min_salary,   max_salary,    base_tax_amount, tax_rate, effective_date, notes)
VALUES
  (     0.00,      20832.00,      0.00,            0.00,     '2025-01-01', 'No withholding tax'),
  ( 20833.00,      33332.99,      0.00,            0.20,     '2025-01-01', '20% on excess over 20,833'),
  ( 33333.00,      66666.99,   2500.00,            0.25,     '2025-01-01', '₱2,500 + 25% on excess over 33,333'),
  ( 66667.00,     166666.99,  10833.00,            0.30,     '2025-01-01', '₱10,833 + 30% on excess over 66,667'),
  (166667.00,     666666.99,  40833.33,            0.32,     '2025-01-01', '₱40,833.33 + 32% on excess over 166,667'),
  (666667.00,          NULL, 200833.33,            0.35,     '2025-01-01', '₱200,833.33 + 35% on excess over 666,667');
