package caesar_cypher;

public class Main {

    public static void main(String[] args) {

        CaesarCypher caesarCypher = new CaesarCypher();

        caesarCypher.setKey(3);
        String text = "Чем хорошим сегодня себя занимаешь?";
        System.out.println("Original text = " + text);

        String cipherText = caesarCypher.cipherText(text);
        System.out.println("cipherText = " + cipherText);

        String deCipherText = caesarCypher.deCipherText(cipherText);
        System.out.println("deCipherText = " + deCipherText);

        caesarCypher.deCipherText("попробуй-ка эТо!");
    }
}
