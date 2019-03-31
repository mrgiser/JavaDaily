package annotation;

import java.lang.annotation.*;

/**
 * 描述:
 * anno2
 *
 * @Author he
 * @Create 2019-03-01 2:20 PM
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAnno2 {
    String one() default "";
    String two() default "";
}