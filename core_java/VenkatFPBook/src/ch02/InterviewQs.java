package ch02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterviewQs {

	public static void groupingStrings() {

		Arrays.asList("123456789".split(""))
			.stream()
			.collect(Collectors.groupingBy(e -> Integer.parseInt(e)%2==0))
			.forEach((k,v) -> System.out.println(String.format(
					"%s : %s",k,getListAsString(v))));
		
	}
	
	private static String getListAsString(List<String> list) {
		return list.stream().collect(Collectors.joining(","));
	}
	
	private static Function<String, Predicate<String>> checkStartsWith = 
			letter -> name -> name.startsWith(letter);
			
	public static void findNamesWith(String names, String delimiter, String startingCharacter) {
		nameStreamProcessor(Arrays.asList(names.split(delimiter)).stream(),startingCharacter);
	}
	
	// Overload: names from file
	public static List<String> findNamesWith(Path filepath, String startingCharacter) throws IOException {
		return nameStreamProcessor(Files.lines(filepath),startingCharacter);
	}
	
	private static List<String> nameStreamProcessor(Stream<String> names, String startingCharacter) {
		return names
//				.parallel()
				.map(String::toUpperCase)
				.filter(checkStartsWith.apply(startingCharacter)) // needs a predicate, which is returned by our function
				.collect(Collectors.toList());
//				.forEach(System.out::println);
	}

}
