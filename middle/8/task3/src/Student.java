import java.util.Objects;
import java.util.Set;

public class Student {
  private final String name;
  private final Set<Lection> lections;

  public Student(String name, Set<Lection> lections) {
    this.name = name;
    this.lections = lections;
  }

  public String getName() {
    return name;
  }

  public Set<Lection> getLections() {
    return lections;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Student student)) {
      return false;
    }
    return Objects.equals(name, student.name) && Objects.equals(lections, student.lections);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lections);
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", lections=" + lections +
        '}';
  }
}
