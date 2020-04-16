package ru.skubatko.dev.hyperskill.case3658;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Case3658Ref {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        char[] html = line.toCharArray();

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        boolean flag = false;

        for (int i = 0; i < html.length; i++) {
            if (html[i] == '>' && !flag) {
                deque.addLast(i + 1);
            } else if (html[i] == '/') {
                flag = true;
                System.out.println(line.substring(deque.pollLast(), i - 1));
            } else if (html[i] == '>' && flag) {
                flag = false;
            }
        }
    }
}
