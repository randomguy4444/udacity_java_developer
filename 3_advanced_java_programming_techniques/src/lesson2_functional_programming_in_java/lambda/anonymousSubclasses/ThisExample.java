package lesson2_functional_programming_in_java.lambda.anonymousSubclasses;

public final class ThisExample {
    private final Runnable withLambda =
            () -> System.out.println("From lambda: " + this.getClass());
    private final Runnable withSubclass = new Runnable() {
        @Override
        public void run() {
            System.out.println("From subclass: " + this.getClass());
        }
    };

    public static void main(String[] args) {
        ThisExample thisExample = new ThisExample();
        thisExample.withLambda.run();
        thisExample.withSubclass.run();
    }
}

