public class AmbiguousPersonException extends Exception
{
    String duplicated_person;

    public AmbiguousPersonException(String target)
    { duplicated_person = target; }

    @Override
    public String getMessage() {
        return "There are duplicates of " + duplicated_person;
    }
}
