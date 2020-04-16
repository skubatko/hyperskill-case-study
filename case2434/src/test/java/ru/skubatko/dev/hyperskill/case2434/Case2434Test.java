package ru.skubatko.dev.hyperskill.case2434;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case2434.Case2434.identityTransformation;
import static ru.skubatko.dev.hyperskill.case2434.Case2434.multTwoAndThenAddOneTransformation;
import static ru.skubatko.dev.hyperskill.case2434.Case2434.squareAndThenGetNextEvenNumberTransformation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.List;

public class Case2434Test {

	@Rule
	public Timeout timeout = Timeout.seconds(5);

	@Test
	public void testCase1() {
		List<Integer> test1 = identityTransformation.apply(Arrays.asList(1, 1, 1, 1));
		assertThat(test1).containsExactly(1, 1, 1, 1);

		List<Integer> test2 = multTwoAndThenAddOneTransformation.apply(Arrays.asList(1, 1, 1, 1));
		assertThat(test2).containsExactly(3, 3, 3, 3);

		List<Integer> test3 = squareAndThenGetNextEvenNumberTransformation.apply(Arrays.asList(1, 1, 1, 1));
		assertThat(test3).containsExactly(2, 2, 2, 2);
	}

	@Test
	public void testCase2() {
		List<Integer> test1 = identityTransformation.apply(Arrays.asList(1, 2, 3));
		assertThat(test1).containsExactly(1, 2, 3);

		List<Integer> test2 = multTwoAndThenAddOneTransformation.apply(Arrays.asList(1, 2, 3));
		assertThat(test2).containsExactly(3, 5, 7);

		List<Integer> test3 = squareAndThenGetNextEvenNumberTransformation.apply(Arrays.asList(1, 2, 3));
		assertThat(test3).containsExactly(2, 6, 10);
	}
}
