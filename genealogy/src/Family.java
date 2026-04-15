import java.util.Map;

public class Family
{
    private Map<String, Person> myFamily;

    public void add(Person person)
    {
        myFamily.put(person.firstName() + ' ' + person.lastName(), person);
    }

    public Person get(String key)
    {
        return myFamily.get(key);
    }
}
