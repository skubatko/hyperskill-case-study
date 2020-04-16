package ru.skubatko.dev.hyperskill.case2484;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case2484Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);


    Multiset<String> first;
    Multiset<String> second;

    @Before
    public void setUp() {
        first = new HashMultiset<>();
        first.add("aaa");
        for (int i = 0; i < 10; i++) {
            first.add("bbb");
        }
        for (int i = 0; i < 100; i++) {
            first.add("ccc");
        }
        for (int i = 0; i < 1000; i++) {
            first.add("ddd");
        }

        second = new HashMultiset<>();
        second.add("bbb");
        for (int i = 0; i < 10; i++) {
            second.add("aaa");
        }
        for (int i = 0; i < 100; i++) {
            second.add("ccc");
        }
        for (int i = 0; i < 1000; i++) {
            second.add("eee");
        }
    }

    @Test
    public void testCase1() {
        Multiset<String> union = new HashMultiset<>();
        for (int i = 0; i < 10; i++) {
            union.add("aaa");
        }
        for (int i = 0; i < 10; i++) {
            union.add("bbb");
        }
        for (int i = 0; i < 100; i++) {
            union.add("ccc");
        }
        for (int i = 0; i < 1000; i++) {
            union.add("ddd");
        }
        for (int i = 0; i < 1000; i++) {
            union.add("eee");
        }

        first.union(second);
        assertThat(first).isEqualTo(union);
    }

    @Test
    public void testCase2() {
        Multiset<String> intersection = new HashMultiset<>();
        intersection.add("aaa");
        intersection.add("bbb");
        for (int i = 0; i < 100; i++) {
            intersection.add("ccc");
        }

        first.intersect(second);
        assertThat(first).isEqualTo(intersection);
    }
}
