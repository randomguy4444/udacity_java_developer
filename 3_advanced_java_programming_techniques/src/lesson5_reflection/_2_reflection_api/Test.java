package lesson5_reflection._2_reflection_api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// NOTE: this is RUNTIME
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)  // Applies to class, interface, or enum
public @interface Test {
    //Class<?> targetClass();
    //String setterPrefix() default "set";
}
