package Helper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Basics {


    public static boolean isPalindrome(String ip) {
        ip = ip.toUpperCase();

        for (int h = 0, t = ip.length() - 1; h <= t; h++, t--) {
            if (ip.charAt(h) != ip.charAt(t)) {
                System.out.println(String.format("ip: %s, palindrome broken at (%s,%s)", ip, h, t));
                return false;
            }
        }

        return true;
    }


    public static String reverse(String ip) {

        StringBuilder str = new StringBuilder();

        for (int i = ip.length() - 1; i >= 0; i--) {
            str.append(ip.charAt(i));
        }

        return str.toString();

    }

    public static String reverseInPlace(String ip) {
        char[] chars = ip.toCharArray();
        for (int i = 0, j = chars.length - 1; i <= j; i++, j--) {
            // swap i & j
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }


    public static long Factorial(int num) {

        if (num == 0) return 1;

        int fact = 1;
        while (num > 0) {
            fact = fact * num;
            num--;
        }
        return fact;
    }

    public static boolean isAnagram(String a, String b) {

        if (a.length() != b.length()) return false;

        a = a.toUpperCase();
        b = b.toUpperCase();

        String[] arr1 = a.split("");
        String[] arr2 = b.split("");

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static List<String> anagrams(String[] ip) {

        System.out.println(">> " + Arrays.toString(ip));
        if (ip.length == 1) return Arrays.asList(ip);

        List<String> subs = new ArrayList<>();

        for (int i = 0; i < ip.length; i++) {

            // divide the problem into one + rest[].
            // then recurse over rest[]

            // swap ith/partitioning entry with 0th entry. helps to subarray
            String one = ip[i];
            ip[i] = ip[0];
            ip[0] = one;

            // step 2 : create subarray[1 .. N] and get anagrams for it,
            // then concat with partitioning element
            for (String eachFromRest : anagrams(Arrays.copyOfRange(ip, 1, ip.length))) {
                subs.add(one + eachFromRest); // concat and add to list
            }

        }

        return subs;

    }

    public static HashSet<String> getAllSubsequencesOf(String[] ip) {

        System.out.println(">> " + Arrays.toString(ip));

        HashSet<String> subs = new HashSet<>();
        if (ip.length == 1) {
            subs.add(ip[0]);
            return subs;
        }

        subs.add(String.join("", ip));

        for (int i = 0; i < ip.length; i++) {

            // divide the problem into one + rest[].
            // then recurse over rest[]

            // swap ith/partitioning entry with 0th entry. helps to subarray
            String one = ip[i];
            ip[i] = ip[0];
            ip[0] = one;

            // step 2 : create subarray[1 .. N] and get subsequences for it,
            // add them to current
            subs.addAll(getAllSubsequencesOf(Arrays.copyOfRange(ip, 1, ip.length)));

        }

        return subs;
    }

    /**
     * Given Integer array, count the number of elements that occur more than once. EX: if ip = [1,3,3,4,4,4] there are two non unique elements: 3 and 4
     *
     * @param numbers
     * @return
     */
    public static int countNonUnique(List<Integer> numbers) {

        int duplicates = 0;

        Collections.sort(numbers);
        for (int i = 0; i < numbers.size() - 1; i++) {
            int j = i + 1;
            Integer a = numbers.get(i);
            while (j < numbers.size() && numbers.get(j).equals(a)) {
                j++;
            }

            if (j > i + 1) duplicates++;
            i = j;
            i--;

        }

        return duplicates;

    }

    /**
     * A repeated word is a cse sensitive word that appears more than once in a sentence.
     * delimeters:
     * whitespace: tab, space
     * others: ,:;-.
     *
     * @param s
     * @return
     */
    public static String firstRepeatedWord(String s) {

        String[] arr = s.split("[ \\t,:;-\\\\.]+");
        String firstRepeatedCaseSensitiveWord = null;

        for (int i = 0; i < arr.length; i++) {
            int j = i + 1;
            while (j < arr.length && !arr[i].equals(arr[j])) {
                j++;
            }

            if (j < arr.length && arr[i].equals(arr[j])) {
                firstRepeatedCaseSensitiveWord = arr[i];
                break;
            }
        }

        return firstRepeatedCaseSensitiveWord;
    }


    private static Integer firstLowerOrEqualPrice(int base, List<Integer> prices) {

        Integer basePrice = prices.get(base);

        for (int i = base + 1; i < prices.size(); i++) {
            if (prices.get(i) <= basePrice) return prices.get(i);
        }

        return null;
    }

    public static void finalPrice(List<Integer> prices) {
        // Write your code here

        List<Integer> discountedPrices = new ArrayList<>(prices.size());
        List<Integer> fullPriceIndexes = new ArrayList<>();

        for (int i = 0; i < prices.size(); i++) {
            Integer discount = firstLowerOrEqualPrice(i, prices);
            Integer listedPrice = prices.get(i);

            if (discount != null) {
                discountedPrices.add(listedPrice - discount);
            }else {
                discountedPrices.add(listedPrice);
                fullPriceIndexes.add(i);
            }
        }

        System.out.println(discountedPrices.stream().mapToInt(value -> value).sum());
        String fullPrices = fullPriceIndexes.stream().map(String::valueOf).collect(Collectors.joining(" "));

        if (fullPriceIndexes.size() > 0) {
            System.out.println(fullPrices);
        } else {
            System.out.println(" ");
        }

    }


}
