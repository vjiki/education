import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PersonMain {

  public static void main(String[] args) {
    List<Person> personList = new ArrayList<>();
    personList.add(new Person("Anna", 25));
    personList.add(new Person("Vitalik", 35));
    personList.add(new Person("Mariya", 12));
    personList.add(new Person("Bob", 63));
    personList.add(new Person("Vlad", 32));

    personList.sort(Comparator.comparing(Person::getName));
    System.out.println(personList);
    personList.sort(Comparator.comparing(Person::getAge));
    System.out.println(personList);
  }
}
