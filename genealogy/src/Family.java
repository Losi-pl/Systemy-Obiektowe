import java.util.*;

public class Family
{
    private Map<String, Set<Person>> myFamily = new HashMap<>();

    public void add(Person... people)
    {
        for(Person p: people)
        {
            String key = p.firstName() + ' ' + p.lastName();
            if(myFamily.containsKey(key))
                myFamily.get(key).add(p);
            else
            {
                var set = new LinkedHashSet<Person>();
                set.add(p);
                myFamily.put(key, set);
            }
        }
    }

    public ArrayList<Person> get(String key)
    {
        var list = new ArrayList<Person>(myFamily.get(key));
        list.sort(Comparator.naturalOrder());
        return list;
    }
}
