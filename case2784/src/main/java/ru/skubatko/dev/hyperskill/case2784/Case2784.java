package ru.skubatko.dev.hyperskill.case2784;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Case2784 {

    // can help https://www.geeksforgeeks.org/tracking-current-maximum-element-in-a-stack/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MaxStack maxStack = new MaxStack();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            switch (sc.next()) {
                case "push":
                    maxStack.push(sc.nextInt());
                    break;
                case "pop":
                    maxStack.pop();
                    break;
                case "max":
                    System.out.println(maxStack.max());
                    break;
                default:
                    break;
            }
        }
    }

    static class MaxStack {
        private Deque<Integer> stack = new ArrayDeque<>();
        private Deque<Integer> max = new ArrayDeque<>();

        public void push(Integer item) {
            stack.push(item);

            if (max.isEmpty()) {
                max.push(item);
            } else {
                max.push(Math.max(item, max.peek()));
            }
        }

        public Integer pop() {
            if (stack.isEmpty()) {
                return 0;
            }
            max.pop();
            return stack.pop();
        }

        public Integer max() {
            if (max.isEmpty()) {
                return 0;
            }

            return max.peek();
        }
    }
}
