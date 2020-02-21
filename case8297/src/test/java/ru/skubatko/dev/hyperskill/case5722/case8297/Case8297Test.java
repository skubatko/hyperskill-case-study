package ru.skubatko.dev.hyperskill.case5722.case8297;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5722.case8297.Case8297.DoublyLinkedList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case8297Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void testCase1() {
        String s = "abcd";
        DoublyLinkedList list = new DoublyLinkedList(s);

        list.split(1);
        list.reverse();
        list.split(1);

        assertThat(list.toString()).isEqualTo("dcba");
    }

    @Test
    public void testCase2() {
        String s = "ab";
        DoublyLinkedList list = new DoublyLinkedList(s);

        list.split(1);
        list.reverse();

        assertThat(list.toString()).isEqualTo("ab");
    }

    @Test
    public void testCase3() {
        String s = "abc";
        DoublyLinkedList list = new DoublyLinkedList(s);

        list.split(1);
        assertThat(list.toString()).isEqualTo("cba");

        list.split(1);
        assertThat(list.toString()).isEqualTo("abc");

        list.reverse();
        assertThat(list.toString()).isEqualTo("bac");
    }

    @Test
    public void testCase4() {
        String s = "abcdefgh";
        DoublyLinkedList list = new DoublyLinkedList(s);

        list.split(2);
        assertThat(list.toString()).isEqualTo("ghefcdab");

        list.reverse();
        assertThat(list.toString()).isEqualTo("badcfehg");

    }

    @Test
    public void testCase02() {
        String s = "abcdefghijklmnopqr";
        DoublyLinkedList list = new DoublyLinkedList(s);

        list.split(2);
        list.split(5);
        list.reverse();
        list.split(1);
        list.reverse();
        list.split(8);

        assertThat(list.toString()).isEqualTo("opqrabcdefghijklmn");
    }
}
