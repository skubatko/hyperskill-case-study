package ru.skubatko.dev.hyperskill.case5718;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Case5718 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine();
        String text = sc.nextLine();

        List<Integer> result = proceed(pattern, text);

        System.out.println(result.size());
        System.out.println(
                result.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    static List<Integer> proceed(String pattern, String text) {
        List<Integer> result = new ArrayList<>();

        String currentText = text;
        int patternLength = pattern.length();
        int lastFoundPosition = -patternLength;
        while (currentText.length() >= patternLength) {
            int foundPosition = currentText.indexOf(pattern);
            if (foundPosition < 0) {
                break;
            }
            lastFoundPosition = lastFoundPosition + patternLength + foundPosition;
            result.add(lastFoundPosition);
            currentText = currentText.substring(foundPosition + patternLength);
        }

        return result;
    }
}
