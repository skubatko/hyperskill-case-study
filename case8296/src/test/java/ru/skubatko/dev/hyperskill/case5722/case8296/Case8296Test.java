package ru.skubatko.dev.hyperskill.case5722.case8296;

import static org.assertj.core.api.Assertions.assertThat;

import ru.skubatko.dev.hyperskill.case5722.case8296.Case8296.DoublyCircularLinkedList;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Case8296Test {

    @Rule
    public Timeout timeout = Timeout.seconds(8);

    @Test
    public void testCase1() {
        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertThat(list.toString()).isEqualTo("1 2 3 4 5 ");

        list.removeRight(1);
        list.removeRight(1);

        assertThat(list.toString()).isEqualTo("1 3 5 ");
    }

    @Test
    public void testCase2() {
        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertThat(list.toString()).isEqualTo("1 2 3 4 5 ");

        list.removeLeft(1);
        list.removeLeft(1);

        assertThat(list.toString()).isEqualTo("1 2 4 ");
    }

    @Test
    public void testCase04() throws IOException {
        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<>();

        List<String> lines = IOUtils.readLines(
                this.getClass().getResourceAsStream("/" + "hyperskill-8296-test-04.txt"), StandardCharsets.UTF_8);

        Arrays.stream(lines.get(1).split("\\s"))
                .map(Integer::parseInt)
                .forEach(list::add);

        assertThat(list.toString()).isEqualTo("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ");

        for (int i = 2; i < lines.size(); i++) {
            String[] buf = lines.get(i).split("\\s");

            String command = buf[0];
            int distance = Integer.parseInt(buf[1]);
            switch (command) {
                case "r":
                    list.removeRight(distance);
                    break;
                case "l":
                    list.removeLeft(distance);
                    break;
                default:
                    break;
            }
        }

        assertThat(list.toString()).isEqualTo("1 4 10 11 13 ");
    }

    @Test
    public void testCase03() throws IOException {
        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<>();

        List<String> lines = IOUtils.readLines(
                this.getClass().getResourceAsStream("/" + "hyperskill-8296-test-03.txt"), StandardCharsets.UTF_8);

        Arrays.stream(lines.get(1).split("\\s"))
                .map(Integer::parseInt)
                .forEach(list::add);

        assertThat(list.toString()).isEqualTo("1 2 ");

        for (int i = 2; i < lines.size(); i++) {
            String[] buf = lines.get(i).split("\\s");

            String command = buf[0];
            int distance = Integer.parseInt(buf[1]);
            switch (command) {
                case "r":
                    list.removeRight(distance);
                    break;
                case "l":
                    list.removeLeft(distance);
                    break;
                default:
                    break;
            }
        }

        assertThat(list.toString()).isEqualTo("");
    }
}
