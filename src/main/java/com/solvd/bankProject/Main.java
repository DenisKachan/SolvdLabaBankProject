package com.solvd.bankProject;

import com.solvd.bankProject.consoleScanner.CreationObjectsFromConsole;
import com.solvd.bankProject.structureOfTheBank.ManagementDepartment;

public class Main {
    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {

            Reflection reflection = new Reflection();
            reflection.invokeCertainMethod();

            Runnable task = () -> {
                ManagementDepartment managementDepartment = new ManagementDepartment();
                managementDepartment.showBalance();
            };
            Thread thread = new Thread(task);
            thread.start();

            Thread thread1 = ConnectionPool.getInstance().getThread();
        }
    }
}
