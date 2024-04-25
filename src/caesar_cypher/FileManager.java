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
        validateSource();
        validateFileExtension(sourcePath);
    }

    public void setDestinationPath() {
        System.out.print("Enter the path of destination file: ");
        destinationPath = Path.of(console.nextLine());
        validateDestination();
        validateFileExtension(destinationPath);
    }

    private void validateSource() {
        if (!Files.exists(sourcePath)) {
            throw new RuntimeException(String.format("Path of '%s' does not exist.", sourcePath));
        }

        if (!Files.isRegularFile(sourcePath)) {
            throw new RuntimeException(String.format("Path of '%s' is not file.", sourcePath));
        }

        try {
            long size = Files.size(sourcePath);
            if (size == 0) {
                throw new RuntimeException(String.format("File '%s' is empty.", sourcePath));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateDestination() {
        String fileName = destinationPath.getFileName().toString();
        if (fileName.contains(".bash_profile") || fileName.contains("hosts")) {
            throw new RuntimeException(String.format("File '%s' is invalid.", destinationPath));
        }
    }

    private void validateFileExtension(Path path) {
        Path fileName = path.getFileName();
        if (!fileName.toString().endsWith(".txt")) {
            throw new RuntimeException(String.format("File '%s' is not text file (*.txt).", path));
        }
    }

    public List<String> readFile() {
        try {
            return Files.readAllLines(sourcePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(List<String> list) {
        try {
            Files.write(destinationPath, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
