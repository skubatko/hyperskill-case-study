package ru.skubatko.dev.hyperskill.case5722.case8294;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Case8294 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (int i = 0; i < n; i++) {
            String command = sc.next();
            switch (command) {
                case "addFirst":
                    list.addFirst(sc.nextInt());
                    break;
                case "addLast":
                    list.addLast(sc.nextInt());
                    break;
                case "removeFirst":
                    list.removeFirst();
                    break;
                case "removeLast":
                    list.removeLast();
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

    static class DoublyLinkedList<E> {
        private Node<E> head;
        private Node<E> tail;
        private int size;

        public DoublyLinkedList() {
            size = 0;
        }

        public Node<E> getHead() {
            return head;
        }

        public Node<E> getTail() {
            return tail;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        void addFirst(E elem) {
            Node<E> tmp = new Node<>(elem, head, null);

            if (head != null) {
                head.prev = tmp;
            }

            head = tmp;

            if (tail == null) {
                tail = tmp;
            }
            size++;
        }

        void addLast(E elem) {
            Node<E> tmp = new Node<>(elem, null, tail);

            if (tail != null) {
                tail.next = tmp;
            }

            tail = tmp;

            if (head == null) {
                head = tmp;
            }
            size++;
        }

        void add(E elem, Node<E> curr) {
            if (curr == null) {
                throw new NoSuchElementException();
            }

            Node<E> tmp = new Node<>(elem, curr, null);

            if (curr.prev != null) {
                curr.prev.next = tmp;
                tmp.prev = curr.prev;
                curr.prev = tmp;
            } else {
                curr.prev = tmp;
                tmp.next = curr;
                head = tmp;
            }
            size++;
        }

        public void removeFirst() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
        }

        public void removeLast() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
        }

        public void remove(Node<E> curr) {
            if (curr == null) {
                throw new NoSuchElementException();
            }
            if (curr.prev == null) {
                removeFirst();
                return;
            }
            if (curr.next == null) {
                removeLast();
                return;
            }
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            size--;
        }

        public String toString() {
            Node<E> tmp = head;
            StringBuilder result = new StringBuilder();

            while (tmp != null) {
                result.append(tmp.value).append(" ");
                tmp = tmp.next;
            }

            return result.toString();
        }

        public void reverse() {
            DoublyLinkedList<E> tmp = new DoublyLinkedList<>();

            Node<E> node = this.tail;
            while (node != null) {
                tmp.addLast(node.value);
                node = node.prev;
            }

            this.head = tmp.head;
            this.tail = tmp.tail;
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

            Node<E> getNext() {
                return next;
            }

            Node<E> getPrev() {
                return prev;
            }
        }
    }
}