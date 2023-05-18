package lesson29;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class Quiz {
}

@Target(ElementType.TYPE)
@interface Details {
    String[] names() default {"F", "M"};
    // Integer value(); // primitives, strings, enums, class, annotations, and their 1D arrays
    @interface R {

    }
    R value();
}
