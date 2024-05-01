// https://contest.yandex.ru/contest/26133/run-report/103685303/

/*
-- ПРИНЦИП РАБОТЫ --
Каждую запакованную строку (ЗС) распаковываем.
Для этого используется два стека. В один складываем все числа на которые нужно умножать.
Во второй складываем длину под строки которую нужно умножить.
Если в стеке с длинами только один элемент то просто умножаем на количество раз на которое нужно умножить.
Если больше одного то вычисляем сначала текущее умножение строк и плюсом запоминаем длину новой подстроки для
следующих вычислений.

Далее находим наибольщий общий префикс для распакованных строк.
Берем первую строку и считаем ее наибольшим префиксом.
Далее берем вторую строку и находим наибольшую общий префикс для этих строк.
Далее для следующей строки ищем наибольшую общий префикс сравнивая ее с предыдущим общим префиксом.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Для распакованных строк нам просто достаточно вычислить наибольшую подстроку с которых эти строки начинаются.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(n*m), где n - количество строк, m - длина самой большой строки.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(m), где m - длина самой большой строки.
+ хранение двух стеков нужных для распаковки
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BiggestPrefix {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            if (n == 0) {
                System.out.println();
                return;
            }

            String prefix = unzip(reader.readLine());
            for (int i = 0; i < n - 1; i++) {
                String currentRow = unzip(reader.readLine());
                while (!prefix.isBlank() && !currentRow.startsWith(prefix)) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
            }
            System.out.println(prefix);
        }
    }
    static String unzip(String zippedRow) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> multiplyStack = new Stack<>();
        Stack<Integer> lengthStack = new Stack<>();
        int length = 0;
        for(int i = 0; i < zippedRow.length(); i++) {
            char c = zippedRow.charAt(i);
            if(Character.isDigit(c)) {
                multiplyStack.push(Character.getNumericValue(c));
            } else if (c == '[') {
                if (multiplyStack.size() > 1 && Character.isDigit(zippedRow.charAt(i-3))) {
                    lengthStack.push(length);
                }
                if (length > 0) {
                    lengthStack.push(length);
                }
                length = 0;
            } else if (c == ']') {
                if (length != 0) {
                    lengthStack.push(length);
                    length = 0;
                }
                if (lengthStack.isEmpty()) {
                    continue;
                }
                if (lengthStack.size() == 1) {
                    int currentLength = lengthStack.pop();
                    String multiplyString = sb.substring(sb.length() - currentLength, sb.length());
                    int m = multiplyStack.pop();
                    sb.append(String.valueOf(multiplyString).repeat(Math.max(0, m - 1)));
                } else {
                    int previousLength = lengthStack.pop();
                    String multiplyString = sb.substring(sb.length() - previousLength, sb.length());
                    int m = multiplyStack.pop();
                    sb.append(String.valueOf(multiplyString).repeat(Math.max(0, m - 1)));
                    int currentLength = lengthStack.pop() + previousLength * m;
                    lengthStack.push(currentLength);
                }
            } else {
                sb.append(c);
                length++;
            }
        }
        return sb.toString();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
