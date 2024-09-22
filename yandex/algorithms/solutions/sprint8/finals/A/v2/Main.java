import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder s = new StringBuilder();
        Solution(new BufferedReader(new InputStreamReader(System.in)), s);
        System.out.println(s.toString());
    }

    public static void Solution(BufferedReader reader, StringBuilder s) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        String prefix = "";
        String current;
        for (int i = 0; i < n; i++) {
            current = unpack(reader.readLine().getBytes());
            if (i == 0) {
                prefix = current;
                continue;
            }
            prefix = current.substring(0, findIndex(prefix, current));
        }
        s.append(prefix);
    }

    public static String unpack(byte[] s) {
        Stack<byte[]> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        byte[] top, curr;
        for (int i = 0; i < s.length; i++) {
            if (Character.isSpaceChar((char) s[i])) {
                continue;
            }
            switch (s[i]) {
                case ']':
                    top = stack.pop();
                    curr = null;
                    while (!Character.isDigit((char) top[0])) {
                        if (curr != null) {
                            curr = appendBytes(top, curr);
                        }
                        top = stack.pop();
                    }
                    int num = Integer.parseInt(new String(top));
                    if (curr != null) {
                        stack.push(repeatBytes(curr, num));
                    }
                    break;
                case '[':
                    continue;
                default:
                    stack.push(new byte[]{s[i]});
            }
        }
        while (!stack.isEmpty()) {
            builder.append(new String(stack.pop()));
        }
        return builder.toString();
    }

    public static int findIndex(String a, String b) {
        int max = Math.max(a.length(), b.length());
        int i = 0;
        for (; i < max; i++) {
            if (i >= a.length() || i >= b.length() || a.charAt(i) != b.charAt(i)) {
                break;
            }
        }
        return i;
    }

    public static byte[] appendBytes(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static byte[] repeatBytes(byte[] s, int num) {
        byte[] result = new byte[s.length * num];
        for (int i = 0; i < num; i++) {
            System.arraycopy(s, 0, result, i * s.length, s.length);
        }
        return result;
    }
}