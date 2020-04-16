package ru.skubatko.dev.hyperskill.case4345;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Case4345 {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        Supplier<Stream<Integer>> saved = FunctionUtils.saveStream(stream.filter(e -> e % 2 == 0));

        System.out.println(saved.get().count());
        System.out.println(saved.get().max(Integer::compareTo).get());
        System.out.println(saved.get().min(Integer::compareTo).get());
    }

    static class FunctionUtils {
        public static <T> Supplier<Stream<T>> saveStream(Stream<T> stream) {
            ArrayList<T> list = new ArrayList<>();
            stream.forEach(list::add);
            return list::stream;
        }
    }
}
