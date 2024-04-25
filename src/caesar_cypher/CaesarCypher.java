package caesar_cypher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaesarCypher {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м',
            'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.',
            ',', '«', '»', '"', '\'', ':', '-', '!', '?', ' '};
    private static final String MSG_FOR_KEY = "Default key value (%d) applied.\n\n";

    private final int alphabetLength = ALPHABET.length;
    private final Scanner console = new Scanner(System.in);
    private int key = 3;

    public CaesarCypher() {
        System.out.println(".:: Caesar cypher game ::.\n");
    }

    public void setKey() {
        int maxLimit = alphabetLength * 2 + 7;
        System.out.printf("\nEnter the key (0 - %d): ", maxLimit);
        if (console.hasNextInt()) {
            int tmpKey = console.nextInt();
            if (tmpKey < 0 || tmpKey > maxLimit) {
                System.out.printf(MSG_FOR_KEY, key);
            } else {
                key = tmpKey;
            }
        } else {
            System.out.printf(MSG_FOR_KEY, key);
        }
    }

    private Integer getCharacterIndex(char character) {
        for (int i = 0; i < alphabetLength; i++) {
            if (ALPHABET[i] == character) {
                return i;
            }
        }

        // Skip a character if it is not in the alphabet.
        return null;
    }

    private Character encryptCharacter(char character) {
        Integer characterIndex = getCharacterIndex(character);
        if (characterIndex == null) {
            return null;
        } else {
            int indexOfEncryptedCharacter = (characterIndex + key) % alphabetLength;
            return ALPHABET[indexOfEncryptedCharacter];
        }
    }

    private String encryptText(String text) {
        char[] characters = text.toCharArray();
        StringBuilder encryptedText = new StringBuilder();
        for (char character : characters) {
            Character encryptedCharacter = encryptCharacter(character);
            if (encryptedCharacter == null) {
                continue;
            }
            encryptedText.append(encryptedCharacter);
        }

        return encryptedText.toString();
    }

    public List<String> encryptFileContent(List<String> fileContent) {
        List<String> encryptedFileContent = new ArrayList<>();
        for (String line : fileContent) {
            String encryptedText = encryptText(line);
            encryptedFileContent.add(encryptedText);
        }

        return encryptedFileContent;
    }

    private int getEncryptedCharacterIndex(char encryptedCharacter) {
        for (int i = 0; i < alphabetLength; i++) {
            if (ALPHABET[i] == encryptedCharacter) {
                return i;
            }
        }

        throw new RuntimeException(String.format("There is no such character '%s' in the alphabet.", encryptedCharacter));
    }

    private char decryptCharacter(char encryptedCharacter) {
        int encryptedCharacterIndex = getEncryptedCharacterIndex(encryptedCharacter);
        int characterIndex = (encryptedCharacterIndex - key) % alphabetLength;
        if (characterIndex < 0) {
            return ALPHABET[alphabetLength + characterIndex];
        } else {
            return ALPHABET[characterIndex];
        }
    }

    private String decryptText(String encryptedText) {
        char[] encryptedCharacters = encryptedText.toCharArray();
        StringBuilder text = new StringBuilder();
        for (char encryptedCharacter : encryptedCharacters) {
            char character = decryptCharacter(encryptedCharacter);
            text.append(character);
        }

        return text.toString();
    }

    public List<String> decryptFileContent(List<String> encryptedFileContent) {
        List<String> fileContent = new ArrayList<>();
        for (String line : encryptedFileContent) {
            String decryptedText = decryptText(line);
            fileContent.add(decryptedText);
        }

        return fileContent;
    }
}
