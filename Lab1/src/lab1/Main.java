package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // System.out.println(((0 & (1 << 2)) >> 2) + " " + ((0 & (1 << 1)) >> 1) + " "
        // + ((0 & (1 << 0)) >> 0));
        // System.out.println(((1 & (1 << 2)) >> 2) + " " + ((1 & (1 << 1)) >> 1) + " "
        // + ((1 & (1 << 0)) >> 0));
        // System.out.println(((2 & (1 << 2)) >> 2) + " " + ((2 & (1 << 1)) >> 1) + " "
        // + ((2 & (1 << 0)) >> 0));
        // System.out.println(((3 & (1 << 2)) >> 2) + " " + ((3 & (1 << 1)) >> 1) + " "
        // + ((3 & (1 << 0)) >> 0));
        // System.out.println(((4 & (1 << 2)) >> 2) + " " + ((4 & (1 << 1)) >> 1) + " "
        // + ((4 & (1 << 0)) >> 0));
        // System.out.println(((5 & (1 << 2)) >> 2) + " " + ((5 & (1 << 1)) >> 1) + " "
        // + ((5 & (1 << 0)) >> 0));
        // System.out.println(((6 & (1 << 2)) >> 2) + " " + ((6 & (1 << 1)) >> 1) + " "
        // + ((6 & (1 << 0)) >> 0));
        // System.out.println(((7 & (1 << 2)) >> 2) + " " + ((7 & (1 << 1)) >> 1) + " "
        // + ((7 & (1 << 0)) >> 0));
        // System.out.println(((8 & (1 << 2)) >> 2) + " " + ((8 & (1 << 1)) >> 1) + " "
        // + ((8 & (1 << 0)) >> 0));

        Scanner scanner = new Scanner(System.in);

        BinaryTrie trie = BinaryTrie.newarray();
        String input;
        String[] inputs;
        String command;
        int index;
        int value;

        while (true) {
            input = scanner.nextLine();
            inputs = input.split(" ");
            command = inputs[0];

            if (command.equals("get")) {
                index = Integer.parseInt(inputs[1]);

                System.out.println("Main-------Get " + index);
                System.out.println("Value at index " + index + ": " + BinaryTrie.get(trie, index));

            } else if (command.equals("set")) {
                index = Integer.parseInt(inputs[1]);
                value = Integer.parseInt(inputs[2]);

                System.out.println("Main-------Set " + index + " to " + value);
                trie = BinaryTrie.set(trie, index, value);

            } else if (command.equals("unset")) {
                System.out.println("Main-------Unset");
                trie = BinaryTrie.unset(trie);

            } else if (command.equals("q")) {
                break;
            }
        }

        scanner.close();
    }
}