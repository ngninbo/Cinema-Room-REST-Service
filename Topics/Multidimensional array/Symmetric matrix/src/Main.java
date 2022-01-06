import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n <= 10) {
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
            System.out.println(isSymmetricMatrix(matrix) ? "YES" : "NO");
        }

        int[] a = {4, 0, 9, 2, 1};
        int[] b = {6, 3, 2, 9, 0};

        for (int i = 0; i < a.length; i = i + 1) {
            if (i % 2 == 0) {
                a[i] -= b[i];
            } else {
                b[i] -= a[i];
            }
        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    private static boolean isSymmetricMatrix(int[][] matrix) {

        for (int start = 0; start < matrix[0].length; start++) {
            for (int i = 1; i + start < matrix[0].length; i++) {
                if (matrix[start + i][start] != matrix[start][start + i]) {
                    return false;
                }
            }
        }

        return true;
    }
}