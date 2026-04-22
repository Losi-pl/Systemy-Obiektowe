import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        List<Person> people = new ArrayList<Person>();

        people.add(new Person("Jan", "Panowski", LocalDate.of(2000, 5, 18)));

        people.get(0).adopt(new Person("Alice", "Noveda", LocalDate.of(2010, 3, 21)));
        people.get(0).adopt(new Person("Jake", "Thane", LocalDate.of(2005, 10, 30)));

        System.out.println(people.get(0).getYoungestChild().birthDate().getYear());
        System.out.println(people.get(0).getChildren().get(0).birthDate().getYear());

        var f = new Family(Main.class.getResource("/family.csv").openStream());

        //System.out.println(f.get("Alice Noveda"));
    }
}