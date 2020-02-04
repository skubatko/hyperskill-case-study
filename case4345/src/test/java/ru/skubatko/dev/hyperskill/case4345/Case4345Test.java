package ru.skubatko.dev.hyperskill.case4345;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Case4345Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void testCase1() {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        Supplier<Stream<Integer>> saved = Case4345.FunctionUtils.saveStream(stream.filter(e -> e % 2 == 0));

        assertThat(saved.get().count()).isEqualTo(4);
        assertThat(saved.get().max(Integer::compareTo).get()).isEqualTo(8);
        assertThat(saved.get().min(Integer::compareTo).get()).isEqualTo(2);
    }
}