import java.time.LocalDate;
import java.util.*;

public class Person implements Comparable
{
    private String firstName, lastName;
    private LocalDate birthDate;
    private Set<Person> children;

    public String firstName() { return firstName; }
    public String lastName() { return lastName; }
    public LocalDate birthDate() { return birthDate; }

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

    public Person getYoungestChild()
    {
        if(children.isEmpty())
            return null;
        var opt = children.stream().min(Person::compareTo);
        return opt.orElse(null);
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Person))
            return 0;
        else
            return birthDate.compareTo(((Person)o).birthDate) * -1;
    }

    public List<Person> getChildren()
    {
        var list = new ArrayList<Person>(children);
        list.sort(Comparator.reverseOrder());
        return list;
    }
}
