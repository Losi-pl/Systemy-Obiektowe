import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Person implements Comparable
{
    private String firstName, lastName;
    private LocalDate birthDate;
    private Optional<LocalDate> deathDate;
    private Set<Person> children;

    public String firstName() { return firstName; }
    public String lastName() { return lastName; }
    public LocalDate birthDate() { return birthDate; }

    public Person(String firstName, String lastName, LocalDate birthDate, Optional<LocalDate> deathDate) throws NegativeLifespanException {
        //Age Check
        if(deathDate.isPresent())
            if(deathDate.get().isBefore(birthDate))
                throw new NegativeLifespanException(birthDate, deathDate.get());

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        children = new LinkedHashSet<Person>();
    }
    public Person(String firstName, String lastName, LocalDate birthDate) throws NegativeLifespanException { this(firstName, lastName, birthDate, Optional.empty()); }

    public Person(String FirstName, String LastName) throws NegativeLifespanException {
        this(FirstName, LastName, LocalDate.now());
    }
    public boolean adopt(Person child_or_prisoner) throws ParentingAgeException
    { return adopt(child_or_prisoner, false); }
    public boolean adopt(Person child_or_prisoner, boolean allowBrakeLaw) throws ParentingAgeException {
        if(ChronoUnit.YEARS.between(birthDate, LocalDate.now()) < 15 && !allowBrakeLaw)
            throw new ParentingAgeException(this, child_or_prisoner);
        return children.add(child_or_prisoner);
    }
    public boolean kill() { return kill(LocalDate.now()); }
    public boolean kill(LocalDate deathTime)
    {
        if(isAlive())
            deathDate = Optional.of(deathTime);
        else
            return false;
        return true;
    }

    public String fullName()
    { return firstName + " " + lastName; }
    public boolean isAlive() { return deathDate.isPresent(); }

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

    public static Person fromCsvLine(InputStream csv, int line) throws IOException
    {
        var content = new String(csv.readAllBytes());
        int[] data_format = null;
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
            Optional<LocalDate> deathDate = Optional.empty(); // T: 3

            int ind = 0;
            for(var c: lines[line + 1].split(","))
                switch (data_format[ind++])
                {
                    case 1: name = c.split(" "); break;
                    case 2: birthDate = LocalDate.parse(c, DateTimeFormatter.ofPattern("dd.MM.yyyy")); break;
                    case 3: if(!Strings.isNullOrEmpty(c)) deathDate = Optional.of(LocalDate.parse(c, DateTimeFormatter.ofPattern("dd.MM.yyyy"))); break;
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
}
