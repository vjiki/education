import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReplaceFunction {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String row = reader.readLine();
            String s = reader.readLine();
            String t = reader.readLine();
            int offset = t.length() - s.length();

            StringBuilder sb = new StringBuilder();
            sb.append(row);
            List<Integer> indexes = search(s, row);
            int off1 = 0;
            for (int inx : indexes) {
                int newInx = inx + off1;
                sb.delete(newInx, newInx+s.length());
                sb.insert(newInx, t);
                off1 += offset;
            }
            System.out.println(sb);
        }
    }

    public static List<Integer> search(String p, String text) {
        // Функция возвращает все позиции вхождения шаблона в тексте.
        List<Integer> result = new ArrayList<>();
        String s = p + "#" + text;
        int[] prefix = new int[p.length()];  // Массив длины |p|.
        int prefix_prev = 0;
        for (int i = 1; i < s.length(); i++) {
            int k = prefix_prev;
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = prefix[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            // Запоминаем только первые |p| значений π-функции.
            if (i < p.length()) {
                prefix[i] = k;
            }
            // Запоминаем последнее значение π-функции.
            prefix_prev = k;
            // Если значение π-функции равно длине шаблона, то вхождение найдено.
            if (k == p.length()) {
                // i - это позиция конца вхождения шаблона.
                // Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
                //  - чтобы «переместиться» на начало найденного шаблона,
                //  - чтобы не учитывать добавленное "pattern#".
                result.add(i - 2 * p.length());
            }
        }
        return result;
    }
}
