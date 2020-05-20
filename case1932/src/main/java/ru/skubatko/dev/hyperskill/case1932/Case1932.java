package ru.skubatko.dev.hyperskill.case1932;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Case1932 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<List<Integer>> matrix = new ArrayList<>();

        String buffer = sc.nextLine();
        while (!(buffer.equals("end"))) {
            matrix.add(Arrays.stream(buffer.split("\\s+")).map(Integer::valueOf).collect(Collectors.toList()));
            buffer = sc.nextLine();
        }

        int[][] neighborsSum = neighborsSum(matrix);

        for (int i = 0; i < neighborsSum.length; i++) {
            for (int j = 0; j < neighborsSum[0].length; j++) {
                System.out.print(neighborsSum[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] neighborsSum(List<List<Integer>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();

        int[][] arr = new int[rows + 2][cols + 2];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                arr[row + 1][col + 1] = matrix.get(row).get(col);
            }
        }

        System.arraycopy(arr[1], 1, arr[rows + 1], 1, cols);
        System.arraycopy(arr[rows], 1, arr[0], 1, cols);
        for (int i = 1; i < rows + 1; i++) {
            arr[i][0] = arr[i][cols];
            arr[i][cols + 1] = arr[i][1];
        }

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};
        int[][] result = new int[rows][cols];
        for (int row = 1; row < rows + 1; row++) {
            for (int col = 1; col < cols + 1; col++) {
                int sum = 0;
                for (int i = 0; i < 4; i++) {
                    sum += arr[row + dRow[i]][col + dCol[i]];
                }
                result[row - 1][col - 1] = sum;
            }
        }

        return result;
    }
}
