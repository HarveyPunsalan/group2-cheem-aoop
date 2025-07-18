/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Overtime;
import java.util.ArrayList;
import java.util.List;

public class OvertimeService {
    private final List<Overtime> overtimeList = new ArrayList<>();

    // Create
    public void addOvertime(Overtime overtime) {
        overtimeList.add(overtime);
    }

    // Read all
    public List<Overtime> getAllOvertimes() {
        return new ArrayList<>(overtimeList);
    }

    // Read by ID
    public Overtime getOvertimeById(int id) {
        for (Overtime ot : overtimeList) {
            if (ot.getOvertimeId() == id) {
                return ot;
            }
        }
        return null;
    }

    // Update
    public boolean updateOvertime(Overtime updatedOvertime) {
        for (int i = 0; i < overtimeList.size(); i++) {
            if (overtimeList.get(i).getOvertimeId() == updatedOvertime.getOvertimeId()) {
                overtimeList.set(i, updatedOvertime);
                return true;
            }
        }
        return false;
    }

    // Delete
    public boolean deleteOvertime(int id) {
        return overtimeList.removeIf(ot -> ot.getOvertimeId() == id);
    }

    // For testing: get the most recently added Overtime
    public Overtime getLatestOvertime() {
        if (overtimeList.isEmpty()) return null;
        return overtimeList.get(overtimeList.size() - 1);
    }
}
