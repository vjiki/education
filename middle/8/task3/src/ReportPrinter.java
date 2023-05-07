import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

public class ReportPrinter {

  public void printStudentsParticipatedMatAnalysis(List<Student> students) {
    System.out.println("Выведите список студентов, которые хоть раз посещали матанализ.");

    students
        .stream()
        .filter(e -> e.getLections()
            .stream()
            .map(Lection::getName)
            .toList()
            .contains("матанализ"))
        .map(Student::getName).forEach(System.out::println);
  }

  public void printStatParticipationPerStudent(List<Student> students) {
    System.out.println("Выведите статистику посещений для каждого студентам в формате: имя - количество посщенных лекций.");

    students.stream()
        .collect(groupingBy(Student::getName,
            summingInt(e -> e.getLections().size())))
        .forEach( (s, v) -> System.out.println(s + " - " + v));
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
  }
}
