package com.epam.rd.autocode.iterator;

import java.util.Iterator;


public class CellsIterator<S> implements Iterator<String> {
    String[] columns;
    int[] rows;
    int positionColumn = 0;
    int positionRow = 0;

    public CellsIterator(String[] columns, int[] rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public boolean hasNext() {
        return positionColumn < columns.length || positionRow != 0;
    }

    @Override
    public String next() {
        String value;

        if (!hasNext()) {
            //System.out.println("over");
            return null;
        }

        value = columns[positionColumn] + rows[positionRow];
        if (positionRow < rows.length -1 ) {
            positionRow = positionRow + 1;
        } else {
            positionRow = 0;
            positionColumn = positionColumn + 1;
        }

        return value;
    }
}
