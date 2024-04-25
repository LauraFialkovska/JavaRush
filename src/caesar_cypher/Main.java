package caesar_cypher;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String message = "There is no such option, try the game again!";
        Scanner console = new Scanner(System.in);
        CaesarCypher caesarCypher = new CaesarCypher();
        FileManager fileManager = new FileManager();
        List<String> fileContent;
        List<String> encryptedFileContent;

        System.out.println("Menu:");
        System.out.println("1 - to encrypt");
        System.out.println("2 - to decrypt");
        System.out.print("\nSelect the action you want: ");

        if (console.hasNextInt()) {
            switch (console.nextInt()) {
                case 1:
                    caesarCypher.setKey();
                    fileManager.setSourcePath();
                    fileManager.setDestinationPath();
                    fileContent = fileManager.readFile();
                    encryptedFileContent = caesarCypher.encryptFileContent(fileContent);
                    fileManager.writeFile(encryptedFileContent);
                    break;
                case 2:
                    caesarCypher.setKey();
                    fileManager.setSourcePath();
                    fileManager.setDestinationPath();
                    encryptedFileContent = fileManager.readFile();
                    fileContent = caesarCypher.decryptFileContent(encryptedFileContent);
                    fileManager.writeFile(fileContent);
                    break;
                default:
                    System.out.println(message);
            }
        } else {
            System.out.println(message);
        }
    }
}
