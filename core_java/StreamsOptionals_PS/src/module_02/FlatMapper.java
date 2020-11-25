package module_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FlatMapper {
    public static void main(String[] args) throws IOException {

        String files = "D:/code D/Java/core_java/Streams_and_Optionals/src/module_02/files/";
        Stream<String> file1 = Files.lines(Paths.get(files + "TomSawyer_01.txt"));
        Stream<String> file2 = Files.lines(Paths.get(files + "TomSawyer_02.txt"));
        Stream<String> file3 = Files.lines(Paths.get(files + "TomSawyer_03.txt"));
        Stream<String> file4 = Files.lines(Paths.get(files + "TomSawyer_04.txt"));

        // words be separated by spaces only (a bit of generalization)
        Function<String, Stream<String>> splitLinesToWords = Pattern.compile(" ")::splitAsStream;  // function reference

        Stream<String> streamOfWords = Stream.of(file1, file2, file3, file4)        // Stream<Stream<lines>>
                .flatMap(Function.identity())                                       // Stream<lines>. Monads in action
                .flatMap(splitLinesToWords);


        System.out.println("Total words: " + streamOfWords.count());                // 70552

        // IllegalStateException : stream already closed
        // System.out.println("Total unique words: " + streamOfWords.distinct().count());

    }
}
