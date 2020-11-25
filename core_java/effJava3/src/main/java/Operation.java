import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation {

    // Courtesy Lambda's, we can use


    ADD(Ops::add, "+"),  // ENUMS are SINGLETONS, and here, the only instance is getting created
    SUBTRACT((x, y) -> x - y, "-"),
    MULTIPLY((x, y) -> x * y, "*"),
    DIVIDE((x, y) -> x / y, "/");

    // Constructor
    Operation(IntBinaryOperator operator, String opSymbol) {
        this.operator = operator;
        this.opSymbol = opSymbol;
    }

    private static final Map<String, Operation> SYMBOL_MAP = Stream.of(values())
            .collect(Collectors.toMap(op -> op.opSymbol, Function.identity()));

    private final IntBinaryOperator operator;
    private final String opSymbol;


    public static Optional<Operation> ofSymbol(String symbol) {
        return Optional.ofNullable(SYMBOL_MAP.get(symbol));
    }
}

class Ops {
    public static Integer add(Integer x, Integer y) {
        return x + y;
    }
}