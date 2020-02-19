package ru.skubatko.dev.hyperskill.case5722.case8294;

import static org.assertj.core.api.Assertions.assertThat;

import ru.skubatko.dev.hyperskill.case5722.case8294.Case8294.DoublyLinkedList;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Case8294Test {

    @Rule
    public Timeout timeout = Timeout.seconds(8);

    @Test
    public void testCase1() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.reverse();
        list.removeFirst();

        assertThat(list.toString()).isEqualTo("2 1 ");
    }

    @Test
    public void testCase05() throws IOException {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        List<String> lines = IOUtils.readLines(
                this.getClass().getResourceAsStream("/" + "hyperskill-8294-test-05.txt"), StandardCharsets.UTF_8);

        for (String line : lines) {
            String[] buf = line.split("\\s");
            String command = buf[0];
            switch (command) {
                case "addFirst":
                    list.addFirst(Integer.valueOf(buf[1]));
                    break;
                case "addLast":
                    list.addLast(Integer.valueOf(buf[1]));
                    break;
                case "removeFirst":
                    list.removeFirst();
                    break;
                case "removeLast":
                    list.removeLast();
                    break;
                case "reverse":
                    list.reverse();
                    break;
                default:
                    break;
            }
        }

        assertThat(list.toString()).isEqualTo("551 38 23 5 87 543 345 36 4 3 5 34 23 4 3 25 5 34 23 3 3 ");
    }
}
