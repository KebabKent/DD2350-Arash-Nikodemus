package lab1;

public class Stack {
    StackNode top;
    int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(TreeNode treeNode) {
        if (this.top != null) {
            StackNode newNode = new StackNode(treeNode, this.top);
            this.top = newNode;
            this.size++;
            return;
        }

        StackNode newNode = new StackNode(treeNode, null);
        this.top = newNode;
        this.size++;
    }

    public TreeNode pop() {
        if (this.top == null) {
            return null;
        } else {
            TreeNode topNode = this.top.treeNode;
            this.top = this.top.previous;
            this.size--;
            return topNode;
        }
    }
}
