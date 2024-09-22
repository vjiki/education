import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {

    // Если ответ существует, верните список из двух элементов
    // Если нет - то верните пустой список
    // функция twosum(numbers, X):
    //    для i из [0..len(numbers)):
    //        для j из [i+1 .. len(numbers)):
    //            если numbers[i] + numbers[j] == X, то:
    //                вернуть numbers[i], numbers[j]
    //    # По условию задачи пара обязательно должна найтись.
    //    # Но предусмотрительность не помешает:
    //    # если пара не найдена - вернём None, None (или можно выкинуть exception).
    //    вернуть None, None
    private static List<Integer> twoSum(List<Integer> arr, int targetSum) {
        // Ваше решение
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if(arr.get(i) + arr.get(j) == targetSum) {
                    return List.of(arr.get(i), arr.get(j));
                }
            }
        }
        return Collections.emptyList();
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int targetSum = readInt(reader);
            List<Integer> result = twoSum(arr, targetSum);
            if (result.isEmpty()) {
                System.out.println("None");
            } else {
                System.out.println(result.get(0) + " " + result.get(1));
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return  Arrays.asList(reader.readLine().split(" "))
                        .stream()
                        .map(elem -> Integer.parseInt(elem))
                        .collect(Collectors.toList());
    }
    
}