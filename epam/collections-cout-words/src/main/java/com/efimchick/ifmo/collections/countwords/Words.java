package com.efimchick.ifmo.collections.countwords;


import java.util.*;

public class Words {


    public static <K, V extends Comparable<V>> TreeMap<K, V> sortMapByValues(final TreeMap<K, V> map) {
        Comparator<K> valueComparator =  new Comparator<K>() {
            public int compare(K k1, K k2) {
                final V v1 = map.get(k1);
                final V v2 = map.get(k2);

                /* Not sure how to handle nulls ... */
                if (v1 == null) {
                    return (v2 == null) ? 0 : 1;
                }

                int compare = v2.compareTo(v1);

                if (compare != 0)
                {
                    return compare;
                }
                else
                {
                    return k1.toString().compareTo(k2.toString());
                }
            }
        };
        TreeMap<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    public String countWords(List<String> lines) {
        TreeMap<String, Integer> stat = new TreeMap<>();
        StringBuilder sb = new StringBuilder();

        for (String s: lines) {
            String[] words = s.trim().replaceAll("[^а-яa-zA-ZА-Я]", " ").split("\\s+");
            for(String word: words) {
                int count = stat.get(word.toLowerCase()) != null ? stat.get(word.toLowerCase()) + 1 : 1;
                if(word.length() > 3 ) {
                    stat.put(word.toLowerCase(), count);
                }
            }
        }

        TreeMap<String, Integer> sortedStat = sortMapByValues(stat);

        for (String s: sortedStat.keySet()) {
            if (sortedStat.get(s) > 9) {
                        sb.append(s).append(" - ").append(sortedStat.get(s)).append("\n");
            }
        }

        return sb.toString().trim();
    }
}
