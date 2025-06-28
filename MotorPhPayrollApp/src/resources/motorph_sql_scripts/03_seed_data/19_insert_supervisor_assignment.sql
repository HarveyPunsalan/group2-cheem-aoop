-- Inserts supervisor-employee assignments with sample valid date ranges
-- Assumes all IDs reference existing employee records
-- Start dates range from Jan 2022 to Jan 2025
-- End dates are optional and set later, using NULL here to reflect ongoing assignments

INSERT INTO supervisor_assignment (
  employee_id, supervisor_id, start_date, end_date
) VALUES
  (10002, 10001, '2022-02-01', NULL),
  (10003, 10001, '2022-03-01', NULL),
  (10004, 10001, '2022-03-15', NULL),
  (10005, 10002, '2022-04-01', NULL),
  (10006, 10002, '2022-04-01', NULL),
  (10007, 10006, '2022-05-01', NULL),
  (10008, 10007, '2022-06-01', NULL),
  (10009, 10007, '2022-06-01', NULL),
  (10010, 10003, '2022-04-01', NULL),
  (10011, 10010, '2022-05-01', NULL),
  (10012, 10011, '2022-06-01', NULL),
  (10013, 10011, '2022-06-15', NULL),
  (10014, 10011, '2022-07-01', NULL),
  (10015, 10002, '2022-03-01', NULL),
  (10016, 10015, '2022-05-01', NULL),
  (10017, 10015, '2022-05-01', NULL),
  (10018, 10016, '2022-06-01', NULL),
  (10019, 10016, '2022-06-01', NULL),
  (10020, 10016, '2022-06-01', NULL),
  (10021, 10016, '2022-06-01', NULL),
  (10022, 10016, '2022-06-01', NULL),
  (10023, 10016, '2022-06-01', NULL),
  (10024, 10016, '2022-06-01', NULL),
  (10025, 10017, '2023-01-01', NULL),
  (10026, 10017, '2023-01-01', NULL),
  (10027, 10017, '2023-01-01', NULL),
  (10028, 10017, '2023-01-01', NULL),
  (10029, 10017, '2023-01-01', NULL),
  (10030, 10017, '2023-01-01', NULL),
  (10031, 10017, '2023-01-01', NULL),
  (10032, 10004, '2023-02-01', NULL),
  (10033, 10004, '2023-02-01', NULL),
  (10034, 10004, '2023-02-01', NULL);
