package org.leetcode;

public class MergeStrings {

    public static String mergeAlternately(String word1, String word2) {
        int minLength = Math.min(word1.length(), word2.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            sb.append(word1.charAt(i)).append(word2.charAt(i));
        }
        if (word1.length() <= word2.length()) {
            sb.append(word2, word1.length(), word2.length());
        } else {
            sb.append(word1, word2.length(), word1.length());
        }
        return sb.toString();
    }

    public static String mergeAlternatelyV2(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int maxLength = word1.length() + word2.length();
        for (int i = 0; i < maxLength; i++) {
            if (i < word1.length()) {
                sb.append(word1.charAt(i));
            }
            if (i < word2.length()) {
                sb.append(word2.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String mergeAlternatelyV3(String word1, String word2) {
        int maxLength = word1.length() + word2.length();
        byte[] byteArray = new byte[maxLength];
        byte[] word1Array = word1.getBytes();
        byte[] word2Array = word2.getBytes();
        int f = 0;
        for (int i = 0; i < maxLength; i++) {
            if (i < word1.length()) {
                byteArray[f] = word1Array[i];
                f++;
            }

            if (i < word2.length()) {
                byteArray[f] = word2Array[i];
                f++;
            }
        }
        return new String(byteArray);
    }

    public static String mergeAlternatelyV4(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int n1 = word1.length(), n2 = word2.length();
        int idx = 0;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (idx % 2 == 0)
                sb.append(word1.charAt(i++));
            else
                sb.append(word2.charAt(j++));
            idx++;
        }

        if (i < n1) sb.append(word1, i, n1);
        if (j < n2) sb.append(word2, j, n2);

        return sb.toString();
    }
}
