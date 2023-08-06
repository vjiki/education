package com.epam.lab;

import com.epam.lab.service.RegExpService;
import com.epam.lab.service.SimpleRegExpService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegExpServiceTest {

    private static RegExpService regExpService;

    @BeforeAll
    static void setup() {
        regExpService = new SimpleRegExpService();
    }

    @Test
    void testMaskSensitiveData() {
        assertEquals("Yesterday evening a transfer was made from account number 4301 **** **** 2140 " +
                        "to account 5042 **** **** 2043 in the amount of $ ${payment_amount}. " +
                        "$ ${balance} left on the account",
                regExpService.maskSensitiveData());
    }

    @Test
    void testGetPrecisionNumber() {
        assertEquals("Yesterday evening a transfer was made from account number 4301 0234 2145 2140 " +
                        "to account 5042 2012 0532 2043 in the amount of $ 1. " +
                        "$ 2 left on the account",
                regExpService.replacePlaceholders(1, 2));
    }

}
