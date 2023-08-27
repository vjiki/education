import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class G {

    private static String getBinaryNumber(int n) {
        if ( n > 10000 || n < 0) {
            return "";
        }

        int bitCheck = 1;
        boolean skip = true;
        StringBuilder sb = new StringBuilder();

        for (int i = 13; i >=0; i--) {
            bitCheck = 1 << i;
            if ((n & bitCheck) > 0) {
                sb.append("1");
                skip = false;
            } else {
                if(!skip) {
                    sb.append("0");
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            System.out.println(getBinaryNumber(n));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
