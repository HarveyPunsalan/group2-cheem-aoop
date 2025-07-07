/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.service.core;

import com.motorph.payrollprocessing.dao.payrolldao.PayslipDAO;
import com.motorph.payrollprocessing.model.payroll.Payslip;
import java.util.List;
import java.util.Optional;

public class PayslipService {

    private final PayslipDAO payslipDAO;

    public PayslipService(PayslipDAO payslipDAO) {
        this.payslipDAO = payslipDAO;
    }

    public int generatePayslip(Payslip payslip) {
        return payslipDAO.insert(payslip);
    }

    public Optional<Payslip> getPayslipById(int id) {
        return payslipDAO.findById(id);
    }

    public Optional<Payslip> getPayslipByPayPeriodAndEmployee(int payPeriodId, int employeeId) {
        return payslipDAO.findByPayPeriodAndEmployee(payPeriodId, employeeId);
    }

    public List<Payslip> getAllPayslips() {
        return payslipDAO.findAll();
    }

    public boolean deletePayslip(int id) {
        return payslipDAO.deleteById(id) > 0;
    }

    public int updatePayslip(Payslip payslip) {
        return payslipDAO.update(payslip);
    }
}

