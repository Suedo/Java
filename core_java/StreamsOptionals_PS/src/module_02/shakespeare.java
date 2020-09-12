package module_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class shakespeare {
    public static void main(String[] args) throws IOException {

        // list of all possible scrabble words
        String files = "D:/code D/Java/core_java/Streams_and_Optionals/src/module_02/files/";

        Set<String> scrabbleWords = Files.lines(Paths.get(files + "ospd.txt"))
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        Set<String> shakespeareWords = Files.lines(Paths.get(files + "words.shakespeare.txt"))
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        System.out.println("# words of Shakespeare : " + shakespeareWords.size());
        System.out.println("# words of Scrabble : " + scrabbleWords.size());

        final int[] scrabbleENScore = {
             // a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,  q, r, s, t, u, v, w, x, y,  z
                1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

        Function<String, Integer> scrabbleScores = word -> word.chars()     // generate IntStream
                .map(letter -> scrabbleENScore[letter - 'a'])               // ascii subtraction
                .sum();                                                     // letter scores combined : word score

        String highestScoringWord = shakespeareWords
                .parallelStream()
                .filter(scrabbleWords::contains)
                .max(Comparator.comparing(scrabbleScores))      // Optional
                .get();                                         // value of Optional


        System.out.println("Top word : " + highestScoringWord + ", score : " + scrabbleScores.apply(highestScoringWord));

    }
}
