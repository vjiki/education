import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bank {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int x = readInt(reader);
        int k = readInt(reader);
        int[] nominals = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        getMinCoins(x, k, nominals);
    }

    static void getMinCoins(int x, int k, int[] nominals) {
        int total = -1;
        int[] prev = new int[x + 1];
        int[] result = new int[x + 1];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < x + 1; j++) {
                result[j] = min(result[j], prev[j]);
                if (j - nominals[i] >= 0 && (result[j - nominals[i]] > 0 || j % nominals[i] == 0)) {
                    result[j] = min(result[j], result[j - nominals[i]] + 1);
                }
            }
            prev = result;
            result = new int[x + 1];
        }

        if (prev[x] > 0) {
            total = prev[x];
        }

        System.out.println(total);
    }

    public static int min(int a, int b) {
        if (a == 0 || b == 0) {
            if (a > b) {
                return a;
            }
        } else if (a < b) {
            return a;
        }
        return b;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}

