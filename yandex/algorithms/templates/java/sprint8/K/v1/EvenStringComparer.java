import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EvenStringComparer {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String firstRow = getEvenRow(reader.readLine());
            String secondRow = getEvenRow(reader.readLine());

            int cmp = firstRow.compareTo(secondRow);
            if (cmp < 0) {
                System.out.println(-1);
            } else if (cmp > 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    static String getEvenRow(String row) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.length(); i++) {
            if ((int) row.charAt(i) % 2 == 0) {
                sb.append(row.charAt(i));
            }
        }
        return sb.toString();
    }

}
