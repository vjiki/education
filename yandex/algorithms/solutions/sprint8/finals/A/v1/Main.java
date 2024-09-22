import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(maxPrefix());
    }

    public static String unpack(String string) {
        int[] multiply = new int[string.length()];
        char[] symbol = new char[string.length()];
        char[] result = new char[string.length()*3];
        int multiplyIndex = 0;
        int symbolIndex = 0;
        int resultIndex = 0;
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c)) {
                multiply[multiplyIndex++] = Character.getNumericValue(c);
                continue;
            }
            if (c == '[') {
                symbol[symbolIndex++] = '[';
                continue;
            }
            if (c == ']') {
                if (symbolIndex == 1) {
                    String temp = new String(symbol, 0, symbolIndex);
                    for (int i = 0; i < multiply[multiplyIndex - 1]; i++) {
                        for (char ch : temp.toCharArray()) {
                            result[resultIndex++] = ch;
                        }
                    }
                    symbolIndex--;
                    multiplyIndex--;
                    continue;
                }
                StringBuilder previous = new StringBuilder();
                for (int i = 0; i < symbolIndex; i++) {
                    previous.append(symbol[i]);
                }
                for (int i = 0; i < multiply[multiplyIndex - 1]; i++) {
                    for (char ch : previous.toString().toCharArray()) {
                        if (ch != '[') {
                            result[resultIndex++] = ch;
                        }
                    }
                }
                symbolIndex--;
                multiplyIndex--;
                continue;
            }
            if (symbolIndex == 0) {
                result[resultIndex++] = c;
                continue;
            }
            symbol[symbolIndex++] = c;
        }
        return new String(result, 0, resultIndex);
    }

    public static String maxPrefix() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 0) {
            return "";
        }
        String prefix = unpack(scanner.next());
        for (int i = 0; i < n - 1; i++) {
            String string = unpack(scanner.next());
            while (!prefix.isEmpty() && !string.substring(0, prefix.length()-1).equals(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }
}

