package com.solvd.bankProject;

import com.solvd.bankProject.structureOfTheBank.ManagementDepartment;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

@Log4j2
public class CompletableFutureImplementation {

    private int maxSize = 5;

    private static int activeFutures = 0;
    static List<CompletableFuture<Void>> futures = new CopyOnWriteArrayList<>();

    List<CompletableFuture<Void>> activeFuturesList = new CopyOnWriteArrayList<>();


    public synchronized boolean isFull() {
        return ((futures.isEmpty()) && (activeFutures >= maxSize));
    }

    public synchronized CompletableFuture<Void> getFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = null;
        if (isFull()) {
            log.info("Pool is full of threads or there is no any threads");
        }
        completableFuture = getFutureFromPool();
        completableFuture = makeAvailable(completableFuture);
        return completableFuture;
    }


    public synchronized void returnFuture(CompletableFuture<Void> completableFuture) {
        if (!activeFuturesList.remove(completableFuture)) {
            log.info("The connection is already returned");
        }
        futures.add(completableFuture);
    }

    private CompletableFuture<Void> createNewFutureForPool() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = createNewFuture();
        completableFuture.get();
        activeFutures++;
        activeFuturesList.add(completableFuture);
        return completableFuture;
    }

    private CompletableFuture<Void> createNewFuture() {
        CompletableFuture<Void> completableFuture =   CompletableFuture
                .runAsync(()->{
                    ManagementDepartment managementDepartment = new ManagementDepartment();
                    managementDepartment.showBalance();
                });
        return completableFuture;
    }


    private CompletableFuture<Void> getFutureFromPool() {
        CompletableFuture<Void> completableFuture = null;
        if (!futures.isEmpty()) {
            completableFuture = futures.get(0);
            activeFuturesList.add(completableFuture);
        }
        return completableFuture;
    }

    private CompletableFuture<Void> makeAvailable(CompletableFuture<Void> completableFuture) throws ExecutionException, InterruptedException {
        activeFuturesList.remove(completableFuture);
        activeFutures--;
        completableFuture = createNewFutureForPool();
        activeFuturesList.add(completableFuture);
        activeFutures++;
        return completableFuture;
    }
}