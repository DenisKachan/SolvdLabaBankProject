package com.solvd.bankProject;

import com.solvd.bankProject.clientsOfTheBank.BaseClient;
import com.solvd.bankProject.clientsPropertyAndHistory.CreditCard;
import com.solvd.bankProject.structureOfTheBank.ManagementDepartment;

import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureImplementation {

    private int maxSize = 5;
    ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
    List<CompletableFuture<Void>> futures = new CopyOnWriteArrayList<>();

    public void limitFutures() {
        for (int i = 0; i < maxSize; i++) {
            final int finalI = i;
         CompletableFuture<Void> completableFuture =   CompletableFuture
                    .supplyAsync(() -> doSomething((ConcurrentHashMap<CreditCard, BaseClient>)
                            ManagementDepartment.cardClientsIndividuals, finalI))
                    .thenAccept(str -> map.put(finalI, (String) str));
            futures.set(i,completableFuture);

        }
        CompletableFuture.allOf((CompletableFuture<?>) futures);
    }

    private String doSomething(ConcurrentHashMap<CreditCard, BaseClient> data, int finalI) {
        String result = null;
        for (int i = 0; i < data.size(); i++) {
        result =  data.get(i).getName();
        }
        return result;

    }
}