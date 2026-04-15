import java.util.Map;

public class Family
{
    private Map<String, Person> myFamily;

    public void add(Person... people)
    {
        for(Person p: people)
            myFamily.put(p.firstName() + ' ' + p.lastName(), p);
    }

    public Person get(String key)
    {
        return myFamily.get(key);
    }
}
