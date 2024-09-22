import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class H {
    private static String sumOfBinaries(String a, String b) {
        if (a.isEmpty() || b.isEmpty()) {
            return "";
        }

        if (a.length() > b.length()) {
            StringBuilder sb = new StringBuilder();
            int length = a.length() - b.length();
            sb.append(b);
            for (int i = 0; i < length; i++) {
                sb.insert(0, "0");
            }
            return sumOfBinaries(a, sb.toString());
        } else if (a.length() < b.length()) {
            return sumOfBinaries(b, a);
        }

        StringBuilder sb = new StringBuilder();
        boolean nextReg = false;
        for (int i = a.length() - 1; i >= 0; i--) {
            if (a.charAt(i) == '1' && b.charAt(i) == '1') {
                if (nextReg) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                nextReg = true;
            } else if (a.charAt(i) == '1' || b.charAt(i) == '1') {
                if (nextReg) {
                    sb.append("0");
                } else {
                    sb.append("1");
                    nextReg = false;
                }
            } else {
                if (nextReg) {
                    sb.append("1");
                    nextReg = false;
                } else {
                    sb.append("0");
                }
            }
        }
        if (nextReg) {
            sb.append("1");
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();
            System.out.println(sumOfBinaries(a, b));
        }
    }
}
