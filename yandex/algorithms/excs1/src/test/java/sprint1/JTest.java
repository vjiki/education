package sprint1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JTest {

    static boolean isPrime(int n)
    {
        if (n <= 1)
            return false;

        if (n == 2 || n == 3)
            return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i < Math.sqrt(n); i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    private static List<Integer> factorize(int n) {
        if (n < 2 || n > 1000000000) {
            return Collections.emptyList();
        }

        List<Integer> factorization = new ArrayList<>();
        for (int i = 2; i * i <= n; i ++) {
            while (n % i == 0) {
                factorization.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            factorization.add(n);
        }

        factorization.sort(Integer::compareTo);

        return factorization;
    }

    @Test
    void successTest1() {
        assertEquals(List.of(3,5), factorize(15));
    }

    @Test
    void successTest2() {
        assertEquals(List.of(13), factorize(13));
    }

    @Test
    void successTest3() {
        assertEquals(List.of(2, 2, 5, 5), factorize(100));
    }

}
