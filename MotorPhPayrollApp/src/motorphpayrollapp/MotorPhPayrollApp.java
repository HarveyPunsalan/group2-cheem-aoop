/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package motorphpayrollapp;

/**
 *
 * @author 63909
 */


import com.motorph.payrollprocessing.view.admin.PayrollList;
import com.motorph.usermanagement.view.LoginPage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class MotorPhPayrollApp {

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PayrollList.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
//            UIManager.setLookAndFeel(new FlatLightLaf());
//        } catch (Exception ex) {
//            System.err.println("Failed to initialize FlatLaf");
//        }
        // Launch the login page
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}