package ru.skubatko.dev.hyperskill.case3658;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static ru.skubatko.dev.hyperskill.case3658.Case3658.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.List;

public class Case3658Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void proceedCase1() {
        String s = "<html><a>hello</a><h1><h4>nestedHello</h4><h3>nestedWorld</h3><h6><br>top</br></h6></h1><br>world</br></html>";

        List<String> expected = Arrays.asList(
                "hello",
                "nestedHello",
                "nestedWorld",
                "top",
                "<br>top</br>",
                "<h4>nestedHello</h4><h3>nestedWorld</h3><h6><br>top</br></h6>",
                "world",
                "<a>hello</a><h1><h4>nestedHello</h4><h3>nestedWorld</h3><h6><br>top</br></h6></h1><br>world</br>"
        );

        List<String> result = proceed(s);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        String s = "<html><input><h2><a>hello</a><br>world</br></h2></input></html>";

        List<String> expected = Arrays.asList(
                "hello",
                "world",
                "<a>hello</a><br>world</br>",
                "<h2><a>hello</a><br>world</br></h2>",
                "<input><h2><a>hello</a><br>world</br></h2></input>"
        );

        List<String> result = proceed(s);

        assertThat(result).isEqualTo(expected);
    }
}
