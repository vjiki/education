import java.util.Scanner;

public class B {

    private static final int MIN_RANGE = -1000000000;
    private static final int MAX_RANGE = 1000000000;

    public static boolean betweenInclusive(int x, int min, int max)
    {
        return x>=min && x<=max;
    }

    private static boolean checkParity(int a, int b, int c) {
        if (betweenInclusive(a, MIN_RANGE, MAX_RANGE) &&
            betweenInclusive(b, MIN_RANGE, MAX_RANGE) &&
            betweenInclusive(c, MIN_RANGE, MAX_RANGE)) {
            boolean aParity = a % 2 == 0;
            boolean bParity = b % 2 == 0;
            boolean cParity = c % 2 == 0;

            return aParity == bParity && bParity == cParity;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (checkParity(a, b, c)) {
            System.out.println("WIN");
        } else {
            System.out.println("FAIL");
        }
        scanner.close();
    }
}