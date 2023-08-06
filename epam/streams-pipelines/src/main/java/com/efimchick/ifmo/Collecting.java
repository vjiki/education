package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Collecting {

    public int sum(IntStream intStream) {
        return intStream.sum();
    }

    public int production(IntStream intStream) {
        return intStream.reduce(1,(a, b) -> (a * b));
    }

    public int oddSum(IntStream intStream) {
        return intStream.filter(value -> (value % 2 != 0)).sum();
    }

    public Map<Integer, Integer> sumByRemainder(int i, IntStream intStream) {
        Function<Integer,Integer> reminderOfDivision = e -> e % i;
        return intStream.boxed().collect(Collectors.toMap(reminderOfDivision,Function.identity(), (existing, replacement) -> existing + replacement));
    }

    public Map<Person, Double> totalScores(Stream<CourseResult> courseResultStream) {
        //courseResultStream.collect(Collectors.toMap(CourseResult::getPerson, CourseResult::getTaskResults, (existing, replacement) -> existing.get()))
        //return courseResultStream.collect(Collectors.toMap(CourseResult::getPerson, Collectors.summingInt(Integer::intValue), (existing, replacement) ->  replacement)));
        //return null;
        //courseResultStream.mapToInt().average()
        //courseResultStream.map(e -> e.getTaskResults()).map(Map::values).average();
        //courseResultStream.flatMapToInt()
        //courseResultStream.map(v -> v.getTaskResults().entrySet()).mapToInt();
        //Function<Map<String, Integer>,Integer> reminderOfDivision = e -> e.get() % i;
        //courseResultStream.collect(Collectors.averagingInt(CourseResult::getTaskResults));

//        courseResultStream.collect(Collectors.toMap(CourseResult::getPerson, CourseResult::getTaskResults));
//        return null;
//        return courseResultStream
//                .map(CourseResult::getTaskResults)
//                .map(Map::values)
//                .flatMap(Collection::stream)
//                .reduce(0, Integer::sum)
//                .collect(
//                        Collectors.groupingBy(
//                                //Map.Entry::getKey,
//                                CourseResult::getPerson,
//
//                                Collectors.averagingInt(CourseResult::getTaskResults)
//                        )
//                );
//                return courseResultStream
//                .map(CourseResult::getTaskResults)
//                        //.map(Map.Entry::getValue)
//        .map(e -> e.getOrDefault())

//        return courseResultStream
//                .map(test -> test.getTaskResults())
//                .map(Map::values)
//                .flatMap(Collection::stream)
//                .collect(
//                        Collectors.groupingBy(
//                                Map.Entry::getKey,
//                                Collectors.mapping(Map.Entry::getValue, Collectors.toList())
//                        )
//                );

//        return courseResultStream.collect(
//                Collectors.toMap(CourseResult::getPerson,
//                        Collectors.averagingInt(CourseResult::getTaskResults))
//                         .stream().);

        //final int[] elems = {0};
        //Map<String, Integer> taskResults;



//                return courseResultStream
////                .peek(e -> System.out.println(e.getPerson()))
////                .peek(e -> System.out.println(e.getTaskResults()))
////                .peek(e -> System.out.println(e.getTaskResults().size()))
////                .peek(e -> System.out.println(e.getTaskResults().keySet()))
////                .peek(e -> System.out.println(e.getTaskResults().entrySet()))
//                        //.collect(Collectors.toMap())
//                        //.forEach(elem -> {elem.getTaskResults(); taskResults = elem.getTaskResults();})
//                        //.
//                        .collect(Collectors.toMap(e -> e.getPerson(),
//                                e -> e.getTaskResults().values().stream()
//                                        .mapToInt(c -> c).average().getAsDouble()));
       // Map<String, Integer> oldTaskResults = new HashMap<>();

        Map<String, Integer> emptyTaskResults = new HashMap<>();
        emptyTaskResults.put("Wedging", 0);
        emptyTaskResults.put("Phalanxing", 0);
        emptyTaskResults.put("Tercioing", 0);
        emptyTaskResults.put("Shieldwalling", 0);


        return courseResultStream
                //.peek(e -> System.out.println(e.getPerson()))
                //.peek(e -> System.out.println(e.getTaskResults()))
                //.peek(e -> System.out.println(e.getTaskResults().size()))
                //.peek(e -> System.out.println(e.getTaskResults().keySet()))
                //.peek(e -> System.out.println(e.getTaskResults().entrySet()))
                .map(e-> {
                        boolean isElemContainsSpecificTask = false;
                        for (String s : emptyTaskResults.keySet()) {
                            if (e.getTaskResults().keySet().contains(s)) {
                                isElemContainsSpecificTask = true;
                            }
                        }

                        if(isElemContainsSpecificTask) {
                            //System.out.println("elem contains historical tasks ");
                            Map<String, Integer> newTaskResults = new HashMap<>();

                            newTaskResults.putAll(e.getTaskResults());
                            for (String s : emptyTaskResults.keySet()) {
                                if (!e.getTaskResults().keySet().contains(s)) {
                                    newTaskResults.put(s, 0);
                                }
                            }
                            CourseResult courseResult = new CourseResult(e.getPerson(), newTaskResults);
                            return courseResult;
                        } else {
                            //System.out.println("elem does not contain specific tasks ");
                            return e;
                        }
                })
//                .peek(e -> {
//                    oldTaskResults.clear();
//                    oldTaskResults.putAll(e.getTaskResults());
//                    System.out.println("saving old Task results: " + oldTaskResults);
//                })
                //.peek(e -> System.out.println("Calculating average value on: " + e.getTaskResults().entrySet()))
                //.collect(Collectors.toMap())
                //.forEach(elem -> {elem.getTaskResults(); taskResults = elem.getTaskResults();})
                //.
                .collect(Collectors.toMap(e -> e.getPerson(),
                        e -> e.getTaskResults().values().stream()
                                .mapToInt(c -> c).average().getAsDouble()));
    }

    public double averageTotalScore(Stream<CourseResult> courseResultStream) {

        Map<String, Integer> emptyTaskResults = new HashMap<>();
        emptyTaskResults.put("Wedging", 0);
        emptyTaskResults.put("Phalanxing", 0);
        emptyTaskResults.put("Tercioing", 0);
        emptyTaskResults.put("Shieldwalling", 0);

        //return courseResultStream.map(e -> e.getTaskResults().values())
        //        .flatMap(Collection::stream).mapToInt(e -> e).average().getAsDouble();

        return courseResultStream
                .map(e-> {
                    boolean isElemContainsSpecificTask = false;
                    for (String s : emptyTaskResults.keySet()) {
                        if (e.getTaskResults().keySet().contains(s)) {
                            isElemContainsSpecificTask = true;
                        }
                    }

                    if(isElemContainsSpecificTask) {
                        Map<String, Integer> newTaskResults = new HashMap<>();

                        newTaskResults.putAll(e.getTaskResults());
                        for (String s : emptyTaskResults.keySet()) {
                            if (!e.getTaskResults().keySet().contains(s)) {
                                newTaskResults.put(s, 0);
                            }
                        }
                        CourseResult courseResult = new CourseResult(e.getPerson(), newTaskResults);
                        return courseResult;
                    } else {
                        return e;
                    }
                })
                .map(e -> e.getTaskResults().values())
                .flatMap(Collection::stream).mapToInt(e -> e).average().getAsDouble();
    }

    public Map<String, Double> averageScoresPerTask(Stream<CourseResult> courseResultStream) {
//        return courseResultStream
//                //.peek(e -> System.out.println(e.getPerson() + " " + e.getTaskResults() ))
//                .map(e -> e.getTaskResults())
//                .flatMap(e -> e.entrySet().stream())
//                //.peek(System.out::println)
////                .collect(Collectors.toMap(e -> e.getKey(),
////                        e -> (double) e.getValue(),
////                        (existing, replacement) -> (existing + replacement)/2.0));
//                .collect(
//                        Collectors.groupingBy(
//                                entry -> entry.getKey(),
//                                Collectors.averagingInt(entry -> entry.getValue())));


        Map<String, Integer> emptyTaskResults = new HashMap<>();
        emptyTaskResults.put("Wedging", 0);
        emptyTaskResults.put("Phalanxing", 0);
        emptyTaskResults.put("Tercioing", 0);
        emptyTaskResults.put("Shieldwalling", 0);

        return courseResultStream
                .map(e-> {
                    boolean isElemContainsSpecificTask = false;
                    for (String s : emptyTaskResults.keySet()) {
                        if (e.getTaskResults().keySet().contains(s)) {
                            isElemContainsSpecificTask = true;
                        }
                    }

                    if(isElemContainsSpecificTask) {
                        Map<String, Integer> newTaskResults = new HashMap<>();

                        newTaskResults.putAll(e.getTaskResults());
                        for (String s : emptyTaskResults.keySet()) {
                            if (!e.getTaskResults().keySet().contains(s)) {
                                newTaskResults.put(s, 0);
                            }
                        }
                        CourseResult courseResult = new CourseResult(e.getPerson(), newTaskResults);
                        return courseResult;
                    } else {
                        return e;
                    }
                })
                .map(e -> e.getTaskResults())
                .flatMap(e -> e.entrySet().stream())
                .collect(
                        Collectors.groupingBy(
                                entry -> entry.getKey(),
                                Collectors.averagingInt(entry -> entry.getValue())));


    }

    private static Function<Double,String> getMark = e -> {
        if (e.compareTo(100.0) <= 0 && e.compareTo(90.0) > 0) return "A";
        else if (e.compareTo(90.0) <= 0 && e.compareTo(83.0) >= 0) return "B";
        else if (e.compareTo(83.0) < 0 && e.compareTo(75.0) >= 0) return "C";
        else if (e.compareTo(75.0) < 0 && e.compareTo(68.0) >= 0) return "D";
        else if (e.compareTo(68.0) < 0 && e.compareTo(60.0) >= 0) return "E";
        else return "F";
    };

    public Map<Person, String> defineMarks(Stream<CourseResult> courseResultStream) {

        Map<String, Integer> emptyTaskResults = new HashMap<>();
        emptyTaskResults.put("Wedging", 0);
        emptyTaskResults.put("Phalanxing", 0);
        emptyTaskResults.put("Tercioing", 0);
        emptyTaskResults.put("Shieldwalling", 0);

        return courseResultStream
                .map(e-> {
                    boolean isElemContainsSpecificTask = false;
                    for (String s : emptyTaskResults.keySet()) {
                        if (e.getTaskResults().keySet().contains(s)) {
                            isElemContainsSpecificTask = true;
                        }
                    }

                    if(isElemContainsSpecificTask) {
                        Map<String, Integer> newTaskResults = new HashMap<>();

                        newTaskResults.putAll(e.getTaskResults());
                        for (String s : emptyTaskResults.keySet()) {
                            if (!e.getTaskResults().keySet().contains(s)) {
                                newTaskResults.put(s, 0);
                            }
                        }
                        CourseResult courseResult = new CourseResult(e.getPerson(), newTaskResults);
                        return courseResult;
                    } else {
                        return e;
                    }
                })
                .collect(Collectors.toUnmodifiableMap(CourseResult::getPerson,
                        e -> getMark.apply(e.getTaskResults().values().stream().mapToInt(c -> c).average().getAsDouble())));



//        return courseResultStream
//                .collect(Collectors.toUnmodifiableMap(CourseResult::getPerson,
//                        e -> getMark.apply(e.getTaskResults().values().stream().mapToInt(c -> c).average().getAsDouble())));
    }

    public String easiestTask(Stream<CourseResult> courseResultStream) {


        Map<String, Integer> emptyTaskResults = new HashMap<>();
        emptyTaskResults.put("Wedging", 0);
        emptyTaskResults.put("Phalanxing", 0);
        emptyTaskResults.put("Tercioing", 0);
        emptyTaskResults.put("Shieldwalling", 0);


        return Collections.max(courseResultStream.map(e-> {
            boolean isElemContainsSpecificTask = false;
            for (String s : emptyTaskResults.keySet()) {
                if (e.getTaskResults().keySet().contains(s)) {
                    isElemContainsSpecificTask = true;
                }
            }

            if(isElemContainsSpecificTask) {
                Map<String, Integer> newTaskResults = new HashMap<>();

                newTaskResults.putAll(e.getTaskResults());
                for (String s : emptyTaskResults.keySet()) {
                    if (!e.getTaskResults().keySet().contains(s)) {
                        newTaskResults.put(s, 0);
                    }
                }
                CourseResult courseResult = new CourseResult(e.getPerson(), newTaskResults);
                return courseResult;
            } else {
                return e;
            }
        })
                .map(e -> e.getTaskResults())
                .flatMap(e -> e.entrySet().stream())
                .collect(
                        Collectors.groupingBy(
                                entry -> entry.getKey(),
                                Collectors.averagingInt(entry -> entry.getValue()))).entrySet(), Map.Entry.comparingByValue()).getKey();


//        return Collections.max(courseResultStream
//                .map(e -> e.getTaskResults())
//                .flatMap(e -> e.entrySet().stream())
//                .collect(
//                        Collectors.groupingBy(
//                                entry -> entry.getKey(),
//                                Collectors.averagingInt(entry -> entry.getValue()))).entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public Collector printableStringCollector() {
        //return Collectors.joining(" | ", "In Germany ", " are of legal age.");
//        return Collector.of(
//                () -> new StringJoiner(" | "),          // supplier
//                //(j, p) -> j.add(p.toString().toUpperCase()),  // accumulator
//                (stringJoiner, o) -> stringJoiner.add(o.toString()),
//                (j1, j2) -> j1.merge(j2),               // combiner
//                StringJoiner::toString);                // finisher
//        return Collectors.groupingBy(CourseResult::getPerson,
//                Collectors.);

        //Collectors.
        return new MyCollector();
    }

/*

    Example:

    Student        | Phalanxing | Shieldwalling | Tercioing | Wedging | Total | Mark |
    Eco Betty      |          0 |            83 |        89 |      59 | 57.75 |    F |
    Lodbrok Johnny |         61 |            92 |        67 |       0 | 55.00 |    F |
    Paige Umberto  |         75 |            94 |         0 |      52 | 55.25 |    F |
    Average        |      45.33 |         89.67 |     52.00 |   37.00 | 56.00 |    F |

*/


//    private static class MyCollector implements
//            Collector<CourseResult, StringBuffer, String> {
//
//        private Set<String>  courseSet = new TreeSet<>();
//
//        @Override
//        public Supplier<StringBuffer> supplier () {
//            return () -> {
//                System.out.println("supplier call");
//                return new StringBuffer();
//            };
//        }
//
//        @Override
//        public BiConsumer<StringBuffer, CourseResult> accumulator () {
//            return (sb, s) -> {
//                String formatName = "%1$-15s |";
//                String formatResult = "%1$20s |";
//                System.out.println("accumulator function call,"
//                        + " accumulator container: "
//                        + System.identityHashCode(sb)
//                        + " thread: "
//                        + Thread.currentThread().getName()
//                        + ", processing: " + s.getPerson());
//                courseSet.addAll(s.getTaskResults().keySet());
//                sb.append(String.format(formatName, s.getPerson().getLastName() + " " + s.getPerson().getFirstName()));
//
//                for (String cs : courseSet) {
//                    sb.append(String.format(formatResult,s.getTaskResults().get(cs)));
//                }
//
//
//                Double average = s.getTaskResults().values().stream().mapToInt(e -> e).average().getAsDouble();
//
//                sb
//                        .append(String.format(" %.2f", average))
//                        .append(" | ")
//                        .append(String.format("%1$4s |", getMark.apply(average)));
//
//                sb.append("\n");
//            };
//        }
//
//        @Override
//        public BinaryOperator<StringBuffer> combiner () {
//            return (stringBuilder, s) -> {
//                System.out.println("combiner function call");
//                return stringBuilder.append(s);
//            };
//        }
//
//        @Override
//        public Function<StringBuffer, String> finisher () {
//            System.out.println("finisher function call");
//
//            StringBuffer header;
//
//
//            header = new StringBuffer().append(String.format("%1$-15s |","Student"));
//
//            for (String s : courseSet) {
//                header.append(String.format("%1$20s |", s));
//            }
//
//            header.append(" Total | Mark |").append("\n");
//
//            return stringBuilder -> header.append(stringBuilder).toString();
//        }
//
//        @Override
//        public Set<Characteristics> characteristics () {
//            //   return Collections.emptySet();
//            System.out.println("characteristics function call");
//            return EnumSet.of(Characteristics.CONCURRENT
//                    , Characteristics.UNORDERED);
//        }
//    }




    private static class MyCollector implements
            Collector<CourseResult, ArrayList<ArrayList<String>>, String> {

        private Set<String>  courseSet = new TreeSet<>();

        @Override
        public Supplier<ArrayList<ArrayList<String>>> supplier () {
            return () -> {
                System.out.println("supplier call");

                ArrayList<ArrayList<String>> table = new ArrayList<>();
                ArrayList<String> header = new ArrayList<>();

                header.add("Student");

                table.add(header);

                return table;
            };
        }

        @Override
        public BiConsumer<ArrayList<ArrayList<String>>, CourseResult> accumulator () {
            return (table, s) -> {
                System.out.println("accumulator function call,"
                        + " accumulator container: "
                        + System.identityHashCode(table)
                        + " thread: "
                        + Thread.currentThread().getName()
                        + ", processing: " + s.getPerson());

                final String[] practicalHistoryTasks = {"Shieldwalling", "Phalanxing", "Wedging", "Tercioing"};

                boolean isElemContainsSpecificTask = false;
                for (String ss : practicalHistoryTasks) {
                    if (s.getTaskResults().keySet().contains(ss)) {
                        isElemContainsSpecificTask = true;
                    }
                }

                if (isElemContainsSpecificTask) {
                    courseSet.addAll(Arrays.asList(practicalHistoryTasks));
                } else {
                    courseSet.addAll(s.getTaskResults().keySet());
                }

                ArrayList<String> row = new ArrayList<>();
                row.add(s.getPerson().getLastName() + " " + s.getPerson().getFirstName());

                for (String cs : courseSet) {
                    if (s.getTaskResults().get(cs) != null) {
                        row.add(s.getTaskResults().get(cs).toString());
                    } else {
                        row.add("0");
                    }
                }


                Double average = row.subList(1,row.size()).stream().mapToInt(e -> Integer.parseInt(e)).average().getAsDouble();

                row.add(String.format(Locale.US, "%.2f", average));
                row.add(String.format("%1s", getMark.apply(average)));
                table.add(row);
            };
        }

        @Override
        public BinaryOperator<ArrayList<ArrayList<String>>> combiner () {

            return (table, s) -> {
                System.out.println("combiner function call");
                table.add(s.get(0));
                return table;
            };
        }

        public static String simpleTable3(ArrayList<ArrayList<String>> table) {

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
            return sb.toString().trim();
        }



        @Override
        public Function<ArrayList<ArrayList<String>>, String> finisher () {
             System.out.println("finisher function call");




                //ArrayList<String> header =


//                for (String s : courseSet) {
//                    header.append(String.format("%1$20s |", s));
//                }
//
//                header.append(" Total | Mark |").append("\n");
//
                return table -> {
                    ArrayList<String> header = table.get(0);
                    for (String s : courseSet) {
                        header.add(s);
                    }

                    header.add("Total");
                    header.add("Mark");

                    table.set(0, header);

                    Collections.sort(table.subList(1, table.size()), Comparator.comparing(e -> e.get(0)));

                    ArrayList<String> averageRow = new ArrayList<>();

                    averageRow.add("Average");

                    Map<Integer, Double> columnAverage = new HashMap<>();

//                    for (ArrayList<String> strings : table) {
//                        for (String string : strings) {
//                            string
//                        }
//                    }
                    //List<Double> averageList = s.getTaskResults().values().stream().mapToInt(e -> e).average().getAsDouble();


//                    table.subList(1, table.size()).stream().forEach(a ->
//                            Stream.iterate(0, (i -> i < a.size()), (i -> ++i)).forEach(i -> {
//                                a.stream().mapToInt(e -> Integer.valueOf(e)).average().getAsDouble();
//                            })
//                    );


                    Stream.iterate(0, (i -> i < courseSet.size()), (i -> ++i)).forEach(i -> {
                        double avDouble = table.subList(1, table.size()).stream().mapToDouble(e -> Double.parseDouble(e.get(i+1))).average().getAsDouble();
                        System.out.println(avDouble);
//                        if(i == courseSet.size()) {
//                            BigDecimal result = new BigDecimal(avDouble);
//                            result = result.setScale(2, RoundingMode.HALF_DOWN);
//                            averageRow.add(String.format(Locale.US, "%.2f", result));
//                        } else {
                            averageRow.add(String.format(Locale.US, "%.2f", avDouble));
//                        }
                    });

                    double avDouble = averageRow.subList(1,averageRow.size()).stream().mapToDouble(e -> Double.parseDouble(e)).average().getAsDouble();

                    averageRow.add(String.format(Locale.US, "%.2f", avDouble));

                    System.out.println(averageRow.get(averageRow.size()-1));

                    averageRow.add(String.format("%1s", getMark.apply(Double.parseDouble(averageRow.get(averageRow.size()-1)))));


//                    double avDouble = table.subList(1, table.size()).stream().mapToInt(e -> Integer.parseInt(e.get(1))).average().getAsDouble();
//                    table.subList(1, table.size()).stream().collect(Collectors.toList());
//
//                    System.out.println(avDouble);


                    table.add(averageRow);

                    //stringBuilder.toString();
                    return simpleTable3(table);
                };
        }

        @Override
        public Set<Characteristics> characteristics () {
            //   return Collections.emptySet();
            System.out.println("characteristics function call");
            return EnumSet.of(Characteristics.CONCURRENT
                    , Characteristics.UNORDERED);
        }
    }

}
