import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        return checkDoubleHash(s, size);
//        return checkDoubleSet(s, size);
//        return checkDoubleMap(s, size);
//        return checkSetBigInt(s, size);
    }

    static boolean checkDoubleHash(String s, int size) {
        int length = s.length();
        int capacity = length - size + 1;
        Map<Long, Long> hashMap = new HashMap<>(capacity);

        long hash1 = 0;
        long hash2 = 0;
        long pow1 = 1;
        long pow2 = 1;

        int a1 = 59;
        int a2 = 53;
        long m1 = 1_000_000_000 + 7;
        long m2 = 1_000_000_000 + 9;

        for (int i = 0; i < size; i++) {
            hash1 += s.charAt(capacity - 1 + i) * pow1;
            hash1 %= m1;

            hash2 += s.charAt(capacity - 1 + i) * pow2;
            hash2 %= m2;

            if (i != size - 1) {
                pow1 = pow1 * a1 % m1;
                pow2 = pow2 * a2 % m2;
            }
        }
        hashMap.put(hash1, hash2);

        for (int i = length, j = 1; i > size; i--, j++) {
            hash1 = (hash1 - s.charAt(i - 1) * pow1 % m1 + m1) * a1 % m1;
            hash1 = (hash1 + s.charAt(i - size - 1)) % m1;

            hash2 = (hash2 - s.charAt(i - 1) * pow2 % m2 + m2) * a2 % m2;
            hash2 = (hash2 + s.charAt(i - size - 1)) % m2;

            Long hash = hashMap.put(hash1, hash2);
            if (hash != null && Objects.equals(hash, hash2)) {
                return true;
            }
        }

        return false;
    }

    static boolean checkDoubleSet(String s, int size) {
        int length = s.length();
        int capacity = length - size + 1;
        Set<Long> hashSet1 = new HashSet<>(capacity);
        Set<Long> hashSet2 = new HashSet<>(capacity);

        long hash1 = 0;
        long hash2 = 0;
        long pow1 = 1;
        long pow2 = 1;

        int a1 = 53;
        int a2 = 52;
        long m1 = 1_000_000_000 + 7;
        long m2 = 1_000_000_000 + 9;

        for (int i = 0; i < size; i++) {
            hash1 += s.charAt(capacity - 1 + i) * pow1;
            hash1 %= m1;

            hash2 += s.charAt(capacity - 1 + i) * pow2;
            hash2 %= m2;

            if (i != size - 1) {
                pow1 = pow1 * a1 % m1;
                pow2 = pow2 * a2 % m2;
            }
        }
        hashSet1.add(hash1);
        hashSet2.add(hash2);

        for (int i = length; i > size; i--) {
            hash1 = (hash1 - s.charAt(i - 1) * pow1 % m1 + m1) * a1 % m1;
            hash1 = (hash1 + s.charAt(i - size - 1)) % m1;

            hash2 = (hash2 - s.charAt(i - 1) * pow2 % m2 + m2) * a2 % m2;
            hash2 = (hash2 + s.charAt(i - size - 1)) % m2;

            boolean isPresented1 = !(hashSet1.add(hash1));
            boolean isPresented2 = !(hashSet2.add(hash2));
            if (isPresented1 && isPresented2) {
                return true;
            }
        }

        return false;
    }

    static boolean checkDoubleMap(String s, int size) {
        int length = s.length();
        int capacity = length - size + 1;
        Map<Long, Integer> hashMap1 = new HashMap<>(capacity);
        Map<Long, Integer> hashMap2 = new HashMap<>(capacity);

        long hash1 = 0;
        long hash2 = 0;
        long pow1 = 1;
        long pow2 = 1;

        int a1 = 13;
        int a2 = 11;
        long m = 1_000_000_000 + 7;

        for (int i = 0; i < size; i++) {
            hash1 += s.charAt(capacity - 1 + i) * pow1;
            hash1 %= m;

            hash2 += s.charAt(capacity - 1 + i) * pow2;
            hash2 %= m;

            if (i != size - 1) {
                pow1 = pow1 * a1 % m;
                pow2 = pow2 * a2 % m;
            }
        }
        hashMap1.put(hash1, 0);
        hashMap2.put(hash2, 0);

        for (int i = length, j = 1; i > size; i--, j++) {
            hash1 = (hash1 - s.charAt(i - 1) * pow1 % m + m) * a1 % m;
            hash1 = (hash1 + s.charAt(i - size - 1)) % m;

            hash2 = (hash2 - s.charAt(i - 1) * pow2 % m + m) * a2 % m;
            hash2 = (hash2 + s.charAt(i - size - 1)) % m;

            Integer idx1 = hashMap1.put(hash1, j);
            Integer idx2 = hashMap2.put(hash2, j);
            if (idx1 != null && Objects.equals(idx1, idx2)) {
                return true;
            }
        }

        return false;
    }

    static boolean checkSetBigInt(String s, int size) {
        int length = s.length();
        int end = length - size;
        int capacity = length - size + 1;
        Set<BigInteger> hashSet = new HashSet<>(capacity);

        BigInteger hash = BigInteger.ZERO;
        BigInteger pow = BigInteger.ONE;

        BigInteger a = BigInteger.valueOf(256);
        BigInteger m = BigInteger.probablePrime(34, new Random());

        for (int i = 0; i < size; i++) {
            hash = hash.add(pow.multiply(BigInteger.valueOf(s.charAt(end + i))));
//            hash += s.charAt(end + i) * pow;
            hash = hash.mod(m);
//            hash %= m;

            if (i != size - 1) {
                pow = pow.multiply(a).mod(m);
//                pow = pow * a % m;
            }
        }
        hashSet.add(hash);

        for (int i = length; i > size; i--) {
            hash = hash.subtract(BigInteger.valueOf(s.charAt(i - 1)).multiply(pow).mod(m)).add(m).multiply(a).mod(m);
//            hash = (hash - s.charAt(i - 1) * pow % m + m) * a % m;
            hash = hash.add(BigInteger.valueOf(s.charAt(i - size - 1))).mod(m);
//            hash = (hash + s.charAt(i - size - 1)) % m;

            hashSet.add(hash);
        }

        return hashSet.size() < capacity;
    }

    static boolean checkSet(String s, int size) {
        int end = s.length() - size;
        int capacity = s.length() - size + 1;
        Set<Long> hashSet = new HashSet<>();

        long hash = 0;
        long pow = 1;

        int a = 256;
        long m = longRandomPrime(31);

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

            hashList.add(hash);
        }

        return false;
    }

    static boolean checkDeepList(String s, int size) {
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

            int idx;
            if ((idx = hashList.indexOf(hash)) != -1) {
                String current = s.substring(i - size - 1, i - 1);
                String idxS = s.substring(s.length() - idx - size, s.length() - idx);
                if (current.equals(idxS)) {
                    return true;
                }
            }

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

    static long longRandomPrime(int bits) {
        BigInteger prime = BigInteger.probablePrime(bits, new Random());
        return prime.longValue();
    }
}
