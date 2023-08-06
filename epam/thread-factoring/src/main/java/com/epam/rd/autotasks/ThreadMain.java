package com.epam.rd.autotasks;

import static java.lang.Thread.sleep;

public class ThreadMain {

    private void printThreadName() {
        System.out.println(Thread.currentThread().getName());
    }

    void testShutdown(ThreadUnion threadUnion) {
        threadUnion.newThread(this::printThreadName);
    }


    public static void main(String[] args) {




//        Thread thread = new Thread(() -> {
//            try {
//                sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("dsds 1\n");
//        });
//
//        thread.setPriority(MAX_PRIORITY);
//        thread.setDaemon(false);
//
//        Thread thread2 = new Thread(() -> {
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("dsds 2\n");
//        });
//
//        thread2.setPriority(MIN_PRIORITY);
//        thread2.setDaemon(false);
//        thread.setName("Thread_" + thread.getId());
//        thread2.setName("Thread_" + thread2.getId());
//
//        thread.start();
//        thread2.start();
//
//        System.out.println(thread.getName());
//        System.out.println(thread2.getName());

        ThreadUnion threadUnion = ThreadUnion.newInstance("myUnion");
        Thread thread = threadUnion.newThread(() -> {

            for (int i = 0; i < 50; i++) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("dsds 1\n");

            }
            //int i = 1/0;
        });

        Thread thread2 = threadUnion.newThread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("dsds 2\n");

            }
        });

        System.out.println(threadUnion.results().toString());

        thread.start();
        thread2.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(thread.isAlive());
            System.out.println(thread2.isAlive());
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println(threadUnion.isFinished());
        System.out.println(threadUnion.isShutdown());
        threadUnion.shutdown();
        System.out.println(threadUnion.isFinished());
        System.out.println(threadUnion.isShutdown());
        System.out.println(threadUnion.activeSize());
        System.out.println(threadUnion.totalSize());


        ThreadMain threadMain = new ThreadMain();

        threadMain.testShutdown(threadUnion);

        //thread.start();

    }
}
