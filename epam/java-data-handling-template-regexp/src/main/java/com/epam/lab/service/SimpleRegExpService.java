package com.epam.lab.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleRegExpService implements RegExpService {

    /**
     * should read the file sensitive_data.txt (from resources directory) and mask confidential information.
     * Account number should only contain the first 4 and the last 4 numbers (1234 **** **** 5678).
     * Method should contain regular expression for account number's search.
     *
     * @return handled text
     */
    @Override
    public String maskSensitiveData() {
        final String REGEX = "(\\d{4}\\s)(\\d{4}\\s)(\\d{4})(\\s\\d{4})";

        String returnString = null;

        try {
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/sensitive_data.txt")), StandardCharsets.UTF_8);
            returnString = content.replaceAll(REGEX, "$1**** ****$4");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnString;
    }

    /**
     * should read the file sensitive_data.txt (from resources directory) and replace placeholders ${payment_amount} and ${balance}
     * with input arguments.
     * Method should contain regular expression for placeholders' search
     *
     * @return handled text
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String returnString = null;

        try {
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/sensitive_data.txt")), StandardCharsets.UTF_8);
            returnString = content.replaceAll("\\$\\{payment_amount\\}", String.format("%.0f", paymentAmount))
            .replaceAll("\\$\\{balance\\}", String.format("%.0f", balance));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return returnString;    }
}
