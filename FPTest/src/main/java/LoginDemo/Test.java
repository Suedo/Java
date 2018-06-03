package LoginDemo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
	public static Integer divide(int num, int denom) {
		return num / denom;
	}

	public static Optional<Integer> divideOp(int num, int denom) {
		if (denom == 0)
			return Optional.empty();
		return Optional.of(num / denom);
	}

	public static Optional<Integer> divide24By(int denom) {
		int num = 24;
		if (denom == 0)
			return Optional.empty();
		return Optional.of(num / denom);
	}

	public static void main(String[] args) {
		// System.out.println(LoginDemo.Test.divide(15, 0)); // throws arithmeticException


		List<Integer> list = Arrays.asList(1, 2, 3, 4, 0).stream() // Stream<Integers>
				.map(Test::divide24By) // Stream<Optional<Integers>>
				.flatMap(elem -> elem.map(Stream::of).orElseGet(Stream::empty)) // Stream<Integers>
				.collect(Collectors.toList());

		list.forEach(System.out::println);

		Arrays.asList("7,8,9".split(",")).stream().map(Optional::ofNullable).collect(Collectors.toList())
				.forEach(System.out::println);

	}
}
