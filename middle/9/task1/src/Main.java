import model.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {

    private final static String personXmlDes = """
           <Человек>
                <Имя>Vasya</Имя>
                <Возраст>12</Возраст>
            </Человек>
           """;

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        XmlReader xmlReader = new XmlReader();
        Person person = new Person("Vasya", 18, "querty");

        String personXml = xmlReader.serialize(List.of(person));
        System.out.println(personXml);

        Person desPerson = (Person) xmlReader.deserialize(personXmlDes, Person.class);
        System.out.println(desPerson);
    }
}