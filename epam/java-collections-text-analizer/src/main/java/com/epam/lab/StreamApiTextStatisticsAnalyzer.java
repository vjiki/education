package com.epam.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.*;

/**
 * You should use Stream Api from Java 8 when implementing methods in this class
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().mapToInt(String::length).reduce(0, Integer::sum);
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.trim().replaceAll("[^а-яa-zA-ZА-Я]", " ").split("\\s+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return Arrays.stream(text.trim().replaceAll("[^а-яa-zA-ZА-Я]", " ").split("\\s+")).collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
            return getWords(text).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        return getWords(text).stream().sorted((a, b) -> direction == Direction.ASC ? Integer.compare(a.length(), b.length()) : Integer.compare(b.length(), a.length())).collect(Collectors.toList());
    }
}
