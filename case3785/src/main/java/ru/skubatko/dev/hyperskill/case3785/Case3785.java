package ru.skubatko.dev.hyperskill.case3785;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Case3785 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int size = n * n;

        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println(checkSudoku(arr, n) ? "YES" : "NO");
    }

    static boolean checkSudoku(int[][] arr, int n) {
        if (!(checkSmallSquares(arr, n))) {
            return false;
        }

        if (!(checkRows(arr, n))) {
            return false;
        }

        if (!(checkColumns(arr, n))) {
            return false;
        }

        return true;
    }

    private static boolean checkSmallSquares(int[][] arr, int n) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int[][] square = getSmallSquare(row, col, arr, n);
                if (!(checkSmallSquare(square, n))) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int[][] getSmallSquare(int row, int col, int[][] arr, int n) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = arr[row * n + i][col * n + j];
            }
        }
        return result;
    }

    private static boolean checkSmallSquare(int[][] square, int n) {
        List<Integer> numbers = getNumbers(n * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                numbers.remove((Integer) square[i][j]);
            }
        }

        return numbers.isEmpty();
    }

    private static boolean checkRows(int[][] arr, int n) {
        int size = n * n;
        for (int row = 0; row < size; row++) {
            if (!(checkRow(row, arr, n))) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkRow(int row, int[][] arr, int n) {
        int size = n * n;
        List<Integer> numbers = getNumbers(size);
        for (int i = 0; i < size; i++) {
            numbers.remove((Integer) arr[row][i]);
        }

        return numbers.isEmpty();
    }

    private static boolean checkColumns(int[][] arr, int n) {
        int size = n * n;
        for (int col = 0; col < size; col++) {
            if (!(checkColumn(col, arr, n))) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkColumn(int col, int[][] arr, int n) {
        int size = n * n;
        List<Integer> numbers = getNumbers(size);
        for (int i = 0; i < size; i++) {
            numbers.remove((Integer) arr[i][col]);
        }

        return numbers.isEmpty();
    }

    private static List<Integer> getNumbers(int size) {
        return IntStream.range(1, size + 1).boxed().collect(Collectors.toList());
    }
}
