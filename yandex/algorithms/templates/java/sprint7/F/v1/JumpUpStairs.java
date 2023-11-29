import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JumpUpStairs {

    // func countWays(n, k int, cache *[]int) int {
    //	if n < 1 {
    //		return 0
    //	} else if n == 1 {
    //		return 1
    //	} else {
    //		if (*cache)[n] != 0 {
    //			return (*cache)[n] % module
    //		} else {
    //			for i := 1; i <= k; i++ {
    //				(*cache)[n] += countWays(n-i, k, cache) % module
    //			}
    //			return (*cache)[n] % module
    //		}
    //	}
    //}

    public static int codeSize(int n, int k, List<Integer> dp) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            if (dp.get(n) != 0) {
                return dp.get(n) % pow(9);
            } else {
                for (int i = 1; i <= k; i++) {
                        int prev = dp.get(n);
                        dp.add(n,  prev + codeSize(n-i, k, dp) % pow(9));
                        cache[n] += countWays(n - i, k, cache) % module;
                }
                return dp.get(n) % pow(9);
            }
        }
    }

    private static int pow(int k) {
        int decimal = 1;
        for (int i = 0; i < k; i++) {
            decimal *= 10;
        }
        return decimal + 7;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String cmd = reader.readLine();
            String[] values = cmd.split(" ");
            int n = Integer.parseInt(values[0]);
            int k = Integer.parseInt(values[1]);
            List<Integer> dp = new ArrayList<>();
            for (int i = 0; i < n+1; i++) {
                dp.add(i, 0);
            }
            System.out.println(codeSize(n, k, dp));
        }
    }
}