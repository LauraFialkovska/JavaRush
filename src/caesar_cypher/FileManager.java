package caesar_cypher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private final Scanner console = new Scanner(System.in);
    private Path sourcePath;
    private Path destinationPath;

    public void setSourcePath() {
        System.out.print("Enter the path of source file: ");
        sourcePath = Path.of(console.nextLine());
        if (!Files.exists(sourcePath)) {
            throw new RuntimeException(String.format("File '%s' does not exist.", sourcePath));
        }
    }

    public void setDestinationPath() {
        System.out.print("Enter the path of destination file: ");
        destinationPath = Path.of(console.nextLine());
    }

    public List<String> readFile() {
        try {
            return Files.readAllLines(sourcePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(List<String> list) {
        try {
            Files.write(destinationPath, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
