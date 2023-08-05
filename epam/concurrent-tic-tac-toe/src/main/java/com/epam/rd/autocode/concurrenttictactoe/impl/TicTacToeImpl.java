package com.epam.rd.autocode.concurrenttictactoe.impl;

import com.epam.rd.autocode.concurrenttictactoe.TicTacToe;

import java.util.Arrays;

public class TicTacToeImpl implements TicTacToe {

    private char[][] table;
    char lastMark;

    public TicTacToeImpl() {
        this.table =  new char[3][3];
        this.lastMark = ' ';
        Arrays.stream(table).forEach(e -> Arrays.fill(e,' '));
    }

    /**
     * Sets a mark in cell with specified coordinates.
     *
     * @param x    - x coordinate.
     * @param y    - y coordinate.
     * @param mark - mark to set.
     */
    @Override
    public void setMark(int x, int y, char mark) {
        if (x < 3 && y < 3) {
            if (table[x][y] == ' ') {
                lastMark = mark;
                table[x][y] = mark;
                //System.out.println("setting mark " + x + " " + y + " " + mark);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Returns a COPY of current table with marks.
     * Note, edit of that copy should not affect the source TicTacToe object.
     *
     * @return a copy of current table.
     */
    @Override
    public char[][] table() {
        return Arrays.stream(table).map(char[]::clone).toArray(char[][]::new);
    }

    /**
     * Returns last mark that was set in a table.
     *
     * @return last mark that was set in a table.
     */
    @Override
    public char lastMark() {
        return lastMark;
    }
}
