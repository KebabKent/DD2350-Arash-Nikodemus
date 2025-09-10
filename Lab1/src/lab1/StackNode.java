package lab1;

public class StackNode {
    TreeNode treeNode;
    StackNode previous;

    public StackNode(TreeNode treeNode, StackNode previous) {
        this.treeNode = treeNode;
        this.previous = previous;
    }
}
