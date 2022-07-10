// https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
package lesson5_reflection._2_reflection_api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class TestRunner {

    private static final List<Class<?>> TESTS = List.of(CalculatorTest.class);

    public static void main(String[] args) throws Exception {

        List<String> passed = new ArrayList<>();
        List<String> failed = new ArrayList<>();

        for (Class<?> klass : TESTS) {
            if (!UnitTest.class.isAssignableFrom(klass)) {
                throw new IllegalArgumentException("Class " + klass.toString() + " must implement UnitTest");
            }
            // https://docs.oracle.com/javase/10/docs/api/java/lang/reflect/Method.html
            for (Method method : klass.getDeclaredMethods()) {
                System.out.println("method name: " + method.getName());
                if (method.getAnnotation(Test.class) != null) {
                    System.out.println("has @Test annotation");
                    try {
                        UnitTest test = (UnitTest) klass.getConstructor().newInstance();
                        test.beforeEachTest();
                        method.invoke(test);
                        test.afterEachTest();
                        passed.add(getTestName(klass, method));
                    } catch (Throwable throwable) {
                        failed.add(getTestName(klass, method));
                    }
                }
            }
        }

        System.out.println("Passed tests: " + passed);
        System.out.println("FAILED tests: " + failed);
    }

    private static String getTestName(Class<?> klass, Method method) {
        return klass.getName() + "#" + method.getName();
    }
}