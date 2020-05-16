package ru.skubatko.dev.hyperskill.case9637;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Case9637 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int k = sc.nextInt();
        sc.nextLine();

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Pair pair = new Pair();
            pair.i = sc.nextInt();
            pair.j = sc.nextInt();
            pairs.add(pair);
        }

        Result result = calcStringPolynomialHash(s, pairs);

        String delimiter = " ";
        System.out.println(result.prefixesHashList.stream()
                                   .map(String::valueOf).collect(Collectors.joining(delimiter)));
        System.out.println(result.givenHashes.stream()
                                   .map(String::valueOf).collect(Collectors.joining(delimiter)));
    }

    static Result calcStringPolynomialHash(String s, List<Pair> pairs) {
        int a = 53;
        long m = 1_000_000_000 + 9;

        Result result = new Result();

        List<Long> prefixesHashList = new ArrayList<>();
        long pow = 1;
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash += charToLong(s.charAt(i)) * pow;
            hash %= m;

            prefixesHashList.add(hash);

            pow = pow * a % m;
        }
        result.prefixesHashList = prefixesHashList;

        List<Long> givenHashes = new ArrayList<>();
        for (Pair pair : pairs) {
            Long iHash = pair.i == 0 ? 0L : prefixesHashList.get(pair.i - 1);
            long givenHash = prefixesHashList.get(pair.j - 1) - iHash;
            givenHash += m;
            givenHash %= m;
            givenHashes.add(givenHash);
        }
        result.givenHashes = givenHashes;

        return result;
    }

    private static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }

    static class Pair {
        int i;
        int j;

        public Pair() {
        }

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static class Result {
        List<Long> prefixesHashList;
        List<Long> givenHashes;
    }
}
