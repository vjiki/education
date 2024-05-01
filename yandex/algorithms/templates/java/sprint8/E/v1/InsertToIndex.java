import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InsertToIndex {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String row = reader.readLine();
            int numInserts = readInt(reader);

            StringBuilder sb = new StringBuilder();

            sb.append(row);
            int additionalLength = 0;
            for (int i = 0; i < numInserts; i++) {
                String[] line = reader.readLine().split(" ");
                String rowToInsert = line[0];
                int position = Integer.parseInt(line[1]);
                sb.insert(position == 0 ? 0 : position + additionalLength, rowToInsert);
                additionalLength += line[0].length();
            }
            System.out.println(sb);
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
