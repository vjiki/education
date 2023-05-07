import java.util.ArrayList;
import java.util.List;

public class LectionMain {

  public static void main(String[] args) {
    List<Student> students = new ArrayList<>();
    Utils utils = new Utils();


    for (int i = 0; i < 10; i++) {
      String name = "vasya" + i;
      Student student = new Student(name, utils.getRandomLectionSet());
      students.add(student);
    }

    ReportPrinter reportPrinter = new ReportPrinter();

    reportPrinter.printStudentsParticipatedMatAnalysis(students);
    reportPrinter.printStatParticipationPerStudent(students);
    reportPrinter.printNameOfDisciplineWhichHasMaxParticipation(students);
    reportPrinter.printStudentNamesWhichHasMaxParticipationPerDay(students);
    reportPrinter.printStatPerCourseWithCountOfStudentsWhichParticipatedAtLeastOnce(students);
  }
}