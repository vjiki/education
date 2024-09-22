import java.time.Instant;
import java.util.Scanner;

public class A {

    private static int getSumV2(int a, int b) {
        return a + b;
    }

    private static int getSum(int a, int b)
    {
        int keep = (a & b) << 1;
        int res = a^b;

        if (keep == 0)
            return res;

        return getSum(keep, res);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(getSum(a, b));
        scanner.close();
    }
}