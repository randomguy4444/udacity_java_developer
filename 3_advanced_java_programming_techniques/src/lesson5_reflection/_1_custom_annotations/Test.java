package lesson5_reflection._1_custom_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)  // Applies to class, interface, or enum
public @interface Test {
    //Class<?> targetClass();
    //String setterPrefix() default "set";
}
