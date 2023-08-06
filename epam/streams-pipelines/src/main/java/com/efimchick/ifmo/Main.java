package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;
import com.efimchick.ifmo.util.StreamHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void simpleTable() {

        /*
         * leftJustifiedRows - If true, it will add "-" as a flag to format string to
         * make it left justified. Otherwise right justified.
         */
        boolean leftJustifiedRows = false;

        /*
         * Table to print in console in 2-dimensional array. Each sub-array is a row.
         */
        String[][] table = new String[][] {
                { "id", "First Name", "Last Name", "Age" },
                { "1", "John", "Johnson", "45" },
                { "2", "Tom", "", "35" },
                { "3", "Rose", "Johnson", "22" },
                { "4", "Jimmy", "Kimmel", "" } };

        /*
         * Calculate appropriate Length of each column by looking at width of data in
         * each column.
         *
         * Map columnLengths is <column_number, column_length>
         */
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        System.out.println("columnLengths = " + columnLengths);

        /*
         * Prepare format String
         */
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");
        System.out.println("formatString = " + formatString.toString());

        /*
         * Print table
         */
        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table[a]));

    }

    public static String simpleTable2(ArrayList<ArrayList<String>> table) {

        Map<Integer, Integer> columnLengths = new HashMap<>();
            table.stream().forEach(a ->
                Stream.iterate(0, (i -> i < a.size()), (i -> ++i)).forEach(i -> {
                    if (columnLengths.get(i) == null) {
                        columnLengths.put(i, 0);
                    }
                    if (columnLengths.get(i) < a.get(i).length()) {
                        columnLengths.put(i, a.get(i).length());
                    }
                })
            );
        System.out.println("columnLengths = " + columnLengths);

        /*
         * Prepare format String
         */
        final StringBuilder formatString = new StringBuilder("");
        columnLengths.entrySet().stream().forEach(e -> {
            String flag = e.getKey() == 0 ? "%-" : " %";
            formatString.append(flag + e.getValue() + "s |");
        });
        formatString.append("\n");
        System.out.println("formatString = " + formatString.toString());

        /*
         * Print table
         */

        StringBuffer sb = new StringBuffer();

        table.stream().forEach(e -> sb.append(String.format(formatString.toString(), e.toArray())));
        return sb.toString();
    }


    public static void main(String[] args) {

        //shotTest(0, 2, Main::fail, Main::pass);

        List<CourseResult> courseResultList = new ArrayList<>();

        Person anton = new Person("Anton", "Aciphriev", 29);
        Map<String, Integer> taskResults = new HashMap<>();
        taskResults.put("Math", 4);
        taskResults.put("English", 3);
        taskResults.put("Russian", 5);

        Person karton = new Person("Karton", "Aciphriev", 30);
        Map<String, Integer> taskResultsKarton = new HashMap<>();
        taskResultsKarton.put("Math", 59);
        taskResultsKarton.put("English", 79);
        taskResultsKarton.put("Russian", 93);
        taskResultsKarton.put("Geographiya", 30);

        Person vagon = new Person("Vagon", "Aciphriev", 31);
        Map<String, Integer> taskResultsVagon = new HashMap<>();
        taskResultsVagon.put("Math", 1);
        taskResultsVagon.put("English", 2);
        taskResultsVagon.put("Russian", 3);

        courseResultList.add(new CourseResult(anton,taskResults));
        courseResultList.add(new CourseResult(karton,taskResultsKarton));
        courseResultList.add(new CourseResult(vagon,taskResultsVagon));
        //courseResultList.add(new CourseResult(karton,taskResultsKarton));


/*        starting comment*/

//        for (CourseResult e : courseResultList) {
//            System.out.println(e.getPerson());
//            System.out.println(e.getTaskResults());
//            System.out.println(e.getTaskResults().values());
//            System.out.println(e.getTaskResults().values().stream().reduce(0,(a, b) -> (a + b)));
//            System.out.println(e.getTaskResults().values().stream().mapToInt(c -> c).average().getAsDouble());
//        }
//
//        Map<Person, Double> collect = courseResultList.stream()
//                .peek(e -> System.out.println(e.getPerson()))
//                .peek(e -> System.out.println(e.getTaskResults()))
//                .collect(Collectors.toMap(CourseResult::getPerson,
//                        e -> e.getTaskResults().values().stream().mapToInt(c -> c).average().getAsDouble()));
//
//        System.out.println(collect);
//
//        List<Integer> collect1 = courseResultList.stream().map(e -> e.getTaskResults().values())
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//
//        System.out.println(collect1);
//
//        double average = courseResultList.stream().map(e -> e.getTaskResults().values())
//                .flatMap(Collection::stream).mapToInt(e -> e).average().getAsDouble();
//
//        System.out.println(average);
//
////        Map<String,Double> averagePerTask = courseResultList.stream()
////                .map(e->e.getTaskResults().entrySet())
////                .peek(System.out::println)
////                .collect(Collectors.toMap(Function.identity(),
////                        e -> e.,
////                        (existing, replacement) -> existing + replacement));
//
//
//
//
////        map.forEach((K,V) -> System.out.println(K + ", Stock : " + V));
//
//
//
//        //Set<Map.Entry<String, Integer>> collect2 = courseResultList.stream().map(e -> e.getTaskResults()).flatMap(e -> e.entrySet().stream()).collect(Collectors.toSet());
//        Map<String, Double> collect2 = courseResultList.stream()
//                .map(e -> e.getTaskResults())
//                .flatMap(e -> e.entrySet().stream())
//                //.collect(Collectors.toMap(entry -> entry.getKey(), entry -> (double) entry.getValue(), (existing, replacement) -> (existing + replacement)/2.0));
//                //.collect(Collectors.toMap(entry -> entry.getKey(), entry -> (double) entry.getValue(), (a,b) -> Double.sum(a,b)/2.0));//(existing, replacement) -> (existing + replacement)/2.0));
//                .collect(
//                        Collectors.groupingBy(
//                                entry -> entry.getKey(),
//                                Collectors.averagingInt(entry -> entry.getValue())));
//
//        System.out.println(collect2);
//
//
//
//
//
//        List<Map<String, Integer>> count = courseResultList.stream()
//                .peek(e -> System.out.println("e"))
//                .map(e -> e.getTaskResults())
//
//                //.flatMap(Stream::of)
//                //.flatMap(x -> x.getTaskResults().stream())
//                //.collect(Collectors.toMap(e -> e.keySet(), e->e.values()));
//        .collect(Collectors.toList());
//
//        System.out.println(count);
//
//
//        Function<Double,String> getMark = e -> {
//            if (e.compareTo(100.0) <= 0 && e.compareTo(90.0) > 0) return "A";
//            else if (e.compareTo(90.0) <= 0 && e.compareTo(83.0) >= 0) return "B";
//            else if (e.compareTo(83.0) < 0 && e.compareTo(75.0) >= 0) return "C";
//            else if (e.compareTo(75.0) < 0 && e.compareTo(68.0) >= 0) return "D";
//            else if (e.compareTo(68.0) < 0 && e.compareTo(60.0) >= 0) return "E";
//            else return "F";
//        };
//
//        System.out.println(getMark.apply(60.0));
//
//        Map<Person, String> collect3 = courseResultList.stream()
//                .peek(e -> System.out.println(e.getPerson()))
//                .peek(e -> System.out.println(e.getTaskResults()))
//                .collect(Collectors.toMap(CourseResult::getPerson,
//                        e -> getMark.apply(e.getTaskResults().values().stream().mapToInt(c -> c).average().getAsDouble())));
//
//        System.out.println(collect3);
//
//
//        Map<String, Double> course4 = courseResultList.stream()
//                .map(e -> e.getTaskResults())
//                .flatMap(e -> e.entrySet().stream())
//                 .collect(
//                        Collectors.groupingBy(
//                                entry -> entry.getKey(),
//                                Collectors.averagingInt(entry -> entry.getValue())));
//
//        String key = Collections.max(courseResultList.stream()
//                        .map(e -> e.getTaskResults())
//                        .flatMap(e -> e.entrySet().stream())
//                        .collect(
//                                Collectors.groupingBy(
//                                        entry -> entry.getKey(),
//                                        Collectors.averagingInt(entry -> entry.getValue()))).entrySet(), Map.Entry.comparingByValue()).getKey();
//
//        System.out.println(key);
//
//        Object maxEntry = Collections.max(course4.entrySet(), Map.Entry.comparingByValue()).getKey();
//
////        Object maxEntry2 = Collections.max(courseResultList.stream()
////                .map(e -> e.getTaskResults())
////                .flatMap(e -> e.entrySet().stream())
////                .collect(Collectors.groupingBy(
////                        entry -> entry.getKey(),
////                        Collectors.averagingInt(entry -> entry.getValue()))), Collectors.averagingInt(entry -> entry.getValue())).getKey();
//
//        System.out.println(maxEntry);


        //        Map<Person, String> collect3 = courseResultList.stream()
//                .peek(e -> System.out.println(e.getPerson()))
//                .peek(e -> System.out.println(e.getTaskResults()))
//                .collect(Collectors.toMap(CourseResult::getPerson,
//                        e -> getMark.apply(e.getTaskResults().values().stream().mapToInt(c -> c).average().getAsDouble())));
//
//        System.out.println(collect3);


//        Map<String, Integer> oldTaskResults = new HashMap<>();
//
//
//        Map<Person, Double> personDoubleMap = courseResultList.stream()
//                //.peek(e -> System.out.println(e.getPerson()))
//                //.peek(e -> System.out.println(e.getTaskResults()))
//                //.peek(e -> System.out.println(e.getTaskResults().size()))
//                //.peek(e -> System.out.println(e.getTaskResults().keySet()))
//                .peek(e -> System.out.println(e.getTaskResults().entrySet()))
//                .map(e-> {
//                    if (oldTaskResults.isEmpty()) {
//                        System.out.println("old tasks is empty. no previous elem.");
//                        return e;
//                    } else {
//
//                        boolean isPrevElemContainsTask = false;
//                        for (String s : oldTaskResults.keySet()) {
//                            if (e.getTaskResults().keySet().contains(s)) {
//                                isPrevElemContainsTask = true;
//                            }
//                        }
//
//                        if(isPrevElemContainsTask) {
//                            System.out.println("prev elem contains some new values ");
//                            Map<String, Integer> newTaskResults = new HashMap<>();
//
//                            newTaskResults.putAll(e.getTaskResults());
//                            for (String s : oldTaskResults.keySet()) {
//                                if (!e.getTaskResults().keySet().contains(s)) {
//                                    newTaskResults.put(s, 0);
//                                }
//                            }
//                            CourseResult courseResult = new CourseResult(e.getPerson(), newTaskResults);
//                            return courseResult;
//                        } else {
//                            System.out.println("prev elem does not contain any task ");
//                            return e;
//                        }
//                    }
//                })
//                .peek(e -> {
//                    oldTaskResults.clear();
//                    oldTaskResults.putAll(e.getTaskResults());
//                    System.out.println("saving old Task results: " + oldTaskResults);
//                })
//                .peek(e -> System.out.println("Calculating average value on: " + e.getTaskResults().entrySet()))
//                //.collect(Collectors.toMap())
//                //.forEach(elem -> {elem.getTaskResults(); taskResults = elem.getTaskResults();})
//                //.
//                .collect(Collectors.toMap(e -> e.getPerson(),
//                        e -> e.getTaskResults().values().stream()
//                                .mapToInt(c -> c).average().getAsDouble()));
//
//        System.out.println(personDoubleMap);


        // no need in queue
        // just create one more local variable with Course Result and keep there the current value
        // check it next time
        // or create a Map with Course Results

//        StreamHistory<CourseResult> streamHistory = new StreamHistory<>(2); // instance of StreamHistory which will store last 2 elements
//
//        courseResultList.stream().map(courseResult -> streamHistory.save(courseResult))
//                .forEach(history -> {
//                    System.out.println(history.getLastElements().getFirst() + " " + history.getLastElements().getLast());
//                    System.out.println(history.getLastElements().getFirst().getPerson() + " " + history.getLastElements().getLast());
//                    //System.out.println(history.getLastElements().getLast());
//                    history.getLastElements().forEach(System.out::print);
//                    System.out.println();
//        });



        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

        ArrayList<String> row1 = new ArrayList<String>();
        row1.add("id");
        row1.add("First Name");
        row1.add("Last Name");
        row1.add("Age");
        ArrayList<String> row2 = new ArrayList<String>();
        row2.add("1");
        row2.add("John");
        row2.add("Johnson");
        row2.add("45");
        ArrayList<String> row3 = new ArrayList<String>();
        row3.add("2");
        row3.add("Tom");
        row3.add("");
        row3.add("35");
        ArrayList<String> row4 = new ArrayList<String>();
        row4.add("3");
        row4.add("Rose");
        row4.add("Johnson ASS asas  axasx");
        row4.add("22");
        ArrayList<String> row5 = new ArrayList<String>();
        row5.add("4");
        row5.add("Jimmy");
        row5.add("Kimmel");
        row5.add("");
        table.add(row1);
        table.add(row2);
        table.add(row3);
        table.add(row4);
        table.add(row5);

        simpleTable();
        System.out.println(simpleTable2(table));



    }
}
