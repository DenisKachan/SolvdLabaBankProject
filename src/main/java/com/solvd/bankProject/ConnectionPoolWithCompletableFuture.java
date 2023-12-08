package com.solvd.bankProject;

import com.solvd.bankProject.structureOfTheBank.ManagementDepartment;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionPoolWithCompletableFuture {

    private volatile static ConnectionPoolWithCompletableFuture instance;

    private final int poolSize;

    private int amountOfThreads = 0;

    private List<CompletableFuture> threads = new CopyOnWriteArrayList<>();

    private List<CompletableFuture> activeThreads = new CopyOnWriteArrayList<>();

    private ConnectionPoolWithCompletableFuture (int poolSize){
        this.poolSize = poolSize;
    }

    public synchronized static ConnectionPoolWithCompletableFuture getInstance(){
        if (instance==null){
            instance = new ConnectionPoolWithCompletableFuture(5);
        }
        return instance;
    }

    public synchronized boolean isFull()
    {return ((threads.isEmpty())&&(amountOfThreads>=poolSize));}


}