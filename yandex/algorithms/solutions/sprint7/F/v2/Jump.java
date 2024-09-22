import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jump {

    static final long MOD_VALUE = 1000000007;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String cmd = reader.readLine();
            String[] values = cmd.split(" ");
            int n = Integer.parseInt(values[0]);
            int k = Integer.parseInt(values[1]);
            long[] cache = new long[n + 1];

            System.out.println(countWays(n, k, cache));
        }
    }


    public static long countWays(int n, int k, long[] cache) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            if (cache[n] == 0) {
                for (int i = 1; i <= k; i++) {
                    cache[n] += countWays(n - i, k, cache) % MOD_VALUE;
                }
            }
            return cache[n] % MOD_VALUE;
        }
    }
}

