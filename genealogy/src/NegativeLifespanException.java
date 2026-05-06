import java.io.IOException;
import java.time.LocalDate;

public class NegativeLifespanException extends IOException {
    LocalDate birthDate, deathDate;

    public  NegativeLifespanException(LocalDate birthDate, LocalDate deathDate)
    {
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    @Override
    public String getMessage() {
        return "The persons date of death is before their date of birth.";
    }

    public LocalDate BirthDate() { return birthDate; }
    public LocalDate DeathDate() { return deathDate; }
}
