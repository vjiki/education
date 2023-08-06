package com.epam.lab;

import java.math.BigDecimal;
import java.util.List;

public interface ListHandler {

    BigDecimal sum(List<? extends Number> list);

    BigDecimal multiply(List<? extends Number> list);

    String concat(List<?> list);

    List<?> combine(List<? extends java.util.Collection> list);
}
