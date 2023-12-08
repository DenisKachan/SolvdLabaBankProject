package com.solvd.bankProject;

import com.solvd.bankProject.consoleScanner.CreationObjectsFromConsole;
import com.solvd.bankProject.structureOfTheBank.ManagementDepartment;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {

         //   Reflection reflection = new Reflection();
        //    reflection.invokeCertainMethod();

        //    Runnable task = () -> {
         //       ManagementDepartment managementDepartment = new ManagementDepartment();
         //       managementDepartment.showBalance();
          //  };
          //  Thread thread = new Thread(task);
          //  thread.start();

            Thread thread1 = ConnectionPool.getInstance().getThread();
            CompletableFutureImplementation completableFutureImplementation = new CompletableFutureImplementation();
            completableFutureImplementation.getFuture();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
