package lesson2_functional_programming_in_java.lambda.cal;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
/*
public class Calculator {
    // TODO: Fill this class in.
    Map<String, BinaryOperator<Integer>> operatorMap;

    public Calculator() {
       operatorMap = new HashMap<>();
    }

    // NOTE: you have to have the <Integer> for the BinaryOperator functional interface
    public void registerOperation(String op, BinaryOperator<Integer> bo) {
        operatorMap.put(op.strip(), bo);
    }

    public int calculate(int a, String op, int b) {
        BinaryOperator bo = operatorMap.get(op.strip());
        return (int) bo.apply(a, b);
    }
}*/

public final class Calculator {
    private final Map<String, BinaryOperator<Integer>> operators = new HashMap<>();

    public void registerOperation(String symbol, BinaryOperator<Integer> operator) {
        operators.put(symbol.strip(), operator);
    }

    public int calculate(int a, String operator, int b) {
        return operators.get(operator).apply(a, b);
    }
}