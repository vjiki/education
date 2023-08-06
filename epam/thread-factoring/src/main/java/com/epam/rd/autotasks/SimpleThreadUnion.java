package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.NORM_PRIORITY;

public class SimpleThreadUnion implements ThreadUnion {
    private final String threadUnionName;
    private final List<FinishedThreadResult> finishedThreadResultList;
    private final AtomicInteger threadCount;
    private final ThreadGroup threadGroup;
    private final List<Thread> threadList;
    private final AtomicBoolean shutdownFlag;

    public SimpleThreadUnion(String name) {
        this.threadUnionName = name;
        this.finishedThreadResultList = Collections.synchronizedList(new ArrayList<>());
        this.threadCount = new AtomicInteger();
        this.threadGroup = new ThreadGroup(name+"Group");
        this.shutdownFlag = new AtomicBoolean(false);
        this.threadList = new ArrayList<>();
    }

    @Override
    public int totalSize() {
        return threadCount.get();
    }

    @Override
    public int activeSize() {
        return threadGroup.activeCount();
    }

    @Override
    public void shutdown() {
        for (Thread thread : threadList) {
            thread.interrupt();
        }

        shutdownFlag.set(true);
    }

    @Override
    public boolean isShutdown() {
        return shutdownFlag.get();
    }

    @Override
    public void awaitTermination() {
        boolean isAlive = true;

        while (isAlive) {
            isAlive = false;
            for (Thread thread : threadList) {
                isAlive |= thread.isAlive();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        shutdownFlag.set(true);
    }

    @Override
    public boolean isFinished() {
        //threadGroup.activeCount() == 0 &&
        return   shutdownFlag.get();
    }

    @Override
    public List<FinishedThreadResult> results() {
        return finishedThreadResultList;
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable r) {

        if (shutdownFlag.get()) {
            throw new IllegalStateException();
        }

        String name = String.format("%s-worker-%d", threadUnionName, threadCount.getAndIncrement());

        //CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Thread thread = new Thread(threadGroup, () -> {
            try {
                r.run();
                finishedThreadResultList.add(new FinishedThreadResult(name));
            } catch (Throwable throwable) {
                finishedThreadResultList.add(new FinishedThreadResult(name, throwable));
            }
        }, name);

        //System.out.println(thread.getThreadGroup() + " " + threadGroup + " active count: " + thread.getThreadGroup().activeCount());

        threadList.add(thread);

        thread.setPriority(NORM_PRIORITY);
        thread.setDaemon(false);

        return thread;
    }
}
