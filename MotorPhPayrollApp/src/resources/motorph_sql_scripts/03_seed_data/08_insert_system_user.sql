-- Inserts users into the system_user table
-- Assumes roles and employees are already defined
-- Passwords are mock values: all users use 'password123' hashed

INSERT INTO system_user 
  (username, password_hashed, employee_id, role_id, account_created, last_login, is_active) 
VALUES
  -- System Executives
  ('manuel.garcia', 'password123', 10001, 1, NOW(), NULL, 1), -- CEO
  ('antonio.lim', 'password123', 10002, 1, NOW(), NULL, 1), -- COO
  ('bianca.aquino', 'password123', 10003, 1, NOW(), NULL, 1), -- CFO
  ('isabella.reyes', 'password123', 10004, 1, NOW(), NULL, 1), -- CMO

  -- Department Heads & Managers
  ('eduard.hernandez', 'password123', 10005, 4, NOW(), NULL, 1), -- IT Head
  ('andrea.villanueva', 'password123', 10006, 2, NOW(), NULL, 1), -- HR Manager
  ('brad.sanjose', 'password123', 10007, 4, NOW(), NULL, 1), -- HR TL
  ('roderick.alvaro', 'password123', 10010, 4, NOW(), NULL, 1), -- Accounting Head
  ('anthony.salcedo', 'password123', 10011, 3, NOW(), NULL, 1), -- Payroll Manager
  ('josie.lopez', 'password123', 10012, 4, NOW(), NULL, 1), -- Payroll TL
  ('christian.mata', 'password123', 10016, 4, NOW(), NULL, 1), -- Account TL
  ('selena.deleon', 'password123', 10017, 4, NOW(), NULL, 1), -- Account TL

  -- HR, Payroll, and Account Rank & File
  ('alice.romualdez', 'password123', 10008, 5, NOW(), NULL, 1),
  ('rosie.atienza', 'password123', 10009, 5, NOW(), NULL, 1),
  ('martha.farala', '$2y$10$D9eVRbS2Hv.3u9MWmH4sQOIA2b8OYa9DkOrglikAnK0hqa7DaPzfi', 10013, 5, NOW(), NULL, 1),
  ('leila.martinez', '$2y$10$D9eVRbS2Hv.3u9MWmH4sQOIA2b8OYa9DkOrglikAnK0hqa7DaPzfi', 10014, 5, NOW(), NULL, 1),
  ('allison.sanjose', '$2y$10$D9eVRbS2Hv.3u9MWmH4sQOIA2b8OYa9DkOrglikAnK0hqa7DaPzfi', 10018, 5, NOW(), NULL, 1);

  -- All users use password: password123
