package lesson29;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface NameValue {
    String value();
    int count() default 100;
}
public class User {
    @NameValue(/*value = */"Fred"/*, count=99*/) String name;

    public static void main(String[] args) throws Throwable {
        User myObject = new User();
        Class<?> cl = myObject.getClass();
        Field fld = cl.getDeclaredField("name");
        NameValue annot = fld.getAnnotation(NameValue.class);
        if(annot != null) {
            System.out.println("annot.value() = " + annot.value()+", count="+annot.count());
        }
    }
}


