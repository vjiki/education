package com.epam.rd.autocode.decorator;

import java.util.Iterator;
import java.util.List;

public abstract class StringSubList {
    List<String> subList;

    public abstract List<String> get();

    public abstract int size();

    public abstract Iterator<String> iterator();
}
