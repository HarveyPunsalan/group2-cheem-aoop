/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.service.core;

import com.motorph.common.util.DateUtil;
import com.motorph.payrollprocessing.comboboxmodel.ComboBoxModelFactory;
import com.motorph.payrollprocessing.dao.payrolldao.PayPeriodDAO;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author 63909
 */
public class PayPeriodService {
    
    private final PayPeriodDAO payPeriodDAO;

    public PayPeriodService(PayPeriodDAO dao) {
        this.payPeriodDAO = dao;
    }
    
    public List<PayPeriod> getAllPayPeriod() {        
        return payPeriodDAO.findAll();
    }
    
    // Search pay periods that fall within a date range
    public Optional<PayPeriod> searchByDateRange(String startDate, String endDate) {        
        return payPeriodDAO.findByDateRange(DateUtil.parseDate(startDate), DateUtil.parseDate(endDate));
    }
    
    public Optional<PayPeriod> searchByDateRange(LocalDate startDate, LocalDate endDate) {        
        return payPeriodDAO.findByDateRange(startDate, endDate);
    }
    
    public DefaultComboBoxModel<String> getPayPeriodComboBoxModel(String dateFormat) {
        return ComboBoxModelFactory.createPayPeriodModel(getAllPayPeriod(), dateFormat);
    }
}
