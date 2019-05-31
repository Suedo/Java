package Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        for(int i = 0, j = chars.length - 1; i <= j ; i++, j--) {
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


}
