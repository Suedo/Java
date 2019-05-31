import Helper.Basics;
import Helper.Numbers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tester {

    private static String lorem = "    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";

    private static Comparator<String> byDescOrder = (a, b) -> b.length() - a.length();

    public static void PalindromeTest() {
        List<String> pList = Arrays.asList("deified", "devived", "enimine", "murdrum", "racecar", "mom", "repaper", "reviver", "rotator", "sememes", "senines");
        System.out.println("Palindromes:");
        System.out.println(pList.stream().filter(Basics::isPalindrome).collect(Collectors.joining("\n")));
    }

    public static void SortingDemo() {
        System.out.println(Arrays.stream(lorem.trim().split("[ \\.,]+"))
                .sorted(byDescOrder)
                .collect(Collectors.joining("\n")));
    }

    public static void TestPrimes() {
        Stream<Integer> primes = Stream.iterate(2, i -> i + 1).filter(Numbers::isPrime).limit(100);
        System.out.println(primes.map(String::valueOf).collect(Collectors.joining("\n")));
    }

    public static List<Integer> ArmstrongTest() {
        /* Only 4 armstrong number exists: 153, 370, 371, 407 */
        return Stream.iterate(100, i -> i + 1)
                .peek(i -> {
                    if (i % 10000 == 0) System.out.print(". ");
                })
                .filter(Numbers::isArmstrong)
                .peek(System.out::println)
                .limit(4)
                .collect(Collectors.toList());
    }

    public static void anagramTest() {
        List<String> anagrams = Basics.anagrams("god".split(""));
        System.out.println(anagrams.size() + "\n" + anagrams);
    }


    public static void main(String[] args) {


    }

}
