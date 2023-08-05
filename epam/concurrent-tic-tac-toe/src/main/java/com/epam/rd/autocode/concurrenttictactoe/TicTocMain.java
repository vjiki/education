package com.epam.rd.autocode.concurrenttictactoe;


import com.epam.rd.autocode.concurrenttictactoe.impl.PlayerStrategyImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TicTocMain {

    public static void main(String[] args) {

//        char[][] table = new char[3][3];
//        Arrays.stream(table).forEach(e -> Arrays.fill(e,'E'));
//        //Arrays.fill(table.,'e');
//
//        for (char[] chars : table) {
//            System.out.println("\n");
//            for (char aChar : chars) {
//                System.out.printf(" %c ", aChar);
//            }
//        }
//        table[0][0] = 'X';
//
//        for (char[] chars : table) {
//            System.out.println("\n");
//            for (char aChar : chars) {
//                System.out.printf(" %c ", aChar);
//            }
//        }

//        TicTacToe ticTacToe = TicTacToe.buildGame();
//        ticTacToe.setMark(0,0, 'X');
//        ticTacToe.setMark(0,1, 'O');
//        ticTacToe.setMark(0,2, 'O');
//        ticTacToe.setMark(2,2, 'X');
//        //System.out.println(ticTacToe.lastMark());
//        char[][] table = ticTacToe.table();
//
//        for (char[] chars : table) {
//            for (char aChar : chars) {
//                System.out.printf("%c", aChar);
//            }
//            System.out.println();
//        }
//
//        table[0][2] = 'F';
//
//        char[][] table2 = ticTacToe.table();
//
//        for (char[] chars : table2) {
//            for (char aChar : chars) {
//                System.out.printf("%c", aChar);
//            }
//            System.out.println();
//        }

//        PlayerStrategy playerStrategy = new PlayerStrategyImpl();
//
//        final TicTacToe ticTacToe = TicTacToe.buildGame();
//
//        final List<Thread> playerThreads = new ArrayList<>();
//        final List<Character> marks = Arrays.asList('X', 'O');
//
//        for (int i = 0; i < marks.size(); i++) {
//            Player player = Player.createPlayer(ticTacToe, marks.get(i), playerStrategy);
//            Thread thread = new Thread(player);
//            playerThreads.add(thread);
//        }
//
//        playerThreads.forEach(Thread::start);
//        //playerThreads.forEach(Thread::join);
//        try {
//            playerThreads.get(0).join();
//            playerThreads.get(1).join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(Arrays.stream(ticTacToe.table())
//                .map(String::new)
//                .collect(Collectors.joining("\n")));
//
//
//        TicTacToe ticTacToe1 = TicTacToe.buildGame();
//        ticTacToe1.setMark(0,0, 'O');
//        ticTacToe1.setMark(1,0, 'O');
//        ticTacToe1.setMark(2,0, 'O');
//        ticTacToe1.setMark(1,2, 'X');
//        ticTacToe1.setMark(2,2, 'X');
//
//
//        System.out.println(Arrays.stream(ticTacToe1.table())
//                .map(String::new)
//                .collect(Collectors.joining("\n")));
//
//
//        PlayerImpl player = new PlayerImpl(ticTacToe1,'O', playerStrategy);
//
//        System.out.println();
    }
}
