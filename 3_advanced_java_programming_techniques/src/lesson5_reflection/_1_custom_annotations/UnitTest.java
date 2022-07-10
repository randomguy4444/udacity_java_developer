package lesson5_reflection._1_custom_annotations;

public interface UnitTest {
    default void beforeEachTest() {}
    default void afterEachTest() {}
}

