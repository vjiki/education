import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaxProfit {

    private static int getMaxProfit(int[] days) {
        if (days.length == 0) {
            return 0;
        }

        if (days.length == 1) {
            return days[0];
        }

        int profit = 0;
        for (int i = 1; i < days.length; i++) {
            if (days[i-1] < days[i]) {
                profit += days[i] - days[i-1];
            }
        }

        return profit;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfDays = readInt(reader);
            int[] days = readArray(reader);
            int chaosNumber = getMaxProfit(days);
            System.out.println(chaosNumber);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }
}
