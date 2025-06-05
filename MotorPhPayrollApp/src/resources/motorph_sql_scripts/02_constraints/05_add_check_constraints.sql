-- Enforce data validity and business rules via CHECK constraints
/*
-- 1. EmployeePersonalInformation
 ALTER TABLE employee_personal_information
   ADD CONSTRAINT chk_personal_birthday
     CHECK (birthday < CURRENT_DATE);  -- no future birth dates
*/
ALTER TABLE employee_personal_information
  ADD CONSTRAINT chk_personal_phone_numeric
    CHECK (phone_number REGEXP '^[0-9]+$');  -- digits only for phone 

-- 2. EmployeeAddress
ALTER TABLE employee_address
  ADD CONSTRAINT chk_postal_code_format
    CHECK (postal_code REGEXP '^[0-9]{4}$');  -- 4-digit postal codes 

-- 3. EmployeeGovernmentInformation
ALTER TABLE employee_government_information
  ADD CONSTRAINT chk_sss_number_format
    CHECK (sss_number REGEXP '^[0-9]{10}$');  -- exactly 10 digits

ALTER TABLE employee_government_information
  ADD CONSTRAINT chk_philhealth_format
    CHECK (philhealth_number REGEXP '^[0-9]{12}$');  -- exactly 12 digits

ALTER TABLE employee_government_information
  ADD CONSTRAINT chk_pagibig_format
    CHECK (pagibig_number REGEXP '^[0-9]{12}$');  -- exactly 12 digits

ALTER TABLE employee_government_information
  ADD CONSTRAINT chk_tin_format
    CHECK (tax_identification_number REGEXP '^[0-9]{12}$');  -- 12-digit TIN
/*
-- 4. EmployeeEmploymentInformation
 ALTER TABLE employee_employment_information
   ADD CONSTRAINT chk_date_hired_past
     CHECK (date_hired <= CURRENT_DATE);  -- no future hire dates
*/
-- 5. SupervisorAssignment
ALTER TABLE supervisor_assignment
  ADD CONSTRAINT chk_supervision_period
    CHECK (end_date > start_date);  -- end must follow start
/*
ALTER TABLE supervisor_assignment
  ADD CONSTRAINT chk_not_self_supervisor
    CHECK (employee_id <> supervisor_id);  -- can’t supervise oneself
*/    
-- 6. Salary
ALTER TABLE salary
  ADD CONSTRAINT chk_basic_salary_positive
    CHECK (basic_salary > 0),  -- salary must be > 0
  ADD CONSTRAINT chk_gross_semi_monthly_rate_positive
    CHECK (gross_semi_monthly_rate > 0),  -- salary must be > 0
  ADD CONSTRAINT chk_hourly_rate_positive
    CHECK (hourly_rate > 0);  -- salary must be > 0

-- 7. DailyTimeRecord
ALTER TABLE daily_time_record
  ADD CONSTRAINT chk_late_hours_non_negative
    CHECK (late_hours >= 0),
  ADD CONSTRAINT chk_overtime_hours_non_negative
    CHECK (overtime_hours >= 0),
  ADD CONSTRAINT chk_worked_hours_non_negative
    CHECK (worked_hours >= 0);  -- no negative hours

-- 8. AttendanceProcessing
ALTER TABLE attendance_processing
  ADD CONSTRAINT chk_total_late_hours_non_negative
    CHECK (total_late_hours >= 0),
  ADD CONSTRAINT chk_total_overtime_hours_non_negative
    CHECK (total_approved_overtime_hours >= 0),
  ADD CONSTRAINT chk_total_worked_hours_non_negative
    CHECK (total_worked_hours >= 0),
  ADD CONSTRAINT chk_payable_hours_non_negative
    CHECK (payable_hours >= 0);  -- non-negative summaries
    
-- 9. EmployeeLeave
ALTER TABLE employee_leave
  ADD CONSTRAINT chk_total_days_non_negative
    CHECK (total_days >= 0);  -- leave days can’t be negative

-- 10. EmployeeAllowance
ALTER TABLE employee_allowance
  ADD CONSTRAINT chk_allowance_amount_non_negative
    CHECK (amount >= 0);  -- allowance amount ≥ 0

-- 11. GovernmentDeductionChartType    
ALTER TABLE deduction_chart_type
  ADD CONSTRAINT chk_deduction_chart_type_code_not_empty 
    CHECK (chart_code <> ''),
  ADD CONSTRAINT chk_deduction_chart_type_name_not_empty 
    CHECK (chart_name <> '');

-- 12. GovernmentDeductionChart
ALTER TABLE government_deduction_chart
  ADD CONSTRAINT chk_minimum_range_non_negative
    CHECK (minimum_range >= 0),  -- salary brackets start at zero or above
  ADD CONSTRAINT chk_maximum_range_greater_than_minimum
    CHECK (maximum_range > minimum_range),  -- upper bound above lower bound
  ADD CONSTRAINT chk_employee_deduction_rate_valid
    CHECK (employee_deduction_rate BETWEEN 0 AND 100),  -- percent in 0–100
  ADD CONSTRAINT chk_employer_contribution_rate_maximum
    CHECK (employer_contribution_share <= 100);   -- percent in 0–100
/*
-- 12. DeductionChart
ALTER TABLE deduction_chart
  ADD CONSTRAINT chk_sss_only_rates
    CHECK (
      chart_type_id <> (
        SELECT chart_type_id FROM chart_type WHERE chart_name = 'sss'
      )
      OR (
           employee_rate  IS NOT NULL
       AND employer_rate  IS NOT NULL
       AND base_tax_amount IS NULL
       AND tax_rate        IS NULL
       AND fixed_amount   IS NULL
      )
    );
    
ALTER TABLE deduction_chart
  ADD CONSTRAINT chk_pagibig_only_rates
    CHECK (
      chart_type_id <> (
        SELECT chart_type_id FROM chart_type WHERE chart_name = 'pagibig'
      )
      OR (
           employee_rate  IS NOT NULL
       AND employer_rate  IS NOT NULL
       AND base_tax_amount IS NULL
       AND tax_rate        IS NULL
       AND fixed_amount   IS NULL
      )
    );
    
ALTER TABLE deduction_chart
  ADD CONSTRAINT chk_withholding_only
    CHECK (
      chart_type_id <> (
        SELECT chart_type_id FROM chart_type WHERE chart_name = 'withholding'
      )
      OR (
           base_tax_amount IS NOT NULL
       AND tax_rate        IS NOT NULL
       AND employee_rate   IS NULL
       AND employer_rate   IS NULL
       AND fixed_amount    IS NULL
      )
    );
    
ALTER TABLE deduction_chart
  ADD CONSTRAINT chk_philhealth_only
    CHECK (
      chart_type_id <> (
        SELECT chart_type_id FROM chart_type WHERE chart_name = 'philhealth'
      )
      OR (
           fixed_amount    IS NOT NULL
       AND employee_rate  IS NULL
       AND employer_rate  IS NULL
       AND base_tax_amount IS NULL
       AND tax_rate        IS NULL
      )
    );

-- Add data‐integrity constraints to sss_contribution_chart
ALTER TABLE sss_contribution_chart
  ADD CONSTRAINT chk_sss_min_salary_non_negative
    CHECK (min_salary >= 0),
  ADD CONSTRAINT chk_sss_contribution_amount_non_negative
    CHECK (contribution_amount >= 0),
  ADD CONSTRAINT chk_sss_max_salary_logical
    CHECK (max_salary IS NULL OR max_salary >= min_salary);
    
-- Add data-integrity constraints to pag_ibig_contribution_chart
ALTER TABLE pag_ibig_contribution_chart
  ADD CONSTRAINT chk_pibig_min_salary_non_negative
    CHECK (min_salary >= 0),
  ADD CONSTRAINT chk_pibig_max_salary_logical
    CHECK (max_salary IS NULL OR max_salary >= min_salary),
  ADD CONSTRAINT chk_pibig_employee_rate_non_negative
    CHECK (employee_rate >= 0),
  ADD CONSTRAINT chk_pibig_employer_rate_non_negative
    CHECK (employer_rate >= 0),
  ADD CONSTRAINT chk_pibig_max_contribution_non_negative
    CHECK (max_contribution >= 0);
    
-- Add data-integrity constraints to philhealth_contribution_chart
ALTER TABLE philhealth_contribution_chart
  ADD CONSTRAINT chk_phc_min_salary_non_negative
    CHECK (min_salary >= 0),
  ADD CONSTRAINT chk_phc_max_salary_logical
    CHECK (max_salary IS NULL OR max_salary >= min_salary),
  ADD CONSTRAINT chk_phc_premium_rate_non_negative
    CHECK (premium_rate >= 0),
  ADD CONSTRAINT chk_phc_min_premium_non_negative
    CHECK (min_premium >= 0),
  ADD CONSTRAINT chk_phc_max_premium_logical
    CHECK (max_premium >= min_premium);
    
-- Add data‐integrity constraints to withholding_tax_bracket
ALTER TABLE withholding_tax_bracket
  ADD CONSTRAINT chk_wtb_min_salary_non_negative
    CHECK (min_salary >= 0),
  ADD CONSTRAINT chk_wtb_max_salary_logical
    CHECK (max_salary IS NULL OR max_salary >= min_salary),
  ADD CONSTRAINT chk_wtb_base_tax_amount_non_negative
    CHECK (base_tax_amount >= 0),
  ADD CONSTRAINT chk_wtb_tax_rate_non_negative
    CHECK (tax_rate >= 0);
*/
-- 13. PayPeriod
ALTER TABLE pay_period
  ADD CONSTRAINT chk_pay_period_end_after_start
    CHECK (end_date > start_date),  -- end must follow start 
  ADD CONSTRAINT chk_pay_period_payday_after_end
    CHECK (pay_day > end_date),  -- payday after period end
  ADD CONSTRAINT chk_pay_period_due_before_payday
    CHECK (payroll_due <= pay_day);  -- payroll due on or before payday 
    
-- 14. PayrollRecord
-- ALTER TABLE payroll_record
--   ADD CONSTRAINT chk_payroll_net_salary_non_negative
--     CHECK (net_salary >= 0),  -- net salary cannot be negative
--   ADD CONSTRAINT chk_payroll_total_deductions_non_negative
--     CHECK (total_deductions >= 0);  -- total deductions cannot be negative
    
-- 15. Payslip
ALTER TABLE payslip
  ADD CONSTRAINT chk_payslip_net_salary_non_negative
    CHECK (net_salary >= 0),  -- net salary cannot be negative
  ADD CONSTRAINT chk_payslip_total_deductions_non_negative
    CHECK (total_deductions >= 0);  -- total deductions cannot be negative
