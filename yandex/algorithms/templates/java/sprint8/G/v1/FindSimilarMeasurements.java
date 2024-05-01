import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FindSimilarMeasurements {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int mesLength = readInt(reader);
            int[] measures = readArray(reader);
            int patternLength = readInt(reader);
            int[] pattern = readArray(reader);

            findAll(measures, pattern);
        }
    }

    public static void findAll(int[] measures, int[] pattern) {
        Set<Integer> occurrences = new TreeSet<>();
        int start = 0; // Начнём поиск с начала строки.
        // Найдём первое вхождение, если оно есть.
        while (true) {
            int diff = measures[start] - pattern[0];
//            System.out.println(diff);
            int pos = find(measures, pattern, start, diff);
            if (start + pattern.length > measures.length) {
                break;
            }
            if (pos != -1) {
                occurrences.add(pos+1); // Сохраним вхождение в список.
            }
            start += 1;       // И продолжим поиск, начиная с позиции,
            // следующей за только что найденной.
        }
        StringBuilder sb = new StringBuilder();
        for (Integer occ : occurrences) {
            sb.append(occ).append(" ");
        }
        if (sb.length() > 0) {
            System.out.println(sb.deleteCharAt(sb.length()-1));
        }
    }

    public static int find(int[] measures, int[] pattern, int start, int diff) {
        if (measures.length < pattern.length) {
            return -1;  // Длинный шаблон не может содержаться в короткой строке.
        }
        for (int pos = start; pos <= measures.length - pattern.length; pos++) {
            // Проверяем, не совпадёт ли шаблон, сдвинутый на позицию pos,
            //   с соответствующим участком строки.
            boolean match = true;
            for (int offset = 0; offset < pattern.length; offset++) {
                if (measures[pos + offset] != pattern[offset] + diff) {
                    // Одного несовпадения достаточно, чтобы не проверять
                    //   дальше текущее расположение шаблона.
                    match = false;
                    break;
                }
            }
            // Как только нашлось совпадение шаблона, возвращаем его.
            // Это первое вхождение шаблона в строку.
            if (match == true) {
                return pos;
            }
            // Если совпадение не нашлось, цикл перейдёт к проверке следующей позиции.
        }
        // Числом -1 часто маркируют, что подстрока не была найдена,
        //   поскольку в строке нет позиции -1.
        // В качестве альтернативы можно возвращать null.
        return -1;
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
