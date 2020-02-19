package ru.skubatko.dev.hyperskill.case5722.case8296;

import java.util.NoSuchElementException;
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
            switch (command) {
                case "r":
                    list.removeRight(sc.nextInt());
                    break;
                case "l":
                    list.removeLeft(sc.nextInt());
                    break;
                default:
                    break;
            }
        }

        System.out.println(list);
    }

    static class DoublyCircularLinkedList<E> {
        private Node<E> first;
        private Node<E> head;
        private int size;

        DoublyCircularLinkedList() {
            size = 0;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void add(E elem) {
            Node<E> tmp = new Node<>(elem);

            if (head != null) {
                tmp.next = head;
                tmp.prev = head.prev;
                head.prev.next = tmp;
                head.prev = tmp;
            } else {
                head = tmp;
                head.next = head;
                head.prev = head;
                first = head;
            }

            size++;
        }

        void removeLeft(int distance) {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            if (distance < 0) {
                removeRight(Math.abs(distance));
                return;
            }

            for (int i = 0; i < distance % size; i++) {
                head = head.prev;
            }

            remove();
        }

        void removeRight(int distance) {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            if (distance < 0) {
                removeLeft(Math.abs(distance));
                return;
            }

            for (int i = 0; i < distance % size; i++) {
                head = head.next;
            }

            remove();
        }

        private void remove() {
            if (size > 1) {
                if (first == head){
                    first = head.next;
                }

                head = head.next;
                head.prev.prev.next = head;
                head.prev = head.prev.prev;
            } else {
                head = null;
                first = null;
            }

            size--;
        }

        @Override
        public String toString() {
            Node<E> iterator = first;
            StringBuilder result = new StringBuilder();

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

            Node(E value) {
                this.value = value;
                this.next = null;
                this.prev = null;
            }
        }
    }
}
