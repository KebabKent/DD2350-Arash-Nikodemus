package lab1;

public class TreeNode {
    public Integer value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
        this.value = null;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int rootSize) {
        this.value = rootSize;
        this.left = null;
        this.right = null;
    }

    public TreeNode(Integer value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
