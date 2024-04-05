public class QuestionNode {
    public QuestionNode yesNode;
    public QuestionNode noNode;
    public String data;

    /**
     * Constructs a leaf node with the specified data.
     * 
     * @param data the data for the leaf node
     * @throws IllegalArgumentException if the data is null
     */
    public QuestionNode(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        this.yesNode = null;
        this.noNode = null;
        this.data = data;
    }

    /**
     * Constructs a non-leaf node with the specified data and child nodes.
     * 
     * @param yesNode the reference to the "yes" child node
     * @param noNode  the reference to the "no" child node
     * @param data    the data for the node
     * @throws IllegalArgumentException if the data is null
     */
    public QuestionNode(QuestionNode yesNode, QuestionNode noNode, String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        this.yesNode = yesNode;
        this.noNode = noNode;
        this.data = data;
    }

    /**
     * Checks if the node is a leaf node (an answer node).
     * 
     * @return true if the node is a leaf node, false otherwise
     */
    public boolean isAnswer() {
        return yesNode == null && noNode == null;
    }

    /**
     * Retrieves the data stored in the node.
     * 
     * @return the data of the node
     */
    public String getData() {
        return data;
    }
}
