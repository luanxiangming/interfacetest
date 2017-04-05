package shelper.datadrive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface DataDriven {
    public String excel() default "auto";
    public String sheet() default "auto";
    public String type() default "excel";
}
