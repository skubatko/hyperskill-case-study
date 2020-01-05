import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(proceed(s));
    }

    static int proceed(String s) {
        return proceedBinarySearch(s);
    }


    static int proceedBinarySearch(String s) {
        int l = 1;
        int r = s.length() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(s, mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l - 1;
    }

    static int proceedNaive(String s) {
        for (int i = s.length() - 1; i > 0; i--) {
            if (check(s, i)) {
                return i;
            }
        }
        return 0;
    }

    static boolean check(String s, int size) {
        return checkSet(s, size);
    }

    static boolean checkSet(String s, int size) {
        int end = s.length() - size;
        int capacity = s.length() - size + 1;
        Set<Long> hashSet = new HashSet<>();

        long hash = 0;
        long pow = 1;

        int a = 256;
        long m = BigInteger.probablePrime(31, new Random()).longValue();

        for (int i = 0; i < size; i++) {
            hash += s.charAt(end + i) * pow;
            hash %= m;

            if (i != size - 1) {
                pow = pow * a % m;
            }
        }
        hashSet.add(hash);

        for (int i = s.length(); i > size; i--) {
            hash = (hash - s.charAt(i - 1) * pow % m + m) * a % m;
            hash = (hash + s.charAt(i - size - 1)) % m;

            hashSet.add(hash);
        }

        return hashSet.size() < capacity;
    }

    static boolean checkList(String s, int size) {
        int length = s.length();
        int end = length - size;
        List<Long> hashList = new ArrayList<>(length - size);

        long hash = 0;
        long pow = 1;

        int a = 53;
        long m = 1_000_000_000 + 9;
        for (int i = 0; i < size; i++) {
            hash += s.charAt(end + i) * pow;
            hash %= m;

            if (i != size - 1) {
                pow = pow * a % m;
            }
        }
        hashList.add(hash);

        for (int i = length; i > size; i--) {
            hash = (hash - s.charAt(i - 1) * pow % m + m) * a % m;
            hash = (hash + s.charAt(i - size - 1)) % m;

            if (hashList.contains(hash)) {
                return true;
            }

//            int idx;
//            if ((idx = hashList.indexOf(hash)) != -1) {
//                String current = s.substring(i - size - 1, i - 1);
//                String idxS = s.substring(s.length() - idx - size, s.length() - idx);
//                if (current.equals(idxS)) {
//                    return true;
//                }
//            }

            hashList.add(hash);
        }

        return false;
    }

    static boolean checkArray(String s, int size) {
        int end = s.length() - size;
        long[] hashes = new long[end + 1];

        long hash = 0;
        long pow = 1;

        int a = 53;
        long m = 1_000_000_000 + 9;
        for (int i = 0; i < size; i++) {
            hash += s.charAt(end + i) * pow;
            hash %= m;

            if (i != size - 1) {
                pow = pow * a % m;
            }
        }
        hashes[0] = hash;

        for (int i = s.length(); i > size; i--) {
            hash = (hash - s.charAt(i - 1) * pow % m + m) * a % m;
            hash = (hash + s.charAt(i - size - 1)) % m;

            hashes[s.length() - i + 1] = hash;
        }

        for (int i = 0; i < hashes.length - 1; i++) {
            for (int j = i + 1; j < hashes.length; j++) {
                if (hashes[i] == hashes[j]) {
                    boolean matches = true;
                    for (int k = 0; k < size; k++) {
                        if (s.charAt(end - i + k) != s.charAt(end - j + k)) {
                            matches = false;
                            break;
                        }
                    }

                    if (matches) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
