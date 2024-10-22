import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class I {

    private static boolean isPowerOfFour(int n) {
        return (n | 0x1554) == 0x1554 || n == 1;
    }

    private static boolean isPowerOfFourV0(int n) {
        return n == 1 || n == 4 || n == 16 || n == 64 || n == 256 || n == 1024 || n == 4096;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            if (isPowerOfFour(n)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

}
