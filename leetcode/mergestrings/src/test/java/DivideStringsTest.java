import org.junit.jupiter.api.Test;
import org.leetcode.DivideStrings;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivideStringsTest {

    @Test
    void successTestABC() {

        String result = DivideStrings.gcdOfStrings("ABC", "ABCABC");
        assertEquals("ABC", result);
    }

    @Test
    void successTestABAB() {

        String result = DivideStrings.gcdOfStrings("ABABAB", "ABAB");
        assertEquals("AB", result);
    }

    @Test
    void successTest() {

        String result = DivideStrings.gcdOfStrings("LEET", "CODE");
        assertEquals("", result);
    }

    @Test
    void successTest1() {

        String result = DivideStrings.gcdOfStrings("ABABABAB", "ABAB");
        assertEquals("ABAB", result);
    }

    @Test
    void successTestTAUX() {

        String result = DivideStrings.gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX");
        assertEquals("TAUXX", result);
    }

}
