package com.epam.lab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class SolutionTest {
    @Test
    void testOne() {
        Method[] listHandlerMethods = Solution.class.getMethods();
        for (Method method: listHandlerMethods) {
            if (method.getName().equals("one")) {
                Assertions.assertEquals(2, method.getGenericParameterTypes().length);
                Assertions.assertEquals("java.util.List<T>", method.getGenericParameterTypes()[0].getTypeName());
                Assertions.assertEquals("java.util.List<T>", method.getGenericParameterTypes()[1].getTypeName());
            }
        }
    }

    @Test
    void testTwo() {
        Method[] listHandlerMethods = Solution.class.getMethods();
        for (Method method: listHandlerMethods) {
            if (method.getName().equals("two")) {
                Assertions.assertEquals(2, method.getGenericParameterTypes().length);
                Assertions.assertEquals("java.util.List<T>", method.getGenericParameterTypes()[0].getTypeName());
                Assertions.assertEquals("java.util.List<? extends T>", method.getGenericParameterTypes()[1].getTypeName());
            }
        }
    }

    @Test
    void testThree() {
        Method[] listHandlerMethods = Solution.class.getMethods();
        for (Method method: listHandlerMethods) {
            if (method.getName().equals("three")) {
                Assertions.assertEquals(2, method.getGenericParameterTypes().length);
                Assertions.assertEquals("java.util.List<? super T>", method.getGenericParameterTypes()[0].getTypeName());
                Assertions.assertEquals("java.util.List<T>", method.getGenericParameterTypes()[1].getTypeName());
            }
        }
    }

    @Test
    void testFour() {
        Method[] listHandlerMethods = Solution.class.getMethods();
        for (Method method: listHandlerMethods) {
            if (method.getName().equals("four")) {
                Assertions.assertEquals(2, method.getGenericParameterTypes().length);
                Assertions.assertEquals("java.util.List<? super T>", method.getGenericParameterTypes()[0].getTypeName());
                Assertions.assertEquals("java.util.List<? extends T>", method.getGenericParameterTypes()[1].getTypeName());
            }
        }
    }
}
