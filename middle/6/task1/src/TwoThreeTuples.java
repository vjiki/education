// Задание 1. Пара-Тройка кортежей
//
//Создайте два класса для хранения кортежей - упорядоченного списка элементов определенных типов
//Pair - содержит пару таких элементов.
//Triple - содержит тройку таких элементов.
//
//у каждого класса создайте методы, которые возвращают элементы на определенных позициях.
// - getFirst
// - getSecond
// - getThird (только для Triple)
//
//У каждого класса создайте конструктор со 2(Pair) или тремя (Triple) параметрами нужного типа.
//
//Для каждого класса определите методы equals и hashCode.
//
//Пример использования
//Pair<String, Integer> lastNameToAge = new Pair<> ("Пупкин", 18);
//String lastName = lastNameToAge.geFirst();
//Integer age = lastNameToAge.getSecond();
//
//Pair<String, List<String>> lastNameToPhoneNumbers = new Pair<>("Пупкин", Arrays.asList("+7 831 2112233", "+7 920 000 22 22"))
//String lastName = lastNameToPhoneNumbers.geFirst();
//List<String> phoneNumbers = lastNameToPhoneNumbers.getSecond();
//
//Аналогично  с Triple, только параметра типа и параметров констркторов должно быть 3.
//

//
//Задание А. Работа с парами:
//Создайте класс Animal с полями имя(String) и тип животного (тоже String)
//Создайте список, состоящий из нескольких пар: животное и любимое блюдо(String) этого животного.
//
//
//Создайте метод feedAnimals(List<Pair<Animal,String>>);
//ЛОгика работы этого метода такая:
//Для каждого животного выводится надпись:
//"Животное %ИМЯ с радостью съедает %БЛЮДО
//
//+ в этом методе сгенерируйте случайное число от 0 до размера списка с животными.
//Это - номер животного, которое сегодня получит двойною порцию любимой еды.
//Для такого животного выведите надпись
//"Счастливое животное %имя получает двойную порцию %БЛЮДО"
//
//Замечания: вы можете справедливо заметить, что любимое блюдо вполне можно было объявить полем класса.
//Но иногда удобнее дополнительное поле вынести за рамки класса, например:
//- Когда  у вас есть библиотечный класс и вы не можете его менять.
//- Когда поле нужно использовать один раз, чтобы не засорять класс доп. полями.
//
//
//Задание Б. Списки, списки...
//
//Сейчас будем выводить разные списки и как-то их обрабатывать:
//
//Список с бензоколонками и ценами на бензин.
//Цены хранятся в списке троек:
//- Адрес бензоколонки - строка
//- Тип бензина - enum, возможные значения: ДТ, АИ-92, АИ-95, АИ-98
//- Цена - double
//
//
//Создайте список List<Triple<String,GasolineType,Double>> и заполните его данными о нескольких бензоколоноках.
//
//Создайте метод findBestPrice(List<Triple<String,GasolineType,Double>> list,GasolineType type ) который выводит на экран
//адрес бензоколонки с самой маленькой ценой на переданный тип бензина
//
//Список приложений на телефон:
//Приложения хранятся в списке троек:
//- Название приложения - строка
//- Количество оценок - double
//- Средний рейтинг - double от 1 до 5.
//
//Создайте список List<Triple<String,Double,Double>>
//создайте функции, которые на вход принимают этот список и выводят:
//- Все приложения, отсортированные по количеству оценок
//- Приложение с самым лучшим рейтингом
//- Приложение с самым худшим рейтингом

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class TwoThreeTuples {

    static void feedAnimals(List<Pair<Animal, String>> listAnimalFood) {
        int rndIndex = ThreadLocalRandom.current().nextInt(0, listAnimalFood.size());
        for (int i = 0; i < listAnimalFood.size(); i++) {
            if (i == rndIndex) {
                System.out.printf("Счастливое животное %s получает двойную порцию %s \n", listAnimalFood.get(i).getFirst().getName(), listAnimalFood.get(i).getSecond());

            } else {
                System.out.printf("Животное %s с радостью съедает %s \n", listAnimalFood.get(i).getFirst().getName(), listAnimalFood.get(i).getSecond());
            }
        }
    }

    static void findBestPrice(List<Triple<String, GasolineType, Double>> list, GasolineType type) {
        Optional<Triple<String, GasolineType, Double>> bestPriceGasolineStation =  list.stream().filter(e -> e.getSecond().equals(type)).min(Comparator.comparing(Triple::getThird));
//        final double minPrice = Collections.min(listWithSpecificType, Comparator.comparing(Triple::getThird)).getThird();
//        List<String> streetNames = listWithSpecificType.stream().filter(e -> e.getThird().compareTo(minPrice) == 0 ).map(Pair::getFirst).collect(Collectors.toList());
        bestPriceGasolineStation.ifPresent(stringGasolineTypeDoubleTriple -> System.out.println("Best price for " + type + " is on: " + stringGasolineTypeDoubleTriple.getFirst()));
    }

    static void findAppsSortedByScores(List<Triple<String, Double, Double>> applicationList) {
        List<Triple<String,Double,Double>> sortedApplicationList =  applicationList.stream().sorted(Comparator.comparing(Triple::getSecond, Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println(sortedApplicationList);
    }

    static void findBestRatingApplication(List<Triple<String, Double, Double>> applicationList) {
        Optional<Triple<String, Double, Double>> worthApp =  applicationList.stream().max(Comparator.comparing(Triple::getThird));
        if(worthApp.isPresent()) {
            System.out.println("Best raiting:" + worthApp.get());
        } else {
            System.out.println("no min value");
        }
    }

    static void findWorthRatingApplication(List<Triple<String, Double, Double>> applicationList) {
        Optional<Triple<String, Double, Double>> worthApp =  applicationList.stream().min(Comparator.comparing(Triple::getThird));
        if(worthApp.isPresent()) {
            System.out.println("Worth rating: " + worthApp.get());
        } else {
            System.out.println("no min value");
        }
    }


    private enum GasolineType {
        DT("ДТ"), AI_92("АИ-92"), AI_95("АИ-95"), AI_98("АИ-98");

        private final String name;

        GasolineType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "GasolineType{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Pair<String, Integer> lastNameToAge = new Pair<>("Пупкин", 18);
        String lastName = lastNameToAge.getFirst();
        Integer age = lastNameToAge.getSecond();
        System.out.println(lastName);
        System.out.println(age);

        Pair<String, List<String>> lastNameToPhoneNumbers = new Pair<>("Пупкин", Arrays.asList("+7 831 2112233", "+7 920 000 22 22"));
        String lastName1 = lastNameToPhoneNumbers.getFirst();
        List<String> phoneNumbers = lastNameToPhoneNumbers.getSecond();

        System.out.println(lastName1);
        System.out.println(phoneNumbers);

        Triple<String, Integer, String> lastNameAgePhone = new Triple<>("Пупкин", 18, "+7 831 2112233");
        System.out.println(lastNameAgePhone);

        List<Pair<Animal,String>> animalFoodList = new ArrayList<>();
        animalFoodList.add(new Pair<>(new Animal("cat", "cat"), "milk"));
        animalFoodList.add(new Pair<>(new Animal("dog", "dog"), "cats"));
        animalFoodList.add(new Pair<>(new Animal("pigeon", "bird"), "corn"));
        animalFoodList.add(new Pair<>(new Animal("shark", "fish"), "perch"));

        feedAnimals(animalFoodList);

        List<Triple<String,GasolineType,Double>> listGasStation = new ArrayList<>();
        listGasStation.add(new Triple<>("Betankura", GasolineType.AI_92, 11.3));
        listGasStation.add(new Triple<>("Volzhskaya", GasolineType.AI_95, 12.3));
        listGasStation.add(new Triple<>("Bulivar", GasolineType.AI_98, 14.3));
        listGasStation.add(new Triple<>("Melnikova", GasolineType.DT, 15.3));
        listGasStation.add(new Triple<>("Odincova", GasolineType.AI_92, 17.3));
        listGasStation.add(new Triple<>("Arbuznaya", GasolineType.AI_92, 11.3));

        findBestPrice(listGasStation, GasolineType.AI_92);
        findBestPrice(listGasStation, GasolineType.AI_95);
        findBestPrice(listGasStation, GasolineType.DT);
        findBestPrice(listGasStation, GasolineType.AI_98);

        List<Triple<String,Double,Double>> applicationList = new ArrayList<>();
        applicationList.add(new Triple<>("vk", 11.0, 1.0));
        applicationList.add(new Triple<>("gosuslugi", 2.0, 3.4));
        applicationList.add(new Triple<>("rostlekom", 100.0, 1.7));
        applicationList.add(new Triple<>("sber", 2021.0, 5.0));
        applicationList.add(new Triple<>("ak bars", 1021.0, 5.0));

        findAppsSortedByScores(applicationList);
        findBestRatingApplication(applicationList);
        findWorthRatingApplication(applicationList);
    }
}
