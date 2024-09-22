import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// функция скользящее_среднее(timeseries, K):
//    result = []  # Пустой массив.
//    для begin_index из [0 .. len(timeseries) - K]:
//        end_index = begin_index + K
//        # Просматриваем окно шириной K.
//        current_sum = 0
//        для каждого v из timeseries[begin_index .. end_index):
//            current_sum += v
//        current_avg = current_sum / K
//        result.добавить(current_avg)
//    вернуть result

public class C {

    private static List<Double> movingAverageV0(int n, List<Integer> arr, int windowSize) {
        // ваше решение
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < n - windowSize + 1; i++) {
            int currentSum = 0;
            for (int j = i; j < i + windowSize; j++) {
                currentSum += arr.get(j);
            }
            result.add((double) currentSum/windowSize);
        }
        return result;
    }

    // функция скользящее_среднее(timeseries, K):
    //    result = []  # Пустой массив.
    //    # Первый раз вычисляем значение честно и сохраняем результат.
    //    current_sum = сумма элементов timeseries[0..K)
    //    result.добавить(current_sum / K)
    //    для i из [0 .. len(timeseries) - K):
    //        current_sum -= timeseries[i]
    //        current_sum += timeseries[i+K]
    //        current_avg = current_sum / K
    //        result.добавить(current_avg)
    //    вернуть result

    private static List<Double> movingAverage(int n, List<Integer> arr, int windowSize) {
        // ваше решение
        List<Double> result = new ArrayList<>();

        int currentSum = 0;
        for (int i = 0; i < windowSize; i++) {
            currentSum += arr.get(i);
        }
        result.add((double) currentSum/windowSize);

        for (int i = 0; i < n - windowSize; i++) {
            currentSum -= arr.get(i);
            currentSum += arr.get(i + windowSize);
            result.add((double) currentSum/windowSize);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
                 int n = readInt(reader);
                 List<Integer> arr = readList(reader);
                 int windowSize = readInt(reader);
                 List<Double> result = movingAverage(n, arr, windowSize);
                 for (double elem : result) {
                     writer.write(elem + " ");
                 }
             }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}