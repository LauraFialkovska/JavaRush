package caesar_cypher;

public class Main {

    public static void main(String[] args) {

        CaesarCypher caesarCypher = new CaesarCypher();

        caesarCypher.setKey(3);
        String text = "fд?:s";
        // String text = "frs";

        String cipherText = caesarCypher.getCipherText(text);
        System.out.println("cipherText = " + cipherText);
    }
}
