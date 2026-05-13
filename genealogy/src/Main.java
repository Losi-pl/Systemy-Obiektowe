import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Family f;
        Person addon;
        try
        {
            var file = Main.class.getResource("/family.csv");
            if(file == null)
                throw new IOException("File family file not found.");
            f = new Family(file.openStream());
            addon  = Person.fromCsvLine(file.openStream(), 10);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        } catch (AmbiguousPersonException e) {
            System.out.println(e);
            return;
        }

        System.out.println(f.get("Marek Kowalski").getFirst().firstName());
        System.out.println(addon.firstName());
    }
}