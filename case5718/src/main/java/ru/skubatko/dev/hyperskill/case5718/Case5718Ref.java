package ru.skubatko.dev.hyperskill.case5718;

import java.util.Scanner;

public class Case5718Ref {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        if (pattern.length() == 0) {
            System.out.println(1);
            System.out.println(0);
        } else if (pattern.length() <= text.length()) {
            int counter = 0;
            String range = "";
            for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                    if (j + 1 == pattern.length()) {
                        range = range + i + " ";
                        counter++;
                        i = i + j;
                    }
                }
            }
            System.out.println(counter);
            if (counter > 0) {
                System.out.println(range);
            }
        } else {
            System.out.println(0);
        }
    }
}
