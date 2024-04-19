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
        System.out.printf("\nEnter the key (0 - %d): ", alphabetLength * 2 + 7);
        if (console.hasNextInt()) {
            int tmpKey = console.nextInt();
            if (tmpKey < 0) {
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

    private Character cipherCharacter(char character) {
        Integer charIndex = getCharacterIndex(character);
        if (charIndex == null) {
            return null;
        } else {
            int cipherIndex = (charIndex + key) % alphabetLength;
            return ALPHABET[cipherIndex];
        }
    }

    private String cipherText(String text) {
        char[] chars = text.toCharArray();
        StringBuilder cipherText = new StringBuilder();
        for (char aChar : chars) {
            Character cipherCharacter = cipherCharacter(aChar);
            if (cipherCharacter == null) {
                continue;
            }
            cipherText.append(cipherCharacter);
        }

        return cipherText.toString();
    }

    public List<String> cipherFileContent(List<String> fileContent) {
        List<String> cipherFileContent = new ArrayList<>();
        for (String line : fileContent) {
            String cipherText = cipherText(line);
            cipherFileContent.add(cipherText);
        }

        return cipherFileContent;
    }

    private int getCipherCharacterIndex(char cipherCharacter) {
        for (int i = 0; i < alphabetLength; i++) {
            if (ALPHABET[i] == cipherCharacter) {
                return i;
            }
        }

        throw new RuntimeException(String.format("There is no such character '%s' in the alphabet.", cipherCharacter));
    }

    private char deCipherCharacter(char cipherCharacter) {
        int cipherIndex = getCipherCharacterIndex(cipherCharacter);
        int charIndex = (cipherIndex - key) % alphabetLength;
        if (charIndex < 0) {
            return ALPHABET[alphabetLength + charIndex];
        } else {
            return ALPHABET[charIndex];
        }
    }

    private String deCipherText(String cipherText) {
        char[] cipherChars = cipherText.toCharArray();
        StringBuilder text = new StringBuilder();
        for (char cipherChar : cipherChars) {
            char character = deCipherCharacter(cipherChar);
            text.append(character);
        }

        return text.toString();
    }

    public List<String> deCipherFileContent(List<String> cipherFileContent) {
        List<String> fileContent = new ArrayList<>();
        for (String line : cipherFileContent) {
            String deCipherText = deCipherText(line);
            fileContent.add(deCipherText);
        }

        return fileContent;
    }
}
