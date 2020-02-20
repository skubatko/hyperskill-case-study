package ru.skubatko.dev.hyperskill.case5722.case8296;

import java.util.Scanner;

public class Case8296 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        for (int i = 0; i < k; i++) {
            String command = sc.next();
            int distance = sc.nextInt();

            switch (command) {
                case "r":
                    list.removeRight(distance);
                    break;
                case "l":
                    list.removeLeft(distance);
                    break;
                default:
                    break;
            }
        }

        System.out.println(list);
    }

    static class DoublyCircularLinkedList<E> {
        private Node<E> head;
        private Node<E> tail;
        private Node<E> curr;
        int size;

        DoublyCircularLinkedList() {
            size = 0;
        }

        void add(E elem) {
            Node<E> tmp = new Node<>(elem, head, tail);

            if (isEmpty()) {
                addFirst(tmp);
            } else {
                head.prev = tmp;
                tail.next = tmp;
                tail = tmp;
            }

            size++;
        }

        private void addFirst(Node<E> node) {
            head = node;
            tail = node;
            head.next = tail;
            tail.prev = head;
            curr = head;
        }

        void removeLeft(int distance) {
            System.out.println("<<<<<< removeLeft: " + distance);
            System.out.println("BEFORE:");
            display();

            for (int i = 0; i < distance; i++) {
                curr = curr.prev;
            }
            remove();

            if (!isEmpty()) {
                curr = curr.prev;
            }

            System.out.println("AFTER:");
            display();
            System.out.println();
        }

        void removeRight(int distance) {
            System.out.println(">>>>>> removeRight: " + distance);
            System.out.println("BEFORE:");
            display();

            for (int i = 0; i < distance; i++) {
                curr = curr.next;
            }
            remove();

            if (!isEmpty()) {
                curr = curr.next;
            }

            System.out.println("AFTER:");
            display();
            System.out.println();
        }

        private void remove() {
            if (isEmpty()) {
                return;
            }

            if (size == 1) {
                removeHead();
                return;
            }

            if (curr == head) {
                removeFirst();
                return;
            }
            if (curr == tail) {
                removeLast();
                return;
            }

            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            size--;
        }

        private void removeHead() {
            head = null;
            tail = null;
            curr = null;
            size--;
        }

        private void removeFirst() {
            head = head.next;
            head.prev = tail;
            tail.next = head;
            size--;
        }

        private void removeLast() {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
            size--;
        }

        private boolean isEmpty() {
            return size == 0;
        }

        void display() {
            if (!isEmpty()) {
                System.out.println(String.format("head=%s, tail=%s, curr=%s", head.value, tail.value, curr.value));
            } else {
                System.out.println(String.format("head=%s, tail=%s, curr=%s", null, null, null));
            }
            System.out.println(this.toString());
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            Node<E> iterator = head;

            for (int i = 0; i < size; i++) {
                result.append(iterator.value).append(" ");
                iterator = iterator.next;
            }

            return result.toString();
        }

        static class Node<E> {
            private E value;
            private Node<E> next;
            private Node<E> prev;

            Node(E element, Node<E> next, Node<E> prev) {
                this.value = element;
                this.next = next;
                this.prev = prev;
            }
        }
    }
}
