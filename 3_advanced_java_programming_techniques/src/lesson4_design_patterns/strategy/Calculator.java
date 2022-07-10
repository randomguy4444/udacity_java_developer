package lesson4_design_patterns.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class Calculator {
    private final Map<String, BinaryOperator<Integer>> operators = new HashMap<>();

    public void registerOperation(String symbol, BinaryOperator<Integer> operator) {
        operators.put(symbol.strip(), operator);
    }

    public int calculate(int a, String operator, int b) {
        return operators.get(operator).apply(a, b);
    }
}
