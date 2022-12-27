package path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class getPath {
    static final Path path = Paths.get("src/path/data.csv");
    static final Path pathSavedFromTak1 = Paths.get("src/createdFiles/fileFromTask1.csv");

    public static Path getPath() {
        return path;
    }

    public static Path getPathSavedFromTak1() {
        return pathSavedFromTak1;
    }
}
