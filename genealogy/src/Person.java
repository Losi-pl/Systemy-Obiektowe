import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Person
{
    String firstName, lastName;
    LocalDate birthDate;
    Set<Person> children;

    public Person(String firstName, String lastName, LocalDate birthDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        children = new LinkedHashSet<Person>();
    }

    public Person(String FirstName, String LastName)
    {
        this(FirstName, LastName, LocalDate.now());
    }

    public boolean adopt(Person child_or_prisoner)
    {
        return children.add(child_or_prisoner);
    }
}
