import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int A = 53;
    private static final long M = 1_000_000_000 + 9;

    private static long[] prefixHashArray;
    private static String s;
    private static int[][] matrix;

    private static int posI;
    private static int posJ;
    private static int posK;
    private static int posM;

    private static void buildPrefixHashArray() {
        prefixHashArray = new long[s.length()];
        long hash = 0;
        long power = 1;
        for (int i = 0; i < s.length(); i++) {
            hash += charToLong(s.charAt(i)) * power;
            hash %= M;
            power = power * A % M;
            prefixHashArray[i] = hash;
        }
    }

    private static long hash(String subString) {
        long hash = 0;
        long power = 1;
        for (int i = 0; i < subString.length(); i++) {
            hash += charToLong(subString.charAt(i)) * power;
            hash %= M;
            power = power * A % M;
        }
        return hash;
    }

    private static long charToLong(char ch) {
        return ch - 'A' + 1;
    }

    private static long prefixHashLeft() {
        if (posI == 0) {
            return prefixHashArray[posJ - 1];
        }
        long hashPrefixI = prefixHashArray[posI - 1];
        long hashPrefixJ = prefixHashArray[posJ - 1];
        return (hashPrefixJ - hashPrefixI) % M;
    }

    private static long prefixHashRight() {
        if (posK == 0) {
            return prefixHashArray[posM - 1];
        }
        long hashPrefixK = prefixHashArray[posK - 1];
        long hashPrefixM = prefixHashArray[posM - 1];
        return (hashPrefixM - hashPrefixK) % M;
    }

    private static void buildPrefixHashArrayOnFly() {
        try (InputStreamReader isr = new InputStreamReader(System.in)) {
            List<Long> list = new ArrayList<>();
            long hash = 0;
            long power = 1;
            int c;
            while ((c = isr.read()) != '\n') {
                hash += charToLong((char) c) * power;
                hash %= M;
                power = power * A % M;
                list.add(hash);
            }
//            prefixHashArray = list.toArray(Long[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.nextLine();
        System.out.println("s length: " + s.length());
//        buildPrefixHashArrayOnFly();
//        System.out.println("prefixHashArray length: " + prefixHashArray.length);

        int t = sc.nextInt();

        matrix = new int[t][4];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int result = 0;
        buildPrefixHashArray();

        System.out.println(Arrays.toString(prefixHashArray));

        for (int i = 0; i < t; i++) {
            posI = matrix[i][0];
            posJ = matrix[i][1];
            posK = matrix[i][2];
            posM = matrix[i][3];
            long hashLeft = prefixHashLeft();
            long hashRight = prefixHashRight();

            long a = 1;
            int power = posK - posI;
            for (int counter = 0; counter < power; counter++) {
                a = a * A % M;
            }

            long hashLeftNative = hash(s.substring(posI, posJ));
            long hashRightNative = hash(s.substring(posK, posM));
            System.out.println("line: " + (i + 1));
            System.out.println("left: " + s.substring(posI, posJ));
            System.out.println("right: " + s.substring(posK, posM));
            System.out.println("hashLeft: " + hashLeft);
            System.out.println("hashRight: " + hashRight);
            System.out.println("a: " + a);
            System.out.println("hashLeft * a: " + hashLeft * a);
            System.out.println("hashLeft * a % M: " + hashLeft * a % M);
            System.out.println("hashLeftNative: " + hashLeftNative);
            System.out.println("hashRightNative: " + hashRightNative);
            System.out.println("hashLeft % hashLeftNative: " + hashLeft % hashLeftNative);
            System.out.println("hashRight % hashRightNative: " + hashRight % hashRightNative);
            System.out.println("hashRight % hashLeft: " + hashRight % hashLeft);
            System.out.println("hashRightNative % hashLeftNative: " + hashRightNative % hashLeftNative);

            if (hashLeft * a % M == hashRight) {
                result++;
            }
        }

        System.out.println(result);
    }
}
