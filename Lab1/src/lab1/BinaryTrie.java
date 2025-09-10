package lab1;

public class BinaryTrie {
    DynamicArray array;
    Stack stack;
    TreeNode root;
    int size;
    int height;

    private BinaryTrie() {
        this.array = new DynamicArray();
        this.stack = new Stack();
        this.root = new TreeNode(null, null, null);
        this.size = 0;
        this.height = 4;
    }

    public static BinaryTrie newarray() {
        BinaryTrie newArray = new BinaryTrie();
        return newArray;
    }

    public static BinaryTrie set(BinaryTrie trie, int index, int value) {

        int i = trie.height - 1;
        if (index >= 1 << i) {
            return null;
        }

        System.out.println("Dynamic Array------------------------------------");
        TreeNode newNode = new TreeNode(value, null, null);
        trie.array.addNode(newNode);
        trie.array.printArray();
        System.out.println();

        System.out.println("Binary Trie------------------------------------");
        TreeNode currentNode = trie.root;
        int directionBit;
        i--;

        while (i >= 0) {

            directionBit = (index & (1 << i)) >> i;

            if (directionBit == 1) {
                System.out.print("1 ");
                if (currentNode.right == null) {
                    currentNode.right = new TreeNode(null, null, null);
                }
                currentNode = currentNode.right;
            } else {
                System.out.print("0 ");
                if (currentNode.left == null) {
                    currentNode.left = new TreeNode(null, null, null);
                }
                currentNode = currentNode.left;
            }
            i--;

        }

        currentNode.value = value;
        trie.size++;

        System.out.println();
        System.out.println();

        return trie;
    }

    public static int get(BinaryTrie trie, int index) {

        int i = trie.height - 1;
        if (index >= 1 << (trie.height - 1)) {
            return 0;
        }

        TreeNode currentNode = trie.root;
        int directionBit;

        i--;
        while (i >= 0) {
            directionBit = (index & (1 << i)) >> i;

            if (directionBit == 1) {
                System.out.print("1 ");
                if (currentNode.right == null) {
                    System.out.println("Null");

                    return 0;
                }
                currentNode = currentNode.right;
            } else {
                System.out.print("0 ");
                if (currentNode.left == null) {
                    System.out.println("Null");

                    return 0;
                }
                currentNode = currentNode.left;
            }
            System.out.println(currentNode.value + "\n");

            i--;
        }

        System.out.println(currentNode.value + "\n");

        return currentNode.value;
    }

    public static BinaryTrie unset(BinaryTrie trie) {
        return trie;
    }
}
