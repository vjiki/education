import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ReportPrinter {

  public void printStudentsParticipatedMatAnalysis(List<Student> students) {
    System.out.println("Выведите список студентов, которые хоть раз посещали матанализ.");

    students
        .stream()
        .filter(e -> e.getLections()
            .stream()
            .map(Lection::getName)
            .anyMatch(l -> l.contains("матанализ")))
        .map(Student::getName).forEach(System.out::println);
  }

  public void printStatParticipationPerStudent(List<Student> students) {
    System.out.println("Выведите статистику посещений для каждого студентам в формате: имя - количество посещенных лекций.");

    students.forEach(s ->
        System.out.println(s.getName() + " - " + s.getLections().size()));
  }

  public void printNameOfDisciplineWhichHasMaxParticipation(List<Student> students) {
    System.out.println("Выведите название дисциплин, имеющих наибольшее количество посещений.");

    Map<String, Integer> lectionToNumParticipations = new HashMap<>();

    students.forEach(e -> e.getLections().forEach(l -> {
      int newNum = 1;
      Integer num = lectionToNumParticipations.get(l.getName());
      if (num != null) {
        newNum = num + 1;
      }
      lectionToNumParticipations.put(l.getName(), newNum);
    }));

    Optional<Entry<String, Integer>> maxEntry = lectionToNumParticipations.entrySet()
        .stream()
        .max(Entry.comparingByValue());

    System.out.println(maxEntry.get().getKey() + " - " + maxEntry.get().getValue());

    // second approach

    Map<String, Long> disciplinesAttended = students.stream()
        .flatMap(student -> student.getLections().stream())
        .collect(groupingBy(Lection::getName, counting()));

    Long discAttendsAndThen = students.stream()
        .flatMap(student -> student.getLections().stream())
        .collect(Collectors.collectingAndThen(groupingBy(Lection::getName, counting()),
            attends -> Collections.max(attends.values())));

    disciplinesAttended.entrySet().stream()
        .filter(entry -> entry.getValue().equals(discAttendsAndThen))
        .forEach(System.out::println);

    // third approach

    System.out.println("\nКоличество посещений по дисциплинам:");
    List<Lection> attendedLections = students.stream()
            .flatMap(s -> s.getLections().stream())
            .toList();
    final Map<String, Long> collect = attendedLections.stream()
            .collect(
                    groupingBy(Lection::getName,
                            counting()/*summingLong( l -> {return 1;})*/ )
            );
    System.out.println(
            collect
    );
    System.out.println("3. Названия дисциплин, имеющих наибольшее количество посещений:");
    System.out.println(
            collect.keySet().stream().filter(
                    l -> collect.get(l).equals( Collections.max(collect.values()) )
            ).toList()
    );

    // not right fours approach
    Map<String, Integer> visitedLectures = students.stream()
            .flatMap(student -> student.getLections().stream())
            .collect(Collectors.groupingBy(Lection::getName, Collectors.summingInt(x -> 1)));

    Optional<Integer> max = visitedLectures.values().stream().max(Integer::compareTo);
    System.out.println("Max: " + max.get());


    // not right firths approach

    Map<String, Long> lecturesStats = students.stream()
            .flatMap(student -> student.getLections().stream())
            .collect(groupingBy(Lection::getName, counting()));

    Optional<Map.Entry<String, Long>> max1 = lecturesStats.entrySet().stream().max(Map.Entry.comparingByValue());

    System.out.println("3. Названия дисциплины, имеющих наибольшее количество посещений");
    if (max1.isPresent()) {
      Map.Entry<String, Long> entry = max1.get();
      System.out.println(entry.getKey() + " - " + entry.getValue());
    }

  }

  public void printStudentNamesWhichHasMaxParticipationPerDay(List<Student> students) {
    System.out.println("Выведите имена студентов, которые посетили наибольшее количество лекций в день.");

    //    Set<LocalDate> days = students.stream()
//        .map(Student::getLections)
//        .flatMap(Collection::stream)
//        .map(Lection::getDate).collect(Collectors.toSet());

    Map<String, Integer> studentToNumParticipationsPerDay = new HashMap<>();

    students.forEach(e -> {
          Map<LocalDate, Integer> maxPerStudent = new HashMap<>();

          e.getLections().forEach(l -> {
            int newNum = 1;
            Integer num = maxPerStudent.get(l.getDate());
            if (num != null) {
              newNum = num + 1;
            }
            maxPerStudent.put(l.getDate(), newNum);
          });

          Integer max = maxPerStudent.values().stream().max(Integer::compareTo).orElse(0);
          studentToNumParticipationsPerDay.put(e.getName(), max);
        }
    );

    Integer maxPerDay = studentToNumParticipationsPerDay.values().stream().max(Integer::compareTo).orElse(0);

    studentToNumParticipationsPerDay.forEach((k,v) ->
    {
      if(maxPerDay.equals(v)) {
        System.out.println(k + " - " + v);
      }
    });

    // second approach

    Long maxAttendsInDay = students.stream()
        .map(student -> maxAttends(student.getLections()))
        .max(Long::compareTo)
        .orElse(null);

    List<String> students1 = students.stream()
        .filter(student -> maxAttends(student.getLections()).equals(maxAttendsInDay))
        .map(Student::getName)
        .toList();
    System.out.printf("Студенты: %s посетили в день max кол-во лекций: %d\n", students1, maxAttendsInDay);

    // third approach

    Map<String,Long> attendances = new HashMap<>();
    for (Student student: students) {
      final Map<LocalDate, Long> studentLections = student.getLections().stream()
              .collect(
                      groupingBy(Lection::getDate, counting())
              );
      attendances.put(student.getName(),Collections.max(studentLections.values()));
    }
    System.out.println(
            attendances.keySet().stream()
                    .filter(
                            a->attendances.get(a).equals((Collections.max(attendances.values()))))
                    .collect(toList())
    );


    //4. Students name with the biggest amount of visits a day
    Map<String, Integer> nameAndMaxVisits = new HashMap<>();

    for (Student st : students) {
      Map<LocalDate, Integer> collect = st.getLections().stream()
              .collect(Collectors.groupingBy(Lection::getDate, Collectors.summingInt(x -> 1)));

      Optional<Integer> maxV = collect.values().stream().max(Comparator.comparingInt(Integer::intValue));
      nameAndMaxVisits.put(st.getName(), maxV.get());
    }
    System.out.println("Max visits for each name " + nameAndMaxVisits);

    //fifth approach

    System.out.println("4. Имена студентов, которые посетили наибольшее количество лекций в день.");

    // Думаю не очень читабильно...
    Map.Entry<Long, List<Student>> longListEntry = students.stream()
            .collect(groupingBy(student -> student.getLections().stream()
                    .collect(groupingBy(Lection::getDate, counting()))
                    .values().stream().max(Comparator.comparingLong(value -> value)).orElse(0L)))
            .entrySet().stream()
            .max(Map.Entry.comparingByKey())
            .orElse(new AbstractMap.SimpleEntry<>(0L, new ArrayList<>())); // in case of null


    System.out.printf("Кол-во лекции: %s - Студенты: %s%n", longListEntry.getKey(), longListEntry.getValue().stream().map(Student::getName).collect(toList()));

  }

  private static Long maxAttends(Set<Lection> lections) {
    return lections.stream()
        .collect(Collectors.collectingAndThen(
            groupingBy(Lection::getDate, counting()),
            attends -> Collections.max(attends.values())));
  }

  public void printStatPerCourseWithCountOfStudentsWhichParticipatedAtLeastOnce(List<Student> students) {
    System.out.println(" Выведите статистику по курсам в формате:\n"
        + "название курсов - количество разных студентов, которые посетили хотя бы одно занятие. (т.е. в лучшем случае это будет 10)");

    Map<String, Set<String>> lectionToStudentNames = new HashMap<>();

    students.forEach(e -> e.getLections().forEach( l -> {
          Set<String> sts = lectionToStudentNames.get(l.getName());

          if (sts != null) {
            sts.add(e.getName());
          } else {
            sts = new HashSet<>();
            sts.add(e.getName());
          }
          lectionToStudentNames.put(l.getName(), sts);
        }
    ));

    System.out.println(lectionToStudentNames);
    lectionToStudentNames.forEach((l, s) -> System.out.println(l + " - " + s.size()));

    // second approach

    Set<String> lectionNames =
        students.stream()
            .flatMap(student -> student.getLections().stream().map(Lection::getName))
            .collect(Collectors.toSet());

    final Map<String, Set<String>> studensOnDiscipline = new HashMap<>();
    for (String lectionName : lectionNames) {
      Set<String> students2 =
          students.stream()
              .filter(s -> s.getLections().stream().anyMatch(l -> l.getName().equals(lectionName)))
              .map(Student::getName)
              .collect(Collectors.toSet());
      studensOnDiscipline.put(lectionName, students2);
    }
    studensOnDiscipline.forEach((l, s) ->  System.out.printf("%s - %d\n", l, s.size()));

    // third approach

    Map<String,Long> courseStudents = new HashMap<>();
    for (String course: lectionNames) {
      courseStudents.put(course, students.stream()
              .filter(s -> s.getLections().stream()
                      .anyMatch(l -> l.getName().equals(course)))
              .count());
    }
    System.out.println("\n5. Статистику по курсам в формате:");
    System.out.println("\"название курса - количество разных студентов, посетивших хотя бы одно занятие\":");
    courseStudents.keySet()
            .forEach(k-> System.out.println(k + "-" + courseStudents.get(k)));


    // statictics by course /название курсов - количество разных студентов
    Map<String, Set<String>> courseAndStudents = new HashMap<>();
    Map<String, Set<String>> lectAndStudents = new HashMap<>();
    Set<String> studentSet = new HashSet<>();

    for (Student st : students) {
      Map<String, List<Lection>> lectNameAndLections = st.getLections().stream().collect(Collectors.groupingBy(Lection::getName));
      Set<String> lectionType = lectNameAndLections.keySet();
      courseAndStudents.put(st.getName(), lectionType);
      for (String lect : lectionType) {
        studentSet.add(st.getName());
        lectAndStudents.put(lect, studentSet);
      }
    }


    System.out.println("======5======= " + lectAndStudents);


    System.out.println("5. название курсов - количество разных студентов, которые посетили хотя бы одно занятие.");
    for (String course: lectionNames) {
      countStudentsAtLeastAttendedOnce(students, course);
    }
  }

  private void countStudentsAtLeastAttendedOnce(List<Student> students, String course) {
    Long studentsCount = students.stream()
            .filter(student -> student.getLections().stream()
                    .anyMatch(lecture -> lecture.getName().equals(course)))
            .count();
    System.out.printf("%s - %s %n", course, studentsCount);
  }

}


