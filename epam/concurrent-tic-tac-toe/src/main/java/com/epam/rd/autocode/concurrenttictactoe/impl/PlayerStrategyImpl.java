package com.epam.rd.autocode.concurrenttictactoe.impl;

import com.epam.rd.autocode.concurrenttictactoe.Move;
import com.epam.rd.autocode.concurrenttictactoe.PlayerStrategy;
import com.epam.rd.autocode.concurrenttictactoe.TicTacToe;

public class PlayerStrategyImpl implements PlayerStrategy {
    /**
     * Computes a new Move.
     *
     * @param mark      - mark to set in move.
     * @param ticTacToe - board to make a move.
     * @return a move - combination of x and y coordinates.
     */
    @Override
    public Move computeMove(final char mark, final TicTacToe ticTacToe) {
        final char[][] table = ticTacToe.table();
        for (int dia = 0; dia < 5; dia++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i + j == dia && table[i][j] == ' ') {
                        return new Move(i, j);
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }
}
