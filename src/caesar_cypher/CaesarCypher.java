package caesar_cypher;

public class CaesarCypher {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м',
            'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.',
            ',', '«', '»', '"', '\'', ':', '-', '!', '?', ' '};

    private final int alphabetLength = ALPHABET.length;
    private int key;

    public CaesarCypher() {
        System.out.println(".:: Caesar cypher game ::.\n");
    }

    public void setKey(int key) {
        this.key = key;
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

    public String cipherText(String text) {
        char[] chars = text.toCharArray();
        String cipherText = "";
        for (char aChar : chars) {
            Character cipherCharacter = cipherCharacter(aChar);
            if (cipherCharacter == null) {
                continue;
            }
            cipherText += cipherCharacter;
        }

        return cipherText;
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

    public String deCipherText(String cipherText) {
        char[] cipherChars = cipherText.toCharArray();
        String text = "";
        for (char cipherChar : cipherChars) {
            char character = deCipherCharacter(cipherChar);
            text += character;
        }

        return text;
    }
}
