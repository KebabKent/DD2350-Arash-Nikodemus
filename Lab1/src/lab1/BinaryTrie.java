package lab1;

public class BinaryTrie {
    // DynamicArray array;
    Stack stack;
    TreeNode root;
    int height;

    private BinaryTrie() {
        this.stack = new Stack();
        this.root = new TreeNode(0); // The root value is the size of the trie.
        this.height = 32; // 2^(height - 1) = number of leaves. Last index is number of leaves - 1.
    }

    public static BinaryTrie newarray() {
        BinaryTrie newArray = new BinaryTrie();
        return newArray;
    }

    public static BinaryTrie set(BinaryTrie trie, int index, int value) {

        int i = trie.height - 1;
        if ((index / 2) >= (1 << (i - 1))) {// only issue is if i-1 is not i-1 and instead i it goes out of bound and
                                            // back to 1
            return trie;
        }

        trie.stack.push(trie.root);
        TreeNode newRoot = new TreeNode(trie.root.value);

        TreeNode currentNode = trie.root;
        TreeNode replacementNode = newRoot;
        int directionBit;
        i--; // This is to push the 1 from 001 to 100 and then back again. We missed this in
             // the theory questions where we took h - 1 instead of h - 2.

        while (i >= 0) {
            directionBit = (index & (1 << i)) >> i;

            if (directionBit == 1) {
                // System.out.print("1 ");
                if (currentNode != null) {
                    replacementNode.left = currentNode.left;
                    currentNode = currentNode.right;
                }

                replacementNode.right = new TreeNode();
                replacementNode = replacementNode.right;
            } else {
                // System.out.print("0 ");

                if (currentNode != null) {
                    replacementNode.right = currentNode.right;
                    currentNode = currentNode.left;
                }

                replacementNode.left = new TreeNode();
                replacementNode = replacementNode.left;
            }
            i--;

        }

        if (currentNode == null) {
            newRoot.value = trie.root.value + 1;
        }

        replacementNode.value = value;
        trie.root = newRoot;

        // System.out.println();

        // System.out.println("Size " + trie.root.value);

        // System.out.println();

        return trie;
    }

    public static int get(BinaryTrie trie, int index) {

        int i = trie.height - 1;
        if ((index / 2) >= (1 << (i - 1))) {// only issue is if i-1 is not i-1 and instead i it goes out of bound and
                                            // back to 1
            // System.out.println("Input Error:" + " your index " + index + " is larger than
            // " + (1 << i));
            return 0;
        }

        TreeNode currentNode = trie.root;
        int directionBit;

        i--;
        while (i >= 0) {
            directionBit = (index & (1 << i)) >> i;

            if (directionBit == 1) {
                // System.out.print("1 ");
                if (currentNode.right == null) {
                    // System.out.println("Null");

                    return 0;
                }
                currentNode = currentNode.right;
            } else {
                // System.out.print("0 ");
                if (currentNode.left == null) {
                    // System.out.println("Null");

                    return 0;
                }
                currentNode = currentNode.left;
            }
            i--;
        }

        return currentNode.value;
    }

    public static BinaryTrie unset(BinaryTrie trie) {
        if (trie.stack.size > 0) {
            trie.root = trie.stack.pop();
        }

        return trie;
    }
}
