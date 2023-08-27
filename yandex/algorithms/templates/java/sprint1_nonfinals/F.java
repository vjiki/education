import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class F {

    private static boolean isPalindromeV0(String text) {
        char[] array = text.toCharArray();

        int j = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == array[j]) {
                if (i == j) {
                    return true;
                }
            } else {
                return false;
            }
            j--;
        }

        // Ваше решение
        return false;
    }

    private static boolean isPalindrome(String text) {
        char[] array = text.toCharArray();

        int j = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == array[j]) {
                if (i == j) {
                    return true;
                }
            } else {
                return false;
            }
            j--;
        }

        // Ваше решение
        return false;
    }
    public static void main(String[] args) throws IOException{
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            if (isPalindrome(text)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
