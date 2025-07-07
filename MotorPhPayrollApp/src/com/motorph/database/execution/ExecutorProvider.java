/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution;

import com.motorph.database.connection.DatabaseService;

/**
 *
 * @author 63909
 */
public class ExecutorProvider {
    private static final SQLExecutor executor = new SQLExecutor(DatabaseService.connectToMotorPH());

    public static SQLExecutor getExecutor() {
        return executor;
    }
}
