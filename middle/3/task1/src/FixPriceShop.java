// Задание 3. счастливые часы
//
//	Создайте класс FixPriceShop - сеть магазинов, которая продает товары по одной цене.
//
//	В классе создайте поле items - массив товаров (товар - это просто название, тип String)
//	Во всех магазинах - одинаковая цена на все товары, например, все магазины продают товары по 49.
//	Во всех магазинах действует акции "счастливый час" - час (от 0 до 23), когда действует скидка 50%
//	В каждом магазине "счастливый час" разный. , т.е. какой-то магазин продает со скидкой в 11, а другой в 23.
//
//	- В классе FixPriceShop определите метод  int checkItemPrice(String item, int hour) - первый аргумент - это название товара, который покупатель собирается купить,
//	второй аргумент - время покупки.
//	 Метод должен возвращать цену.
//	 Если время покупки совпадает со счастливым часом - то должна быть учтена скидка 50%.
//	 Если указанный товар не обнаружен - то метод должен вернуть -1.
//
//	- В классе FixPriceShop определите метод String[] getItems() - который возвращает массив доступных товаров в магазине.
//	В возвращенном массиве не должно быть null элементов.
//
//	- В классе FixPriceShop определите метод void buyItem(String item, int hour) - который выводит на экран надпись "товар <> продан по цене <>".
//	Цена выводится с учетом времени. После этого, из массива товаров купленный товар должен быть удален.
//	Если такого товара нет - выведите на экран "товар <> не обнаружен"
//
//
//
//Создайте 3 магазина
//	Каждый магазин заполните случайными товарами. Лучше будет, если каждый магазин получит разный набор товаров.
//	Из каждого магазина выберите случайный товар (для этого придется получить сначала список всех товаров)
//	и найдите оптимальное время для его покупки (т.е. время, когда действует счастливый час) используя функцию checkItemPrice
//
//	Купите этот товар.
//	попробуйте купить этот же самый товар еще раз, посмотрите консоль и убедитесь, что товар не обнаружен (при условии, что одинаковых товаров не было)
//
//	Советы:
//	- для хранение единой цены используйте статическую переменную.
//	Значение можете задать константой или использовать класс Random() для заполнения случайным числом.
//	- Для заполнения магазина товарами используйте конструктор.
//	Можете передать список в магазин или использовать конструктор без параметров и генерировать список внутри конструктора.

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class FixPriceShop {

    private final static int fixPrice = getRandomInt(2, 100);
    private String[] items;

    private final int happyHour;

    public FixPriceShop(String[] items, int happyHour) {
        System.out.println("fixed Price is: " + fixPrice +", happyHour is " + happyHour + ", items: " + Arrays.toString(items));
        this.items = items;
        this.happyHour = happyHour;
    }

    public String[] getItems() {
        return items;
    }

    int checkItemPrice(String item, int hour) {
        if(Arrays.stream(items).noneMatch(it -> it.equals(item))) {
            return -1;
        }
        if (happyHour == hour) {
           return fixPrice/2;
        }
        return fixPrice;
    }

    void buyItem(String item, int hour) {
//        List<String> itemsList = new ArrayList<>(Arrays.asList(items));
//
//        itemsList.remove(item);
//
//        items = itemsList.toArray(new String[0]);

        for (int i = 0; i < items.length; i++) {
            if(items[i].equals(item)) {
                items = remove(items, i);
                System.out.printf( "товар <%s> продан по цене <%d> \n", item, hour==happyHour ? fixPrice/2 : fixPrice);
                System.out.println(Arrays.toString(items));
                return;
            }
        }

        System.out.println("товар не найден " + item);

    }

    private static String[] remove(String[] array, int index) {
        String[] result = new String[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, result.length - index);
        return result;
    }



    static String[] getRandomItems(int size) {
        String[] goods = new String[] {"a", "b", "c", "d", "e", "f", "g", "k", "l", "m"};

        String[] randItems = new String[size];

        for (int i = 0; i < randItems.length; i++) {
            randItems[i] = goods[getRandomInt(0,10)];
        }

        return randItems;
    }

    static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    static FixPriceShop getShop() {
        return new FixPriceShop(getRandomItems(getRandomInt(1,10)), getRandomInt(0,24));
    }
    public static void main(String[] args) {

        FixPriceShop[] fixPriceShops = new FixPriceShop[]{getShop(), getShop(), getShop()};

        for (FixPriceShop fixPriceShop : fixPriceShops) {
            String randItem = fixPriceShop.getItems()[getRandomInt(0, fixPriceShop.getItems().length)];
            for (int happyHour = 0; happyHour < 24; happyHour++) {
                if (fixPrice / 2 == fixPriceShop.checkItemPrice(randItem, happyHour)) {
                    System.out.println("happyHour is " + happyHour + " for " + randItem);
                    fixPriceShop.buyItem(randItem, happyHour);
                    fixPriceShop.buyItem(randItem, happyHour);
                }
            }

        }
    }
}