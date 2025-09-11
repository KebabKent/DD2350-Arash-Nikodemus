package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BinaryTrie trie = BinaryTrie.newarray();
        String input;
        String[] inputs;
        String command;
        int index;
        int value;

        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            inputs = input.split(" ");
            command = inputs[0];

            if (command.equals("get")) {
                index = Integer.parseInt(inputs[1]);

                System.out.println(BinaryTrie.get(trie, index));

            } else if (command.equals("set")) {
                index = Integer.parseInt(inputs[1]);
                value = Integer.parseInt(inputs[2]);

                trie = BinaryTrie.set(trie, index, value);

            } else if (command.equals("unset")) {
                BinaryTrie.unset(trie);
            }

        }
        scanner.close();

    }
}