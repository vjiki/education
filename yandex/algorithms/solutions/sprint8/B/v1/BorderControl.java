import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BorderControl {

    private final static String FAIL = "FAIL";
    private final static String OK = "OK";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String idName = reader.readLine();
            String dbName = reader.readLine();

            if (Math.abs(idName.length() - dbName.length()) > 1) {
                System.out.println(FAIL);
                return;
            }

            if (idName.length() == dbName.length()) {
                int cnt = 0;
                for (int i = 0; i < idName.length(); i++) {
                    if (cnt > 1) {
                        System.out.println(FAIL);
                        return;
                    }
                    if (idName.charAt(i) != dbName.charAt(i)) {
                        cnt++;
                    }
                }
                System.out.println(OK);
            } else
            if (idName.length() > dbName.length()) {
                isForeignerCanPass(dbName.toCharArray(), idName.toCharArray());
            } else {
                isForeignerCanPass(idName.toCharArray(), dbName.toCharArray());
            }
        }
    }

    static void isForeignerCanPass(char[] minName, char[] maxName) {
        int k = 0;
        int add = 0;
        for (int i = 0; i < minName.length; i++) {
            if(add > 1) {
                System.out.println(FAIL);
                return;
            }
            if (minName[k] != maxName[i]) {
                add++;
            } else {
                k++;
            }
        }
        System.out.println(OK);
    }
}
