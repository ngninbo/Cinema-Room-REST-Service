import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();
        String[] input = scanner.nextLine().split(" ");
        for (String s : input) {
            list.add(Integer.parseInt(s));
        }

        filterList(list);
    }

    private static void filterList(List<Integer> list) {

        List<Integer> filteredList = new ArrayList<>();

        for (int i = list.size() - 1; i >= 0 ; i--) {
            if (i % 2 != 0) {
                filteredList.add(list.get(i));
            }
        }

        for (Integer integer : filteredList) {
            System.out.print(integer + " ");
        }
    }
}