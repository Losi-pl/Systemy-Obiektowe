import org.jspecify.annotations.NonNull;

import net.sourceforge.plantuml.SourceStringReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlantUMLRunner
{
    static String PlantJarPath;

    public static void setPath(String path)
    { PlantJarPath = Paths.get(path).toAbsolutePath().toString(); }

    public static void generateModel(String content, String folderPath, String fileName) throws IOException {
        Path path = Paths.get(folderPath).toAbsolutePath();
        if(!Files.exists(path))
            Files.createDirectory(path);
        var destPath = Paths.get(path.toString(), fileName + ".png").toAbsolutePath();
        try (var destFile = new FileOutputStream(Files.exists(destPath) ? destPath.toFile() :
                Files.createFile(destPath).toFile())) {
            var render = new SourceStringReader(content);
            if (render.outputImage(destFile).getDescription() == null)
                throw new RuntimeException("Unknown error occurred during generation");
        }
    }
}
