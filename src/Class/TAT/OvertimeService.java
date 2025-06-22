/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import Class.SQLService.OvertimeSQLService;

public class OvertimeService {

    private final OvertimeSQLService overtimeDAO;

    public OvertimeService(Connection conn) {
        this.overtimeDAO = new OvertimeSQLService(conn);
    }

    public List<Overtime> getAllOvertimes() {
        List<Overtime> overtimes = new ArrayList<>();
        List<String[]> records = overtimeDAO.getAllOvertimeRecords();

        for (String[] row : records) {
            if (row.length >= 4) {
                overtimes.add(new Overtime(
                    row[0], row[1], row[2], row[3]
                ));
            } else {
                System.err.println("⚠️ Skipped malformed overtime record: " + Arrays.toString(row));
            }
        }

        return overtimes;
    }

    public Overtime getOvertimeByRequestID(String requestID) {
        List<String[]> result = overtimeDAO.getByRequestID(requestID);
        if (!result.isEmpty() && result.get(0).length >= 4) {
            String[] row = result.get(0);
            return new Overtime(row[0], row[1], row[2], row[3]);
        }
        return null;
    }

    // ✅ Optional: For fixing getOvertimeByID error elsewhere
    public Overtime getOvertimeByID(String overtimeID) {
        List<String[]> result = overtimeDAO.getByOvertimeID(overtimeID);
        if (!result.isEmpty() && result.get(0).length >= 4) {
            String[] row = result.get(0);
            return new Overtime(row[0], row[1], row[2], row[3]);
        }
        return null;
    }

    public boolean saveOvertime(Overtime overtime) {
        return overtimeDAO.saveOvertime(
            overtime.getRequestId(),
            overtime.getEmployeeID(),
            overtime.getDtrId()
        );
    }

    public boolean deleteOvertimeById(String overtimeId) {
        return overtimeDAO.deleteOvertimeById(overtimeId);
    }
}
