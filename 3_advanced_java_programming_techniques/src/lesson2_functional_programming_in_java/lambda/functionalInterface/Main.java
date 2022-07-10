package lesson2_functional_programming_in_java.lambda.functionalInterface;

import java.util.function.BinaryOperator;

public final class Main {
    public static void main(String[] args) {
        BinaryOperation add = (a, b) -> a + b;
        // alternative method
        //BinaryOperator<Integer> add = (a, b) -> a + b;
        assert 5 == add.apply(2, 3);
    }
}