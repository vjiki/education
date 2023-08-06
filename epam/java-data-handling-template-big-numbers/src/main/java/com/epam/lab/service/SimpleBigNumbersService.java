package com.epam.lab.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * divides the first number by the second with a given precision
     * For example, 1/3 with a precision 2 gives 0.33
     * @param range precision
     * @return result
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), range, RoundingMode.HALF_EVEN);
    }

    /**
     * returns a primary number by ordered number
     *
     * @param range ordered number, starting from the number '2'
     * @return primary number
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger prime = BigInteger.valueOf(2);
        for (int i =0; i< range; i++) {
            prime = prime.nextProbablePrime();
        }
        return prime;
    }
}
