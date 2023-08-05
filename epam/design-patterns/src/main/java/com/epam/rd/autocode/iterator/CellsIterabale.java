package com.epam.rd.autocode.iterator;

import java.util.Iterator;

public class CellsIterabale implements Iterable<String> {
    String[] columns;
    int[] rows;

    public CellsIterabale(String[] columns, int[] rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public Iterator<String> iterator() {
        return new CellsIterator<String>(columns, rows);
    }
}