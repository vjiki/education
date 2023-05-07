//2. Написать программу, в которой есть две переменные типа Integer со значениями. Значения можно указать в коде программы или ввести с клавиатуры или взять из аргументов.
//3. Если первое число больше второго – написать на экран « Число %s больше %s”
//Если первое число меньше второго – написать на экран «Число %s меньше %s”
//4. В любом случае, вывести на экран сумму чисел.

import java.util.Scanner;

public class IntegerTask {

    private static Integer var1 = 2;
    private static Integer var2 = 1;

    public static void main(String[] args) {

        System.out.printf("Default numbers: first: %s second: %s \n", var1, var2);

        if (args.length == 2) {
            var1 = Integer.valueOf(args[0]);
            var2 = Integer.valueOf(args[1]);
            System.out.printf("Numbers from Command Line Arguments: first: %s second: %s \n", var1, var2);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first number :");
        if (sc.hasNextInt()) {
            var1 = sc.nextInt();
        } else {
            System.out.println("Skipping first number from CLI");
        }
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter second number :");
        if (sc2.hasNextInt()) {
            var2 = sc2.nextInt();
        } else {
            System.out.println("Skipping second number from CLI");
        }

        if (var1.compareTo(var2) > 0) {
            System.out.printf("Число %s больше %s \n", var1, var2);
        } else if ((var1.compareTo(var2) < 0)) {
            System.out.printf("Число %s меньше %s \n", var1, var2);
        }

        System.out.println("sum is " + Integer.sum(var1, var2));

    }
}