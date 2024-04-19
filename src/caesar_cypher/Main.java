package caesar_cypher;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        CaesarCypher caesarCypher = new CaesarCypher();
        FileManager fileManager = new FileManager();
        List<String> fileContent;
        List<String> cipherFileContent;

        System.out.println("Menu:");
        System.out.println("1 - to encrypt");
        System.out.println("2 - to decrypt");
        System.out.print("\nSelect the action you want: ");
        int choice = console.nextInt();

        switch (choice) {
            case 1:
                caesarCypher.setKey();
                fileManager.setSourcePath();
                fileManager.setDestinationPath();
                fileContent = fileManager.readFile();
                cipherFileContent = caesarCypher.cipherFileContent(fileContent);
                fileManager.writeToFile(cipherFileContent);
                break;
            case 2:
                caesarCypher.setKey();
                fileManager.setSourcePath();
                fileManager.setDestinationPath();
                cipherFileContent = fileManager.readFile();
                fileContent = caesarCypher.deCipherFileContent(cipherFileContent);
                fileManager.writeToFile(fileContent);
                break;
            default:
                System.out.println("Only two options are available, try the game again!");
        }
    }
}
