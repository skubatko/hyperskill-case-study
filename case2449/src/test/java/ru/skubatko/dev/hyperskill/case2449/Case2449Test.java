package ru.skubatko.dev.hyperskill.case2449;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case2449.Case2449.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.List;

public class Case2449Test {

	@Rule
	public Timeout timeout = Timeout.seconds(5);

	@Test
	public void proceedCase1() {
		String s = "Functions bring happiness!";
		List<String> expected = Arrays.asList(
				"bring",
				"functions",
				"happiness"
		);

		List<String> result = proceed(s);

		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void proceedCase2() {
		String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
				"Sed sodales consectetur purus at faucibus. Donec mi quam, " +
				"tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia " +
				"velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet " +
				"sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. " +
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
				"Integer vel odio nec mi tempor dignissim.";
		List<String> expected = Arrays.asList(
				"consectetur",
				"faucibus",
				"ipsum",
				"lorem",
				"adipiscing",
				"amet",
				"dolor",
				"eget",
				"elit",
				"mi"
		);

		List<String> result = proceed(s);

		assertThat(result).isEqualTo(expected);
	}
}
