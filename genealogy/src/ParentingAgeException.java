import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ParentingAgeException extends Exception
{
    Person prospective_parent;
    Person prospective_child;

    public ParentingAgeException(Person parent, Person child)
    {
        prospective_parent = parent;
        prospective_child = child;
    }

    @Override
    public String getMessage() {
        var age = ChronoUnit.YEARS.between(prospective_parent.birthDate(), LocalDate.now());
        return "Prospective parent is a minor of age " + Math.floor(age);
    }

    public Person parent()
    { return prospective_parent; }

    public Person child()
    { return prospective_child; }
}
