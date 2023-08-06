package com.epam.lab.service;

import java.util.Locale;

public class SimpleTextService implements TextService {

    /**
     * Should remove part from string
     *
     * For example, for base string "Hello, hello, hello, how low?" and removed string ", he"
     * method should return "Hellollollo, how low?"
     *
     * @param base - base string
     * @param remove - part of the string that should be removed
     */
    @Override
    public String removeString(String base, String remove) {
        return base.replace(remove,"");
    }

    /**
     * Returns {@code true} if string ends with question mark, otherwise - false
     *
     * For example, for "Hello, hello, hello, how low?" method returns true
     * For "Hello, hello, hello!" method returns false
     */
    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?");
    }

    /**
     * Should concatenate strings
     *
     * For {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * method should return "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        StringBuilder sb = new StringBuilder();
        for (String el : elements) {
            sb.append(el);
        }
        return sb.toString();
    }

    /**
     * Should return text in jumped-case. Returned text should start from lowercase letter.
     *
     * For "Load Up On Guns And Bring Your Friends"
     * method should return "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (char c : text.toCharArray()) {
            if ((i % 2) == 0) {
                sb.append(String.valueOf(c).toLowerCase());
            } else {
                sb.append(String.valueOf(c).toUpperCase());
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * Check if string is a palindrome.
     *
     * A palindrome is a word, number, phrase, or other sequence of characters
     * which reads the same backward as forward, such as madam, racecar.
     */
    @Override
    public boolean isPalindrome(String string) {
//        string = string.trim();
//        StringBuffer sbr = new StringBuffer(string);
//        sbr.reverse();
//        //string.
////        System.out.println(string);
////        int i = 0;
////        for(char c : string.toCharArray()) {
////            if (c != string.charAt(string.length()-i-1)) {
////                return false;
////            }
////            i++;
////        }
////       return true;
//
//        return !string.contentEquals(sbr);
//
        StringBuilder reverse = new StringBuilder();
        String clean = string.replaceAll("\\s+", "").toLowerCase();
        char[] plain = clean.toCharArray();
        for (int i = plain.length - 1; i >= 0; i--) {
            reverse.append(plain[i]);
        }
        return (reverse.toString()).equals(clean);
    }
}
