package com.epam.rd.autocode.concurrenttictactoe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PlayerImpl implements Player {
    private final TicTacToe ticTacToe;
    private final char mark;
    private final PlayerStrategy strategy;
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static final Condition conditionMet = lock.newCondition();
    private final AtomicBoolean hasWon = new AtomicBoolean(false);


    public PlayerImpl(final TicTacToe ticTacToe, final char mark, final PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }

    private boolean hasWon(char markXO) {

        char[][] table = ticTacToe.table();

        for (int m = 0; m < 3; m++) {
            boolean hasWonHorizontally = true;
            for (int i = 0; i < 3; i++) {
                hasWonHorizontally &= table[m][i] == markXO;
            }
            if(hasWonHorizontally) {
                //System.out.println("hasWonHorizontally");
                return true;
            }
        }


        for (int m = 0; m < 3; m++) {
            boolean hasWonVertically = true;
            for (int i = 0; i < 3; i++) {
                hasWonVertically &= table[i][m] == markXO;
            }
            if(hasWonVertically) {
                //System.out.println("hasWonVertically");
                return true;
            }
        }


        boolean hasWonDiagLeft = true;
        for (int i = 0; i < 3; i++) {
            hasWonDiagLeft &= table[i][i] == markXO;
        }
        if(hasWonDiagLeft) {
            //System.out.println("hasWonDiagLeft");
            return true;
        }

        boolean hasWonDiagRight = true;
        int j = 2;
        for (int i = 0; i < 3; i++) {
            hasWonDiagRight &= table[i][j] == markXO;
            j--;
        }
        //System.out.println("hasWonDiagRight");
        return hasWonDiagRight;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //System.out.println("starting " + this);

        if (mark == 'O') {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            try {
                lock.lock();
                conditionMet.await(100, TimeUnit.MILLISECONDS);

                hasWon.set(hasWon('O') | hasWon('X'));
                if (hasWon.get()) {
                    break;
                } else if (ticTacToe.lastMark() == mark) {
                    conditionMet.signal();
                } else {
                    //System.out.println("doing move" + this);
                    try {
                        Move newMove = strategy.computeMove(mark, ticTacToe);
                        //System.out.println("row: " + newMove.row + " column: " + newMove.column + " column: " + mark);
                        ticTacToe.setMark(newMove.row, newMove.column, mark);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    hasWon.set(hasWon('O') | hasWon('X'));
                    conditionMet.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            } finally {
                lock.unlock();
            }
        }
    }
}
