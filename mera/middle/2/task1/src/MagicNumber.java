// Задание 2.
//
//Магия чисел.
//Создайте массив целых чисел на 100 элементов.
//Заполните их случайными числами от - 100 до 100.
//Создайте функцию boolean isMagicNumber(int number)
//Функция возвращает true, если число "магическое" - состоит из одинаковых цифр (например 22, или -33)
//Используя цикл for, проверьте каждое число в массиве, является ли оно "магическим".
//Если число "магическое" - выведите на экран надпись "Число <число> - магическое!"
//
//Подсказка: для получения цифр числа можно использовать деление на 10 и остаток от деления на 10 (операция %)
//
//
//

import java.util.Random;

public class MagicNumber {

    static boolean isMagicNumber(int number) {
        int division = number/10;
        int remainderOfDivision = number%10;

        return division != 0 && remainderOfDivision != 0 && number / 10 == number % 10;
    }

    public static void main(String[] args) {
        new Random().ints(100, -101, 101).filter(MagicNumber::isMagicNumber).peek(num -> System.out.printf("Число %d - магическое! \n", num)).toArray();
    }
}