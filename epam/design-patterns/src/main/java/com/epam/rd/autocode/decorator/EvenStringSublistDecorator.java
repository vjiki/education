package com.epam.rd.autocode.decorator;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EvenStringSublistDecorator extends StringSubList {

    public EvenStringSublistDecorator(List<String> sourceList) {
        subList = IntStream
                .range(0, sourceList.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(sourceList::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> get() {
        return subList;
    }

    @Override
    public int size() {
        return subList.size();
    }

    @Override
    public Iterator<String> iterator() {
        return subList.iterator();
    }
}
