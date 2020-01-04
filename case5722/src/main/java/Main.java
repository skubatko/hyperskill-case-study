import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(proceed(s));
    }

    static int proceed(String s) {
        for (int i = s.length() - 1; i > 0; i--) {
            if (check(s, i)) {
                return i;
            }
        }
        return 0;
    }

    static boolean check(String s, int size) {
        List<Long> hashList = new ArrayList<>();

        long hash = 0;
        long pow = 1;

        int a = 53;
        long m = 1_000_000_000 + 9;
        for (int i = 0; i < size; i++) {
            hash += s.charAt(s.length() - size + i) * pow;
            hash %= m;

            if (i != size - 1) {
                pow = pow * a % m;
            }
        }
        hashList.add(hash);

        for (int i = s.length(); i > size; i--) {
            hash = (hash - s.charAt(i - 1) * pow % m + m) * a % m;
            hash = (hash + s.charAt(i - size - 1)) % m;

            if (hashList.contains(hash)) {
                return true;
            }
            hashList.add(hash);
        }

        return false;
    }
}
