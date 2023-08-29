import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class J {

    private static List<Integer> factorize(int n) {
        if (n < 2 || n > 1000000000) {
            return Collections.emptyList();
        }

        List<Integer> factorization = new ArrayList<>();
        for (int i = 2; i * i <= n; i ++) {
            while (n % i == 0) {
                factorization.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            factorization.add(n);
        }

        factorization.sort(Integer::compareTo);

        return factorization;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> factorization = factorize(n);
            for (int elem : factorization) {
                writer.write(elem + " ");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
