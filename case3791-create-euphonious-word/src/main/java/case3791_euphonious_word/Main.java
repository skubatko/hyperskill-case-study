package case3791_euphonious_word;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String vowels = "aeiouy";
        Scanner sc = new Scanner(System.in);
        char[] input = sc.nextLine().toCharArray();
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
        System.out.println(result);
    }

}
