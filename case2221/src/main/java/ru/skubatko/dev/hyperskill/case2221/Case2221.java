package ru.skubatko.dev.hyperskill.case2221;

public class Case2221 {

    static int runSnail(int height, int day, int night) {
        return (height - night - 1) / (day - night) + 1;
    }
}
