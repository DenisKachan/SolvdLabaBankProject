package com.solvd.bankProject;

import com.solvd.bankProject.structureOfTheBank.ManagementDepartment;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Log4j2
public class ConnectionPool {

    private volatile static ConnectionPool instance;

    private final int poolSize;

    private int amountOfThreads = 0;

    private List<Thread> threads = new CopyOnWriteArrayList<>();

    private List<Thread> activeThreads = new CopyOnWriteArrayList<>();

    private ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
    }

    public synchronized static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool(5);
        }
        return instance;
    }

    public synchronized boolean isFull() {
        return ((threads.isEmpty()) && (amountOfThreads >= poolSize));
    }

    public synchronized Thread getThread() {
        Thread thread = null;
        if (isFull()) {
            log.info("Pool is full of threads or there is no any threads");
        }
        thread = getThreadFromPool();
        if (thread == null) {
            thread = createNewThreadForPool();
        }
        thread = makeAvailable(thread);
        return thread;
    }

    public synchronized void returnThread(Thread thread) {
        if (!activeThreads.remove(thread)) {
            log.info("The connection is already returned");
        }
        threads.add(thread);
    }

    private Thread createNewThreadForPool() {
        Thread thread = createNewThread();
        thread.start();
        amountOfThreads++;
        activeThreads.add(thread);
        return thread;
    }

    private Thread createNewThread() {
        Runnable task = () -> {
            ManagementDepartment managementDepartment = new ManagementDepartment();
            managementDepartment.showBalance();
        };
        return new Thread(task);
    }

    private Thread getThreadFromPool() {
        Thread thread = null;
        if (!threads.isEmpty()) {
            thread = threads.get(0);
            activeThreads.add(thread);
        }
        return thread;
    }

    private Thread makeAvailable(Thread thread) {
        if (thread.isAlive()) {
            return thread;
        }
        activeThreads.remove(thread);
        amountOfThreads--;
        thread.interrupt();
        thread = createNewThread();
        activeThreads.add(thread);
        amountOfThreads++;
        return thread;
    }
}

