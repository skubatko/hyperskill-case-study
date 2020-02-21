package ru.skubatko.dev.hyperskill.case5722.case8297;

import java.util.Scanner;

public class Case8297 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = sc.nextInt();

        DoublyLinkedList list = new DoublyLinkedList(s);

        for (int i = 0; i < n; i++) {
            String command = sc.next();
            switch (command) {
                case "split":
                    int index = sc.nextInt();
                    if (0 < index && index < s.length()) {
                        list.split(index);
                    }
                    break;
                case "reverse":
                    list.reverse();
                    break;
                default:
                    break;
            }
        }

        System.out.println(list);
    }

    static class DoublyLinkedList {
        private Node head;
        private Direction direction;

        DoublyLinkedList(String s) {
            s.chars().forEach(this::add);
            direction = Direction.RIGHT;
        }

        void add(int elem) {
            Node tmp = new Node(elem);

            if (head == null) {
                head = tmp;
                head.next = head;
                head.prev = head;
                return;
            }

            Node tail = head.prev;
            tmp.next = head;
            tmp.prev = tail;
            tail.next = tmp;
            head.prev = tmp;
        }

        void split(int index) {
            for (int i = 0; i < index; i++) {
                head = direction == Direction.RIGHT ? head.next : head.prev;
            }
        }

        void reverse() {
            head = direction == Direction.RIGHT ? head.prev : head.next;
            direction = direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();

            Node iterator = head;
            do {
                result.append((char) iterator.value);
                iterator = direction == Direction.RIGHT ? iterator.next : iterator.prev;
            } while (iterator != head);

            return result.toString();
        }

        static class Node {
            private int value;
            private Node next;
            private Node prev;

            Node(int element) {
                this.value = element;
            }
        }

        enum Direction {
            RIGHT, LEFT;
        }
    }
}
