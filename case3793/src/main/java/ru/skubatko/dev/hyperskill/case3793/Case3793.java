package ru.skubatko.dev.hyperskill.case3793;

public class Case3793 {

    static int calcEquation(String s) {
        String[] eqParts = s.split(" = ");
        String[] leftPart = new String[0];
        String[] rightPart = new String[0];
        if (eqParts[0].contains("x")) {
            leftPart = eqParts[0].split("\\s");
            rightPart = eqParts[1].split("\\s");
        } else {
            rightPart = eqParts[0].split("\\s");
            leftPart = eqParts[1].split("\\s");
        }

        int result = 0;
        if (leftPart.length > 1) {
            if (leftPart[0].equals("x")) {
                if (leftPart[1].equals("+")) {
                    result = Integer.valueOf(rightPart[0]) - Integer.valueOf(leftPart[2]);
                } else {
                    result = Integer.valueOf(rightPart[0]) + Integer.valueOf(leftPart[2]);
                }
            } else {
                if (leftPart[1].equals("+")) {
                    result = Integer.valueOf(rightPart[0]) - Integer.valueOf(leftPart[0]);
                } else {
                    result = Integer.valueOf(leftPart[0]) - Integer.valueOf(rightPart[0]);
                }
            }
        } else {
            if (rightPart[1].equals("+")) {
                result = Integer.valueOf(rightPart[0]) + Integer.valueOf(rightPart[2]);
            } else {
                result = Integer.valueOf(rightPart[0]) - Integer.valueOf(rightPart[2]);
            }
        }

        return result;
    }
}
