package huffman;

public class Leaf extends Node{
    private final char character;

    public Leaf(int frequency, char character) {
        super(frequency);
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
