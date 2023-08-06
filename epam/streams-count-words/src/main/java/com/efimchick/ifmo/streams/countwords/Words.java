package com.efimchick.ifmo.streams.countwords;


import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        TreeMap<String, Integer> stat = new TreeMap<>();
        StringBuilder sb = new StringBuilder();

        lines.forEach((s) -> {
            String[] words = s.trim().replaceAll("[^а-яa-zA-ZА-Я]", " ").split("\\s+");
            Arrays.stream(words).filter(w -> w.length() > 3)
                    .forEach(word -> stat.put(word.toLowerCase(), stat.get(word.toLowerCase()) != null ? stat.get(word.toLowerCase()) + 1 : 1));
        });


        stat.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .filter(s -> s.getValue() > 9)
                .forEach((s) -> sb.append(s.getKey()).append(" - ").append(s.getValue()).append("\n"));

        return sb.toString().trim();
    }
}
