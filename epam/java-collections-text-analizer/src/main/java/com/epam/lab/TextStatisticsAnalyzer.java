package com.epam.lab;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TextStatisticsAnalyzer {

    int countSumLengthOfWords(String text);

    int countNumberOfWords(String text);

    int countNumberOfUniqueWords(String text);

    List<String> getWords(String text);

    Set<String> getUniqueWords(String text);

    Map<String, Integer> countNumberOfWordsRepetitions(String text);

    List<String> sortWordsByLength(String text, Direction direction);
}
