import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(proceed(s));
    }

    static int proceed(String s) {
        for (int i = s.length() - 1; i > 0; i--) {
            if (checkSize(s, i)) {
                return i;
            }
        }
        return 0;
    }

    private static boolean checkSize(String s, int size) {
        long[] hashes = new long[s.length() - size + 1];
        long hash = 0;
        long pow = 1;

        int a = 53;
        long m = 1_000_000_000 + 9;
        for (int i = 0; i < size; i++) {
            hash += charToLong(s.charAt(s.length() - size + i)) * pow;
            hash %= m;

            if (i != size - 1) {
                pow = pow * a % m;
            }
        }
        hashes[s.length() - size] = hash;

        for (int i = s.length(); i > size; i--) {
            hash = (hash - charToLong(s.charAt(i - 1)) * pow % m + m) * a % m;
            hash = (hash + charToLong(s.charAt(i - size - 1))) % m;

            if (checkHash(s, hash, i, size, hashes)) {
                return true;
            }
            hashes[i - size - 1] = hash;
        }

        return false;
    }

    private static long charToLong(char ch) {
        return ch;
    }

    private static boolean checkHash(String s, long hash, int idx, int size, long[] hashes) {
        String subHash = s.substring(idx - size - 1, idx - 1);
        for (int i = s.length(); i >= idx; i--) {
            if (hashes[i - size] == hash) {
                String subI = s.substring(i - size, i);
                if (subHash.equals(subI)) {
                    return true;
                }
            }
        }
        return false;
    }
}
