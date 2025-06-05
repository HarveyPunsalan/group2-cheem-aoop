
-- Consolidated view of employee personal details, permanent address, hire info, job title, and government IDs

CREATE OR REPLACE VIEW employee_details_view AS
SELECT
  p.employee_id AS "ID",
  p.last_name AS "Last Name",
  p.first_name AS "First Name",
  p.birthday  AS "Birthday",
  CONCAT_WS(', ',
    CONCAT_WS(' ', a.house_number, a.street),
    a.barangay,
    a.municipality,
    a.province,
    a.postal_code
  ) AS "Address",
  p.phone_number AS "Phone Number",
  g.sss_number AS "SSS Number",
  g.philhealth_number AS "PhilHealth Number",
  g.tax_identification_number AS "TIN Number",
  g.pagibig_number AS "Pag-IBIG Number",
  ee.date_hired  AS "Date Name",
  ee.employment_status AS "Status",
  j.job_title AS "Position",
  s.basic_salary AS "Basic Salary",
  s.gross_semi_monthly_rate AS "Gross Semi-monthly Rate",
  s.hourly_rate AS "Hourly Rate"
FROM employee_personal_information p
LEFT JOIN employee_address a
  ON p.employee_id = a.employee_id
LEFT JOIN employee_employment_information ee
  ON p.employee_id = ee.employee_id
LEFT JOIN job j
  ON ee.job_id = j.job_id
LEFT JOIN employee_government_information g
  ON p.employee_id = g.employee_id
LEFT JOIN salary s
  ON ee.salary_id = s.salary_id
LEFT JOIN employee_allowance ea
  ON ee.salary_id = ea.employee_allowance_id;