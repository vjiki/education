package com.epam.lab;

import java.util.*;

import static java.util.Collections.*;

/**
 * Advice:
 * Start from implementing the method {@link SimpleTextStatisticsAnalyzer#getWords(String)}.
 * and then use this method when implementing other methods.
 * <p>
 * If necessary, you can create your own private methods in this class.
 */
public class SimpleTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    /**
     * Should count total length of all words (without spaces, punctuation marks, etc).
     * For example, for string "One, I - tWo!!" - method should return 7.
     *
     * @param text text
     */
    @Override
    public int countSumLengthOfWords(String text) {
        List<String> words = getWords(text);
        int countSumLength = 0;

        for (String word : words) {
            countSumLength += word.length();
        }

        return countSumLength;
    }

    /**
     * Should count number of words in text
     * For example, for "One, two, three, three - one, tWo, tWo!!" - method should return 7.
     *
     * @param text text
     */
    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    /**
     * Should count number of unique words in text (case sensitive).
     * For example, for "One, two, three, three - one, tWo, tWo!!" - method should return 5.
     * @param text text
     */
    @Override
    public int countNumberOfUniqueWords(String text) {
        return getUniqueWords(text).size();
    }

    /**
     * Should return list of words from the text.
     * Spaces, commas, periods, quotation marks and other punctuation marks are word separators.
     * For example, for "One, two, three, three - one, tWo, tWo!!" - method should return the list:
     * {"One", "two", "three", "three", "one", "tWo", "tWo"}
     *
     * @param text text
     */
    @Override
    public List<String> getWords(String text) {
        return Arrays.asList(text.trim().replaceAll("[^а-яa-zA-ZА-Я]", " ").split("\\s+"));
    }

    /**
     *
     * Should return list of unique words from the text.
     * Spaces, commas, periods, quotation marks and other punctuation marks are word separators.
     * For example, for "One, two, three, three - one, tWo, tWo!!" - method should return the list:
     * {"One", "two", "three", "one", "tWo"}
     *
     * @param text text
     */
    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<>(getWords(text));
    }

    /**
     * Should count the number of words repetitions.
     * For example, for "One, two, three, three - one, tWo, tWo!!" - method should return:
     * {"One" : 1, "two" : 1, "three" : 2, "one" : 1, "tWo" : 2}
     *
     * @param text text
     */
    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        Map<String,Integer> wordsRepetitionsMap = new HashMap<>();
        List<String> words = getWords(text);

        for (String word : words) {
            if (wordsRepetitionsMap.get(word) == null) {
                wordsRepetitionsMap.put(word, 1);
            } else {
                int count = wordsRepetitionsMap.get(word);
                wordsRepetitionsMap.replace(word, count+1);
            }
        }
        return wordsRepetitionsMap;
    }

    /**
     * Should return ordered (by words' length) list of words from the text depending on direction parameter.
     * For example, for "Hello, Hi, mother, father - good, cat, c!!" method should return:
     * ASC : {"c", "Hi", "cat", "good", "Hello", "father", "mother"}
     * DESC : {"mother", "father", "Hello", "good", "cat", "Hi", "c"}
     *
     * @param text text
     */
    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> words = getWords(text);

        if (direction == Direction.ASC) {
            words.sort(Comparator.comparingInt(String::length));
        } else {
            words.sort((a, b) -> Integer.compare(b.length(), a.length()));
        }

        return words;
    }
}
