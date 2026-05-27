import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression", "unused"})
public class Family
{
    private final Map<String, Set<Person>> myFamily = new HashMap<>();

    public  Family(InputStream stream) throws IOException, AmbiguousPersonException {
        var content = new String(stream.readAllBytes());
        int[] data_format = null;
        for(String l: content.split("\n"))
        {
            if(data_format == null)
            {
                data_format = new int[StringUtils.countMatches(l, ',') + 1];
                int ind = 0;
                for (var col: l.split(","))
                {
                    data_format[ind++] = switch (col)
                    {
                        case "imię i nazwisko" -> 1;
                        case "data urodzenia" -> 2;
                        case "data śmierci" -> 3;
                        case "rodzic" -> 4;
                        default -> -1;
                    };
                }

                // Validity check: no unknowns
                if(Arrays.stream(data_format).filter(t -> t == -1).findAny().isPresent())
                    throw new IOException("Unknown column type found.");

                // Validity check: no unknowns
                if(Arrays.stream(data_format).filter(t -> t == 1).findAny().isEmpty())
                    throw new IOException("Person name is required.");
            }
            else
            {
                String[] name = null; // T: 1
                LocalDate birthDate = null; // T: 2
                LocalDate deathDate = null; // T: 3
                ArrayList<Person> parents = new ArrayList<>(); //T; 4

                int ind = 0;
                for(var c: l.split(","))
                    switch (data_format[ind++])
                    {
                        case 1: name = c.split(" "); break;
                        case 2: birthDate = LocalDate.parse(c, DateTimeFormatter.ofPattern("dd.MM.yyyy")); break;
                        case 3: if(!Strings.isNullOrEmpty(c)) deathDate = LocalDate.parse(c, DateTimeFormatter.ofPattern("dd.MM.yyyy")); break;
                        case 4: if(!Strings.isNullOrEmpty(c)) parents.add(get(c).getFirst()); break;
                    }

                // Validity check
                if(name == null)
                    throw new IOException("No name present");
                if(name.length != 2)
                    throw new IOException("Name format invalid");

                if(birthDate == null)
                    birthDate = LocalDate.now();

                if(has(name[0] + " " + name[1]))
                    throw new AmbiguousPersonException(name[0] + " " + name[1]);
                var p = new Person(name[0], name[1], birthDate, deathDate);
                for (var par: parents)
                    try { par.adopt(p); }
                    catch (ParentingAgeException ex)
                    {
                        System.out.print("Parent " + par.fullName() + " is under 15, do you want to allow him to adopt " + p.fullName() + "? [y/N]: ");
                        String res = new Scanner(System.in).nextLine();
                        if(res.equalsIgnoreCase("y"))
                            try { par.adopt(p, /*allowBrakeLaw*/true); }
                            catch (ParentingAgeException ignore) { }
                    }
                add(p);
            }
        }
    }

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

    public boolean has(String nane)
    { return myFamily.containsKey(nane); }

    public ArrayList<Person> get(String key)
    {
        var list = new ArrayList<>(myFamily.get(key));
        list.sort(Comparator.naturalOrder());
        return list;
    }


    public ArrayList<Person> Everyone()
    {
        return new ArrayList<>(myFamily.values().stream().flatMap(Collection::stream).toList());
    }

    @SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
    public static String plant(List<Person> people)
    {
        var build = new StringBuilder();
        build.append("@startuml\n");
        build.append("skinparam actorStyle awesome\n");
        for(var person: people)
            build.append(':').append(person.fullName()).append(": as ")
                    .append(person.hashCode()).append('\n');
        for (var person: people)
        {
            person.getChildren().stream().filter(people::contains).forEach(ch ->
                build.append(person.hashCode()).append(" --> ").append(ch.hashCode()).append('\n'));
        }
        build.append("@enduml");
        return build.toString();
    }

    public String plant()
    {
        var rez = this.myFamily.values().stream().flatMap(Collection::stream).toList();
        return plant(rez);
    }
}
