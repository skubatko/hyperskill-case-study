package ru.skubatko.dev.hyperskill.case5723;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Case5723 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String s = sc.nextLine();

        int t = sc.nextInt();

        int[][] pairs = new int[t][4];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < 4; j++) {
                pairs[i][j] = sc.nextInt();
            }
        }

        System.out.println(proceed(s, t, pairs));
    }

    static int proceed(String s, int t, int[][] pairs) {
        int radix = radix();
        long prime = prime();
        int result = 0;

        long[] prefixHashArray = prefixHashArray(s);

//        System.out.println(Arrays.toString(prefixHashArray));

        for (int i = 0; i < t; i++) {
            int posI = pairs[i][0];
            int posJ = pairs[i][1];
            int posK = pairs[i][2];
            int posM = pairs[i][3];
            long hashLeft = prefixHash(posI, posJ, prefixHashArray);
            long hashRight = prefixHash(posK, posM, prefixHashArray);

            long a = 1;
            int power = posK - posI;
            for (int counter = 0; counter < power; counter++) {
                a = a * radix % prime;
            }

//            log(s, i, posI, posJ, posK, posM, hashLeft, hashRight, a);

            if (hashLeft * a % prime == hashRight) {
                result++;
            }
        }
        return result;
    }

    private static void log(String s, int i, int posI, int posJ, int posK, int posM, long hashLeft, long hashRight, long a) {
        long prime = prime();
        long hashLeftNative = hash(s.substring(posI, posJ));
        long hashRightNative = hash(s.substring(posK, posM));
        System.out.println("===================");
        System.out.println("line: " + (i + 1));
        System.out.println("left: " + s.substring(posI, posJ));
        System.out.println("right: " + s.substring(posK, posM));
        System.out.println("hashLeft: " + hashLeft);
        System.out.println("hashRight: " + hashRight);
        System.out.println("a: " + a);
        System.out.println("hashLeft * a: " + hashLeft * a);
        System.out.println("hashLeft * a % prime: " + hashLeft * a % prime);
        System.out.println("hashLeftNative: " + hashLeftNative);
        System.out.println("hashRightNative: " + hashRightNative);
        System.out.println("hashLeft % hashLeftNative: " + hashLeft % hashLeftNative);
        System.out.println("hashRight % hashRightNative: " + hashRight % hashRightNative);
        System.out.println("hashRight % hashLeft: " + hashRight % hashLeft);
        System.out.println("hashRightNative % hashLeftNative: " + hashRightNative % hashLeftNative);
    }

    static long[] prefixHashArray(String s) {
        long[] prefixHashArray = new long[s.length()];
        long prime = prime();
        int radix = radix();
        long hash = 0;
        long power = 1;
        for (int i = 0; i < s.length(); i++) {
            hash += s.charAt(i) * power;
            hash %= prime;
            power = power * radix % prime;
            prefixHashArray[i] = hash;
        }
        return prefixHashArray;
    }

    static long hash(String s) {
        long hash = 0;
        long power = 1;
        long prime = prime();
        int radix = radix();
        for (int i = 0; i < s.length(); i++) {
            hash += s.charAt(i) * power;
            hash %= prime;
            power = power * radix % prime;
        }
        return hash;
    }

    static long prefixHash(int left, int right, long[] prefixHashArray) {
        long prime = prime();
        if (left == 0) {
            return prefixHashArray[right - 1];
        }
        long leftHash = prefixHashArray[left - 1];
        long rightHash = prefixHashArray[right - 1];
        return (rightHash - leftHash + prime) % prime;
    }

    static void buildPrefixHashArrayOnFly() {
        long prime = prime();
        int radix = radix();
        try (InputStreamReader isr = new InputStreamReader(System.in)) {
            List<Long> list = new ArrayList<>();
            long hash = 0;
            long power = 1;
            int c;
            while ((c = isr.read()) != '\n') {
                hash += (char) c * power;
                hash %= prime;
                power = power * radix % prime;
                list.add(hash);
            }
//            prefixHashArray = list.toArray(Long[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int radix() {
        return 53;
    }

    static long prime() {
        return 1_000_000_000L + 9L;
    }
}
