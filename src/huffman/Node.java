package huffman;
public class Node implements Comparable<Node>{
    private final int frequency;
    private Node leftNode;
    private Node rightNode;

    public Node(Node leftNode, Node rightNode) {
        frequency = leftNode.getFrequency() + rightNode.getFrequency();
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Node(int frequency) {
        this.frequency = frequency;
    }

    public int compareTo(Node o) {
        return frequency-o.getFrequency();
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }
}
