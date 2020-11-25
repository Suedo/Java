package module_04;

import java.util.Optional;

/**
 * Created by 401944 on 11/15/2017.
 */
public class MyMath {

    public static Optional<Double> inverse(Double d) {
        // inverse of zero not allowed
        return d != 0d ? Optional.of(1d/d) : Optional.empty();

    }

    public static Optional<Double> squareRoot(Double d) {
        // square root of only positive numbers allowed
        return d < 0d ? Optional.empty() : Optional.of(Math.sqrt(d));
    }

}
