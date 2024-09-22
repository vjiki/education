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

            StringBuilder sb = new StringBuilder();
            sb.append(row);
            int additionalShift = 0;
            for (Map.Entry<Integer, String> entry : indexToWord.entrySet()) {
                sb.insert(entry.getKey() + additionalShift, entry.getValue());
                additionalShift += entry.getValue().length();
            }
            System.out.println(sb);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
