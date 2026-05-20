import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.Nullable;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@SuppressWarnings("unused")
public class Person implements Comparable<Person>
{
    private final String firstName, lastName;
    private final LocalDate birthDate;
    private LocalDate deathDate;
    private final transient Set<Person> children;
    private final transient Set<Person> parents;

    public String firstName() { return firstName; }
    public String lastName() { return lastName; }
    public LocalDate birthDate() { return birthDate; }

    public Person(String firstName, String lastName, LocalDate birthDate, @Nullable LocalDate deathDate) throws NegativeLifespanException {
        //Age Check
        if(deathDate != null)
        {
            if(deathDate.isBefore(birthDate))
                throw new NegativeLifespanException(birthDate, deathDate);
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        children = new LinkedHashSet<>();
        parents = new LinkedHashSet<>();
    }
    public Person(String firstName, String lastName, LocalDate birthDate) throws NegativeLifespanException { this(firstName, lastName, birthDate, null); }

    public Person(String FirstName, String LastName) throws NegativeLifespanException {
        this(FirstName, LastName, LocalDate.now());
    }
    @SuppressWarnings("UnusedReturnValue")
    public boolean adopt(Person child_or_prisoner) throws ParentingAgeException
    { return adopt(child_or_prisoner, false); }
    public boolean adopt(Person child_or_prisoner, boolean allowBrakeLaw) throws ParentingAgeException {
        if(ChronoUnit.YEARS.between(birthDate, LocalDate.now()) < 15 && !allowBrakeLaw)
            throw new ParentingAgeException(this, child_or_prisoner);
        return children.add(child_or_prisoner) && child_or_prisoner.parents.add(this);
    }
    public boolean kill() { return kill(LocalDate.now()); }
    public boolean kill(LocalDate deathTime)
    {
        if(isAlive())
            deathDate = deathTime;
        else
            return false;
        return true;
    }

    public String fullName()
    { return firstName + " " + lastName; }
    public boolean isAlive() { return deathDate != null; }

    public Person getYoungestChild()
    {
        if(children.isEmpty())
            return null;
        var opt = children.stream().min(Person::compareTo);
        return opt.orElse(null);
    }

    @Override
    public int compareTo(Person person) {
        return birthDate.compareTo(person.birthDate) * -1;
    }

    public List<Person> getChildren()
    {
        var list = new ArrayList<>(children);
        list.sort(Comparator.reverseOrder());
        return list;
    }

    public static Person fromCsvLine(InputStream csv, int line) throws IOException
    {
        var content = new String(csv.readAllBytes());
        int[] data_format;
        var lines = content.split("\n");
        {
            data_format = new int[StringUtils.countMatches(lines[0], ',') + 1];
            int ind = 0;
            for (var col: lines[0].split(","))
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
        if(lines.length > line + 1)
        {
            String[] name = null; // T: 1
            LocalDate birthDate = null; // T: 2
            LocalDate deathDate = null; // T: 3

            int ind = 0;
            for(var c: lines[line + 1].split(","))
                switch (data_format[ind++])
                {
                    case 1: name = c.split(" "); break;
                    case 2: birthDate = LocalDate.parse(c, DateTimeFormatter.ofPattern("dd.MM.yyyy")); break;
                    case 3: if(!Strings.isNullOrEmpty(c)) deathDate = LocalDate.parse(c, DateTimeFormatter.ofPattern("dd.MM.yyyy")); break;
                }

            // Validity check
            if(name == null)
                throw new IOException("No name present");

            if(name.length != 2)
                throw new IOException("Name format invalid");

            if(birthDate == null)
                birthDate = LocalDate.now();

            return new Person(name[0], name[1], birthDate, deathDate);
        }
        else
            throw new IOException("Index out of range");
    }

    public static ArrayList<Person> fromCsv(InputStream csv) throws IOException, AmbiguousPersonException
    { return (new Family(csv)).Everyone(); }

    @SuppressWarnings("unchecked")
    public static List<Person> fromBin(InputStream stream)
    {
        try(var ser = new ObjectInputStream(stream))
        { return (List<Person>) ser.readObject(); }
        catch (IOException | ClassNotFoundException e)
        { throw new RuntimeException(e); }
    }

    public static void toBin(ArrayList<Person> people, OutputStream stream)
    {
        try(var ser = new ObjectOutputStream(stream))
        { ser.writeObject(people); }
        catch (IOException e)
        { throw new RuntimeException(e); }
    }

    @SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
    public String plant()
    {
        var build = new StringBuilder();
        build.append("@startuml\n");
        build.append("skinparam actorStyle awesome\n");
        build.append(':').append(fullName()).append(": as Main\n");
        for(var parent: parents)
            build.append(':').append(parent.fullName()).append(": --> ").append("Main\n");
        for (var child: children)
            build.append("Main --> ").append(':').append(child.fullName()).append(":\n");
        build.append("@enduml");
        return build.toString();
    }
}
