package ru.skubatko.dev.hyperskill.case3791;

import java.util.Scanner;

public class Case3791 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(proceed(sc.nextLine()));
    }

    static int proceed(String s){
        String vowels = "aeiouy";

        char[] input = s.toCharArray();
        boolean isCountingVowel = true;
        int counter = 0;
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            if (vowels.contains(String.valueOf(input[i]))) {
                if (isCountingVowel) {
                    counter++;
                } else {
                    result += counter < 3 ? 0 : (counter - 1) / 2;
                    isCountingVowel = true;
                    counter = 1;
                }
            } else {
                if (isCountingVowel) {
                    result += counter < 3 ? 0 : (counter - 1) / 2;
                    isCountingVowel = false;
                    counter = 1;
                } else {
                    counter++;
                }
            }
        }

        result += counter < 3 ? 0 : (counter - 1) / 2;

        return result;
    }
}
