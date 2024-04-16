package caesar_cypher;

public class CaesarCypher {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м',
            'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.',
            ',', '«', '»', '"', '\'', ':', '-', '!', '?', ' '};

    private int key;

    public CaesarCypher() {
        System.out.println(".:: Caesar cypher game ::.");
    }

    public void setKey(int key) {
        this.key = key;
    }

    private Integer getCharIndex(char symbol) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == symbol) {
                return i;
            }
        }

        // Skip a character if it is not in the alphabet
        // throw new RuntimeException(String.format("There is no such symbol '%s' in the alphabet.", symbol));
        return null;
    }

    private Character getCipherSymbol(char symbol) {
        Integer charIndex = getCharIndex(symbol);
        if (charIndex == null) {
            return null;
        } else {
            int cypherIndex = (charIndex + key) % ALPHABET.length;
            return ALPHABET[cypherIndex];
        }
    }

    public String getCipherText(String text) {
        char[] chars = text.toCharArray();
        String cipherText = "";
        for (int i = 0; i < chars.length; i++) {
            Character cipherSymbol = getCipherSymbol(chars[i]);
            if (cipherSymbol == null) {
                continue;
            }
            cipherText += cipherSymbol;
        }

        return cipherText;
    }
}
