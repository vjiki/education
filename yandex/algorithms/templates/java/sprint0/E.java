import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    # Сортируем исходный массив стандартной функцией.
//    sort(numbers)
//
//    left = 0
//    right = len(numbers) - 1
//    пока left < right:
//        current_sum = numbers[left] + numbers[right]
//        если current_sum == X, то:
//            вернуть numbers[left], numbers[right]
//        если current_sum < X, то:
//            left += 1
//        иначе:
//            right -= 1
//    # Если ничего не нашлось в цикле, значит, нужной пары элементов в массиве нет.
//    вернуть None, None

public class E {

    // Если ответ существует, верните список из двух элементов
    // Если нет - то верните пустой список

    private static List<Integer> twoSumV1(List<Integer> arr, int targetSum) {
        // Ваше решение
        // функция twosum_with_sort(numbers, X):
        arr.sort(Integer::compareTo);
        int left = 0;
        int right = arr.size() - 1;
        while (left < right) {
            int curerentSum = arr.get(left) + arr.get(right);
            if (curerentSum == targetSum) {
                return List.of(arr.get(left), arr.get(right));
            } else if (curerentSum < targetSum) {
                left += 1;
            } else {
                right -= 1;
            }
        }

        return Collections.emptyList();
    }

    // функция twosum_extra_ds(numbers, X):
    //    # Создаём вспомогательную структуру данных с быстрым поиском элемента.
    //    previous = set()
    //
    //    для каждого A из numbers:
    //        Y = X - A
    //        если Y уже лежит в previous, то:
    //            вернуть A, Y
    //        иначе:
    //            добавить A в previous
    //
    //    # Если ничего не нашлось в цикле, значит, нужной пары элементов в массиве нет.
    //    вернуть None, None

    private static List<Integer> twoSum(List<Integer> arr, int targetSum) {
        // Ваше решение
        // функция twosum_with_sort(numbers, X):
        Set<Integer> integerSet = new HashSet<>();

        for (Integer a: arr) {
            int y = targetSum - a;
            if (integerSet.contains(y)) {
                return List.of(y, a);
            } else {
                integerSet.add(a);
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