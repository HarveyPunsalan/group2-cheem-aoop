-- Seed data for mapping roles to access definitions in user_access
-- Assumes user_role and user_access tables are pre-populated

INSERT INTO role_access 
  (role_id, access_id) 
VALUES
  -- System Administrator: Full access to all actions and resources (role_id = 1)
  (1, 1),  (1, 2),  (1, 3),  (1, 4),
  (1, 5),  (1, 6),  (1, 7),  (1, 8),
  (1, 9),  (1, 10), (1, 11), (1, 12),
  (1, 13), (1, 14), (1, 15), (1, 16),
  (1, 17), (1, 18), (1, 19), (1, 20),
  (1, 21), (1, 22), (1, 23), (1, 24),
  /*  (1, 25), (1, 26), (1, 27), (1, 28),
  (1, 29), (1, 30),*/

  -- HR Manager: Manage employees, leave, and payroll reports (role_id = 2)
  (2, 1),  (2, 2),  (2, 3),  (2, 4),
  (2, 5),  (2, 6),  (2, 7),  (2, 8),
  (2, 9),  (2, 10), (2, 11), (2, 12),
  (2, 13), (2, 14), (2, 15), (2, 21), (2, 22),

  -- Payroll Administrator: Responsible for salary and payslips (role_id = 3)
  (3, 21), (3, 22), (3, 23), (3, 24),

  -- Department Head: Supervises team and approves requests (role_id = 4)
  (4, 5), (4, 6), (4, 7), (4, 8), (4, 13), (4, 14), (4, 15),

  -- Employee: Self-service access (role_id = 5)
  (5, 1), (5, 6), (5, 10), (5, 14), (5, 22); -- (5, 26);
