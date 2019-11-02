
package textfindersimon;

/**
 *
 * @author sfv02
 */
public class TreeNode {
    
    private TreeNode left;
    private TreeNode right;
    private String element;
    private Coincidences coincidences;
    
    public TreeNode(String element){
        this.left = null;
        this.right = null;
        this.element = element;
        this.coincidences = new Coincidences();
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Coincidences getCoincidences() {
        return coincidences;
    }

    public void setCoincidences(Coincidences coincidences) {
        this.coincidences = coincidences;
    }
    
    
}
