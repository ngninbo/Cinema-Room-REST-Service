import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        List<String> sequence = new ArrayList<>();
        String input;
        do {
            input = scanner.nextLine();
            if (!"0".equals(input)) {
                sequence.add(input);
            }
        } while (!"0".equals(input));

        for (String el : sequence) {
            try {
                int number = Integer.parseInt(el) * 10;
                System.out.println(number);
            } catch (NumberFormatException e) {
                System.out.println("Invalid user input: " + el);
            }
        }
    }
}