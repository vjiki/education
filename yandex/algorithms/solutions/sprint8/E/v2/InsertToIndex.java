import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class InsertToIndex {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String row = reader.readLine();
            int numInserts = readInt(reader);

            Map<Integer, String> indexToWord = new TreeMap<>();
            for (int i = 0; i < numInserts; i++) {
                String[] line = reader.readLine().split(" ");
                String rowToInsert = line[0];
                int position = Integer.parseInt(line[1]);
                indexToWord.put(position, rowToInsert);
            }

            int additionalShift = 0;
            for (Map.Entry<Integer, String> entry : indexToWord.entrySet()) {
//                System.out.println(entry.getKey() + " " + entry.getValue());
                row = insert(row, entry.getKey() + additionalShift, entry.getValue());
                additionalShift += entry.getValue().length();
            }
            System.out.println(row);
        }
    }

    public static String insert(String string, int index, String substring) {
        int length = string.length();
        int shift = substring.length();
        if (index > length) {
            // index == length - край строки
            throw new IllegalArgumentException("Нет такой позиции");
        }
        string = String.format("%-" + (length + shift) + "s", string);
        if (length > 0) {
            // Если length == 0, делать сдвиг нет смысла.
            // Кроме того, не следует в вычислениях писать (length - 1),
            // не проверив, что индекс не ноль.
            // В некоторых языках длина представляется беззнаковым целым числом,
            // в таком случае (length - 1) будет равен не -1, а числу MAX_INT,
            // и цикл станет некорректным. Мы этого избегаем.
            for (int i = length - 1; i >= index; i--) {
                string = string.substring(0, i + shift) + string.charAt(i) + string.substring(i + shift + 1);
            }
        }
        for (int i = 0; i < shift; i++) {
            string = string.substring(0, index + i) + substring.charAt(i) + string.substring(index + i + 1);
        }
        return string;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
