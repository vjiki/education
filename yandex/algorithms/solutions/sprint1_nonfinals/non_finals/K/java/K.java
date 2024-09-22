import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class K {

    static List<Integer> getSum(List<Integer> maxList, List<Integer> minList) {
        int indexK = minList.size() - 1;
        List<Integer> result = new ArrayList<>();

        int extraSum = 0;
        for (int i = maxList.size() - 1; i >= 0; i--) {
            if (indexK >= 0) {
                int sum = maxList.get(i) + minList.get(indexK) + extraSum;
                char[] sumChars = String.valueOf(sum).toCharArray();
                int lastChar = Integer.parseInt(String.valueOf(sumChars[0]));
                if (sumChars.length == 1) {
                    extraSum = 0;
                    result.add(0, lastChar);
                } else {
                    extraSum = lastChar;
                    result.add(0, Integer.parseInt(String.valueOf(sumChars[1])));
                }
                indexK--;
            } else {
                result.add(0, maxList.get(i) + extraSum);
                extraSum = 0;
            }
        }
        if (extraSum != 0) {
            result.add(0, extraSum);
        }

        return result;
    }

    private static List<Integer> getSum(List<Integer> numberList, int k) {

        char[] chars = String.valueOf(k).toCharArray();

        List<Integer> secondList = new ArrayList<>();
        for (char aChar : chars) {
            secondList.add(Integer.parseInt(String.valueOf(aChar)));
        }

        if (numberList.size() >= secondList.size()) {
            return getSum(numberList, secondList);
        } else {
            return getSum(secondList, numberList);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int numberLength = readInt(reader);
            List<Integer> numberList = readList(reader);
            int k = readInt(reader);
            List<Integer> sum = getSum(numberList, k);
            for (int elem : sum) {
                writer.write(elem + " ");
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
