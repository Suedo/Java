package Helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Numbers {

    public static boolean isPrime(int number) {

        List<Integer> easyPrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);

        if (easyPrimes.contains(number)) return true;

        for (Integer i : easyPrimes) {
            if (number % i == 0) return false;
        }

        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 23; i < sqrt; i += 2) {
            if (number % i == 0) return false;
        }

        return true;

    }

    public static boolean isArmstrong(long ip) {
        /* Only 4 armstrong number exists: 153, 370, 371, 407 */
        long temp = ip;
        long rem = 0;
        long sum = 0;
        while ( temp > 0) {
            rem = temp % 10;
            sum = sum + (rem * rem * rem);
            temp = temp / 10;
        }

        return ip == sum ? true : false;

        /* M2:
        int sum = Arrays.stream(String.valueOf(153).split(""))
                    .mapToInt(Integer::parseInt)
                    .reduce(0, (accumulator, val) -> accumulator + (val * val * val));
         */

        /* M3:
        int sum = 0;
        for(int i = 0; i <arr.length; i++) {
            sum = sum + (int) Math.pow(Integer.parseInt(arr[i]),3);
        }
        */

    }
}
