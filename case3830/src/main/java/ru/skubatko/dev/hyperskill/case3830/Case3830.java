package ru.skubatko.dev.hyperskill.case3830;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Case3830 {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int number = scanner.nextInt();
        scanner.nextLine();
        System.out.println(Stream.generate(() -> Arrays.stream(scanner.nextLine().split("\\s+")))
                .limit(number)
                .flatMap(s -> s)
                .map(String::toLowerCase)
                .distinct()
                .count());
    }
}
