package com.epam.lab;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleListHandler implements ListHandler {

    /**
     * should return sum of all the elements of the list. List can contain any numbers.
     */
    @Override
    public BigDecimal sum(List<? extends Number> list) {
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < list.size(); i++) {
            Number num = (Number) list.get(i);
            result = result.add(new BigDecimal(String.valueOf(num)));
        }
        return result;
    }

    /**
     * should multiply all the elements of the list. List can contain any numbers.
     */
    @Override
    public BigDecimal multiply(List<? extends Number> list) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 0; i < list.size(); i++) {
            Number num = (Number) list.get(i);
            result = result.multiply(new BigDecimal(String.valueOf(num)));
        }
        return result;
    }

    /**
     * should return string that contains all the elements of the list
     */
    @Override
    public String concat(List<?> list) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : list) {
            builder.append(obj);
        }
        return builder.toString();
    }

    /**
     * @param list - list of collections of any elements
     * @return list that contains all the elements from these collections
     */
    @Override
    public List<?> combine(List<? extends Collection> list) {
        List result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Collection collection = (Collection) list.get(i);
            result.addAll(collection);
        }
        return result;
    }
}
