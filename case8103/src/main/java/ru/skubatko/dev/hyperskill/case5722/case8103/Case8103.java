package ru.skubatko.dev.hyperskill.case5722.case8103;

import java.util.Scanner;

public class Case8103 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double scaling = Double.parseDouble(sc.next());
        double downscaling = Double.parseDouble(sc.next());

        DynamicArray array = new DynamicArray(scaling, downscaling);
        for (int i = 0; i < n; i++) {
            String command = sc.next();
            switch (command) {
                case "add":
                    array.add(sc.nextInt());
                    break;
                case "delete":
                    array.remove(sc.nextInt());
                    break;
                case "count":
                    System.out.println(array.count());
                    break;
                default:
                    break;
            }
        }
    }

    static class DynamicArray {
        private double size;
        private double length;

        private final int DEFAULT_CAPACITY = 2;
        private double scaling;
        private double downscaling;

        public DynamicArray(double scaling, double downscaling) {
            this.scaling = scaling;
            this.downscaling = downscaling;
            this.size = 0;
            this.length = DEFAULT_CAPACITY;
        }

        private void tryIncrease() {
            size += 1;
            if (length < size) {
                length *= scaling;
            }
        }

        private void tryDecrease() {
            size -= 1;
            if (size <= length / downscaling) {
                length /= downscaling;
            }
        }

        public void add(int fraction) {
            for (int i = 0; i < fraction; i++) {
                tryIncrease();
            }
            length = Math.floor(length);
        }

        public void remove(int fraction) {
            for (int i = 0; i < fraction; i++) {
                tryDecrease();
            }
            length = Math.floor(length);
        }

        public int count() {
            return (int) length;
        }
    }
}
