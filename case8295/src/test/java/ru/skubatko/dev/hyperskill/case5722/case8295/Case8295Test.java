package ru.skubatko.dev.hyperskill.case5722.case8295;

import static org.assertj.core.api.Assertions.assertThat;

import ru.skubatko.dev.hyperskill.case5722.case8295.Case8295.DoublyLinkedList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case8295Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void testCase1() {
        DoublyLinkedList list = new DoublyLinkedList();

        list.addLast(4);
        list.addLast(2);
        list.add(1);
        list.add(5);
        list.add(3);

        assertThat(list.toString()).isEqualTo("5 4 2 1 3 ");
    }
}
