package huffman;

import java.util.*;

public class Huffman {
    private Node root;
    private final String text;
    private Map<Character,Integer> charFrequencies;
    private final Map<Character,String> huffmanCodes;

    public Huffman(String text){
        this.text = text;
        huffmanCodes = new HashMap<Character, String>();
        fillCharFrequenciesMap();
    }

    private void fillCharFrequenciesMap() {
        charFrequencies = new HashMap<Character, Integer>();
        for(char character : text.toCharArray()){
            charFrequencies.merge(character, 1, Integer::sum);
        }
    }

    public String encode(){
        final Queue<Node> queue = new PriorityQueue<>();
        charFrequencies.forEach((character,frequency) ->
                queue.add(new Leaf(frequency,character)));
        while(queue.size() > 1){
            queue.add(new Node(queue.poll(),queue.poll()));
        }
        root = queue.poll();
        generateHuffmanCodes(root,"");
        return getEncodedText();
    }

    private void generateHuffmanCodes(Node node, String code) {
        if(node instanceof Leaf){
            huffmanCodes.put(((Leaf)node).getCharacter(),code);
            return;
        }
        generateHuffmanCodes(node.getLeftNode(),code.concat("0"));
        generateHuffmanCodes(node.getRightNode(),code.concat("1"));
    }

    private String getEncodedText() {
        StringBuilder sb = new StringBuilder();
        for(char character : text.toCharArray()){
            sb.append(huffmanCodes.get(character));
        }
        return sb.toString();
    }

    public String decode(String encodedText){
        StringBuilder sb = new StringBuilder();
        Node current = root;
        for(char character : encodedText.toCharArray()){
            if(character == '0'){
                current = current.getLeftNode();
            }else{
                current = current.getRightNode();
            }

            if(current instanceof Leaf){
                sb.append(((Leaf)current).getCharacter());
                current = root;
            }
        }
        return sb.toString();
    }


}
