package lesson5_reflection._4_custom_classloaders;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


// When you're finished with the code, compile and run the tests:
// $javac *.java */*.java
// $java -ea TestRunner tests/ CalculatorTest
public final class TestRunner {

    // TODO: Delete this list!
    // 2. Delete the static TESTS list; your new code will have to load the class dynamically,
    // using only the location and name information from the command-line arguments.
    //private static final List<Class<?>> TESTS = List.of(CalculatorTest.class);

    // 1. Update the main() method to take two command-line arguments: the first is the external test directory,
    // the second is the name of the test class that should be run.
    public static void main(String[] args) throws Exception {
        // TODO: Make sure the user has passed in two arguments: one for the test directory, and one
        //       with the name of the test class to run.
        List<String> passed = new ArrayList<>();
        List<String> failed = new ArrayList<>();

        /* 3. Add code to load the test class by name, using the command-line arguments.
        The easiest way to do this is to use Class.forName(<name>, true, <custom ClassLoader>).
        You can write your own ClassLoader subclass if you want the practice, but it's faster
        in this case to convert the test directory name to a java.net.URL (for example: Path.of(args[0]).toUri().toURL())
        and then use a URLClassLoader to do the class loading. */
        // TODO: Rewrite this for-loop. Instead of using the TESTS list (which you should have deleted),
        //       locate the test using the command-line arguments and a custom ClassLoader. Then call
        //       Class.forName(className, true, loader) using the custom ClassLoader for the third
        //       parameter.
        //
        //       The code to record passed/failed tests should pretty much stay the same.
        Class<?> testClass = getTestClass(args[0], args[1]);

        for (Method method: testClass.getDeclaredMethods()) {
            if (method.getAnnotation(Test.class) == null) {
                continue;
            }

            try {
                UnitTest test = (UnitTest) testClass.getConstructor().newInstance();
                test.beforeEachTest();
                method.invoke(test);
                test.afterEachTest();
                passed.add(getTestName(testClass, method));
            } catch (Throwable throwable) {
                failed.add(getTestName(testClass, method));
            }
        }

        System.out.println("Passed tests: " + passed);
        System.out.println("Failed tests: " + failed);

        /*
        for (Class<?> klass : TESTS) {
            if (!UnitTest.class.isAssignableFrom(klass)) {
                throw new IllegalArgumentException("Class " + klass.toString() + " must implement UnitTest");
            }

            for (Method method : klass.getDeclaredMethods()) {
                if (method.getAnnotation(Test.class) != null) {
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
        }*/
    }

    private static Class<?> getTestClass(String testFolder, String className) throws Exception {
        // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html
        // https://docs.oracle.com/javase/7/docs/api/java/net/URL.html
        URL testDir = Path.of(testFolder).toUri().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{testDir});
        // https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html
        Class<?> klass = Class.forName(className, true, loader);
        if (!UnitTest.class.isAssignableFrom(klass)) {
            throw new IllegalArgumentException("Class" + klass.toString() + " must implement UnitTest");
        }
        return klass;
    }

    private static String getTestName(Class<?> klass, Method method) {
        return klass.getName() + "#" + method.getName();
    }
}
