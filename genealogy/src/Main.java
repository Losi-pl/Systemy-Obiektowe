import java.io.IOException;

//https://github.com/BartekDaniluk/OOP_2025_laby
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
        } catch (AmbiguousPersonException ex) {
            ex.printStackTrace(System.err);
            return;
        }

        System.out.println(f.get("Marek Kowalski").getFirst().firstName());
        System.out.println(addon.firstName());

        PlantUMLRunner.setPath("jar/plantuml-1.2026.3.jar");

        try { PlantUMLRunner.generateModel(f.plant(), "out/png", "image"); }
        catch (IOException ignore) { }
    }
}
