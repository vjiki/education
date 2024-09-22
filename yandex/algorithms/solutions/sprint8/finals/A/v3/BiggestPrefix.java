import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BiggestPrefix {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
//            String[] zippedRows = new String[n];
//
//            String unzippedRow = unzip(reader.readLine());
//            String unzippedRow2 = unzip(reader.readLine());
//            String unzippedRow3 = unzip(reader.readLine());
//
//            System.out.println(unzippedRow);
//            System.out.println(unzippedRow2);
//            System.out.println(unzippedRow3);
//
//            for (int i = 0; i < n; i++) {
//                zippedRows[i] = reader.readLine();
//            }


            String prefix = unzip(reader.readLine());
            for (int i = 0; i < n - 1; i++) {
                String string = unzip(reader.readLine());
                while (!prefix.isBlank() && !string.startsWith(prefix)) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
            }
            System.out.println(prefix);


            StringBuilder sb = new StringBuilder();

            System.out.println(sb);
        }
    }

    // 3
    //2[a]2[ab]
    //3[a]2[r2[t]]
    //a2[aa3[b]]
    static String unzip(String zippedRow) {
        StringBuilder sb = new StringBuilder();

        Stack<Character> characterStack = new Stack<>();
        Stack<Integer> multiplyStack = new Stack<>();
        Stack<Integer> lengthStack = new Stack<>();
        int repeatTime = 1;
        int numberBrackets = 0;
        int length = 0;
        for(int i = 0; i < zippedRow.length(); i++) {
            char c = zippedRow.charAt(i);
            if(Character.isDigit(c)) {
                multiplyStack.push(Character.getNumericValue(c));
            } else if (c == '[') {
                if (length != 0) {
                    lengthStack.push(length);
                }
                length = 0;
            } else if (c == ']') {
                if (length != 0) {
                    lengthStack.push(length);
                    length = 0;
                }
                if (lengthStack.size() == 1) {
                    int currentLength = lengthStack.pop();
                    String multiplyString = sb.substring(sb.length() - currentLength, sb.length());
                    int m = multiplyStack.pop();
                    sb.append(String.valueOf(multiplyString).repeat(Math.max(0, m - 1)));
                } else {
                    int previousLength = lengthStack.pop();
                    String multiplyString = sb.substring(sb.length() - previousLength, sb.length());
                    int m = multiplyStack.pop();
                    sb.append(String.valueOf(multiplyString).repeat(Math.max(0, m - 1)));
                    int currentLength = lengthStack.pop() + previousLength * m;
                    lengthStack.push(currentLength);

//                    int fullLength = lengthStack.pop() + lengthStack.pop();
//                    String multiplyString = sb.substring(sb.length() - fullLength, sb.length());
//                    int m = multiplyStack.pop();
//                    sb.append(String.valueOf(multiplyString).repeat(Math.max(0, m - 1)));
//                    lengthStack.push(multiplyString.length()*m);
                }
            } else {
                sb.append(c);
                length++;
                characterStack.push(c);
            }
        }
        return sb.toString();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
