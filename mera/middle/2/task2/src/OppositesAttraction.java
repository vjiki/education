//
//
//Влечение противоположностей
//-Создайте массив на 10 элементов
//-Заполните его случайными числами из всего диапазона int'a
//-Выведите все элементы массива на экран
//
//Теперь Проверьте все  "соседние" элементы массива (начинайте с 0 индекса и до самого конца).
//Если элементы разных знаков - то выведите на экран " Числа с противоположными знаками обнаружены: <Число1> <Число2>"
//Если одно из чисел 0 - то ничего не делайте.
//Выведите получившийся массив на экран.
//
//Например,
//массив [1,2,3,4,5,-7,2]
//
//Числа с противоположными знаками обнаружены: 5 -7
//Числа с противоположными знаками обнаружены: -7 2

import java.util.Arrays;
import java.util.Random;

public class OppositesAttraction {

    public static void main(String[] args) {
        int[] arrayOfInts = new Random().ints(10, Integer.MIN_VALUE, Integer.MAX_VALUE).toArray();

        System.out.println(Arrays.toString(arrayOfInts));

        for (int i = 0; i < arrayOfInts.length-1; i++) {
            if(arrayOfInts[i] == 0 || arrayOfInts[i+1] == 0) {
                continue;
            }

            if ( Integer.signum(arrayOfInts[i]) != Integer.signum(arrayOfInts[i+1])) {
                System.out.printf(" Числа с противоположными знаками обнаружены: %d %d \n", arrayOfInts[i], arrayOfInts[i+1]);
            }
        }
    }
}