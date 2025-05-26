-- 02_constraints/01_add_not_null_constraints.sql

ALTER TABLE deduction_chart_type
  MODIFY chart_code VARCHAR(50)    NOT NULL,
  MODIFY chart_name VARCHAR(100)   NOT NULL,
  MODIFY created_at TIMESTAMP NOT NULL,
  MODIFY updated_at TIMESTAMP NOT NULL;