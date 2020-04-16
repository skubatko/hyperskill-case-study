package ru.skubatko.dev.hyperskill.case6951;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import ru.skubatko.dev.hyperskill.case6951.Case6951.HashTable;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case6951Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    private HashTable<String> table;

    @Before
    public void setUp() {
        table = new HashTable<>(1);
    }

    @Test
    public void testCase1() {
        table.put(1, "0001");
        table.put(4, "0004");
        table.put(3, "0003");

        assertThat(table.get(1)).isEqualTo("0001");
        assertThat(table.get(4)).isEqualTo("0004");
        assertThat(table.get(3)).isEqualTo("0003");

        table.remove(1);
        table.remove(4);

        assertThat(table.get(1)).isNull();
        assertThat(table.get(4)).isNull();
        assertThat(table.get(3)).isEqualTo("0003");
    }

    @Test
    public void testCase05() {
        table.remove(1);
        table.remove(2);
        table.remove(3);

        assertThat(table.get(1)).isNull();
        assertThat(table.get(2)).isNull();
        assertThat(table.get(3)).isNull();

        table.put(1, "0001");
        table.put(2, "0002");
        table.put(3, "0003");

        assertThat(table.get(3)).isEqualTo("0003");
        assertThat(table.get(1)).isEqualTo("0001");
        assertThat(table.get(2)).isEqualTo("0002");

        table.remove(1);
        table.remove(3);
        table.remove(2);

        assertThat(table.get(3)).isNull();
        assertThat(table.get(1)).isNull();
        assertThat(table.get(2)).isNull();

        table.put(1, "0010");
        table.put(3, "0030");
        table.put(2, "0020");

        assertThat(table.get(3)).isEqualTo("0030");
        assertThat(table.get(2)).isEqualTo("0020");
        assertThat(table.get(1)).isEqualTo("0010");
    }
}
