import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
  public final static String[] lectionNames =
      new String[]{"матанализ", "философия", "английкий язык", "история", "физкультура"};


  public LocalDate randomDateBetween(LocalDate startInclusive, LocalDate endExclusive) {
    long startEpochDay = startInclusive.toEpochDay();
    long endEpochDay = endExclusive.toEpochDay();
    long randomDay = ThreadLocalRandom
        .current()
        .nextLong(startEpochDay, endEpochDay);

    return LocalDate.ofEpochDay(randomDay);
  }

  Set<Lection> getRandomLectionSet() {
    Set<Lection> lectionSet = new HashSet<>();

    LocalDate start = LocalDate.of(2020, Month.OCTOBER, 14);
    LocalDate end = LocalDate.now();


    long limit = ThreadLocalRandom
        .current()
        .nextLong(1, 10);

    for (int i = 0; i < limit; i++) {
      int lectionNum = (int) ThreadLocalRandom
          .current()
          .nextLong(0, lectionNames.length);
      LocalDate randomLocalDate = randomDateBetween(start, end);
      lectionSet.add(new Lection(lectionNames[lectionNum], randomLocalDate));
    }

    return lectionSet;
  }
}
