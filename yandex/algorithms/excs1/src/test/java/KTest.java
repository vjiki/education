import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KTest {
    private static List<Integer> getSum(List<Integer> numberList, int k) {

        char[] chars = String.valueOf(k).toCharArray();
        int indexK = chars.length -1;
        List<Integer> result = new ArrayList<>();


//        for (int i = chars.length -1; i >= 0; i--) {
//            System.out.println(String.valueOf(chars[i]));
//
//        }

        int extraSum = 0;
        for (int i = numberList.size() -1; i >= 0; i--) {
//            System.out.println(String.valueOf(numberList.get(i)));
            if (indexK >= 0) {
                int sum = numberList.get(i) + Integer.parseInt(String.valueOf(chars[indexK]))  + extraSum;
                char[] sumChars = String.valueOf(sum).toCharArray();
                int lastChar = Integer.parseInt(String.valueOf(sumChars[0]));
                if (sumChars.length == 1) {
                    extraSum = 0;
                    result.add(0,lastChar);
                } else {
                    extraSum = lastChar;
                    result.add(0,Integer.parseInt(String.valueOf(sumChars[1])));
                }
                indexK--;
            } else {
                result.add(0,numberList.get(i) + extraSum);
            }
        }
        if (extraSum != 0) {
            result.add(0,extraSum);
        }

        return result;
    }

    @Test
    void successTest1() {
        assertEquals(List.of(1, 2, 3, 4), getSum(List.of(1,2,0,0), 34));
    }

    @Test
    void successTest2() {
        assertEquals(List.of(1, 1, 2), getSum(List.of(9, 5), 17));
    }

    @Test
    void successTest3() {
        assertEquals(List.of(7, 9, 9, 4), getSum(List.of(7, 9, 9 , 1), 3));
    }

    // 1 4 2 7 5 1 7 9 7 9 0 8 6 9 5 9 8 6 6 2 0 1 1 6 2 1 2 8 7 2 6 9 8 4 9 2 6 1 0 9
    // 1 4 2 7 5 1 7 9 7 9 0 8 6 9 5 9 8 6 6 2 0 1 1 6 2 1 2 8 7 2 6 9 8 4 9 2 6 1 7 2
}
