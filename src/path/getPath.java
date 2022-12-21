package path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class getPath {
    static final Path path = Paths.get("src/path/data.csv");

    public static Path getPath() {
        return path;
    }
}
