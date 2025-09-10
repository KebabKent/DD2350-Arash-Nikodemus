package lab1;

public class DynamicArray {
    private TreeNode[] array;
    private int numberOfElements;

    public DynamicArray() {
        array = new TreeNode[8];
        numberOfElements = 0;
    }

    public void printArray() {
        System.out.print("Array: ");
        for (int i = 0; i < numberOfElements; i++) {
            if (array[i] != null) {
                System.out.print(array[i].value + " ");
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();

        System.out.println("Array length: " + array.length);
        System.out.println("Number of elements: " + numberOfElements);
    }

    public void addNode(TreeNode treeNode) {
        // Add node to end of array

        if (numberOfElements == array.length) {
            resizeUp();
        }
        array[numberOfElements] = treeNode;
        numberOfElements++;
    }

    public void resizeUp() {
        // Resize the array
        TreeNode[] newArray = new TreeNode[array.length * 2];

        for (int i = 0; i < this.numberOfElements; i++) {
            newArray[i] = array[i];
        }

        this.array = newArray;
    }

    public void removeNodeAtIndex(int index) {

        if (array.length > 8) {
            // remove the element function
            this.array[index] = null;
            numberOfElements--;
        }

        if (numberOfElements > 0 && numberOfElements <= (array.length / 4)) {
            resizeDown();
        }
    }

    public void resizeDown() {
        // Resize the array
        TreeNode[] newArray = new TreeNode[array.length / 2];

        int counter = 0;

        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = array[i];
        }

        this.array = newArray;
    }

}
