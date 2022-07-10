package lesson5_reflection._4_custom_classloaders;

public interface UnitTest {
    default void beforeEachTest() {}
    default void afterEachTest() {}
}

