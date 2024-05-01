import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Levenshtein {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            char[] firstRow = reader.readLine().toCharArray();

            char[] secondRow = reader.readLine().toCharArray();
            if (firstRow.length >= secondRow.length) {
                getMaxCommonPart(firstRow, secondRow);
            } else {
                getMaxCommonPart(secondRow, firstRow);
            }
        }
    }

    private static void getMaxCommonPart(char[] firstRow,
                                         char[] secondRow) {
        HashMap<Character, List<Integer>> firstRowMap = new HashMap<>();
        HashMap<Character, List<Integer>> secondRowMap = new HashMap<>();
        for (int i = 0; i < firstRow.length; i++) {
            List<Integer> indexes;
            if (firstRowMap.containsKey(firstRow[i])) {
                indexes = firstRowMap.get(firstRow[i]);
            } else {
                indexes = new ArrayList<>();
            }
            indexes.add(i);
            firstRowMap.put(firstRow[i], indexes);
        }
        for (int i = 0; i < secondRow.length; i++) {
            List<Integer> indexes;
            if (secondRowMap.containsKey(secondRow[i])) {
                indexes = secondRowMap.get(secondRow[i]);
            } else {
                indexes = new ArrayList<>();
            }
            indexes.add(i);
            secondRowMap.put(secondRow[i], indexes);
        }

        int count = 0;
        int i = 0;
        while ( i < secondRow.length) {
            if (secondRowMap.containsKey(firstRow[i])) {
                if (!secondRowMap.get(firstRow[i]).contains(i)) {
                    List<Integer> indexesSecond = secondRowMap.get(secondRow[i]);
                    List<Integer> indexesFound = secondRowMap.get(firstRow[i]);
                    int replaceIndex = -1;
                    for (int index : indexesFound) {
                        if (index > i) {
                            replaceIndex = index;
                        }
                    }
                    if (replaceIndex != -1) {
                        indexesFound.remove(Integer.valueOf(replaceIndex));
                        secondRowMap.put(firstRow[i], indexesFound);
                        indexesSecond.add(i);
                        secondRowMap.put(secondRow[i], indexesSecond);
                    }
                    count++;
                }
            } else {
                count++;
            }
            i++;
        }
        while (i < firstRow.length) {
            count++;
            i++;
        }

        System.out.println(count);
    }
}
