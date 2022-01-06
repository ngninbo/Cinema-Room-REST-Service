import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        List<String> list;
        String[] text = scanner.nextLine().split("\\s");

        list = Arrays.asList(text);

        System.out.println(list);
    }
}