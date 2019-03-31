package annotation;

import java.lang.annotation.*;

/**
 * @author he
 * @Title: MyTestAnno
 * @ProjectName java.daily
 * @Description: TODO
 * @date 2019/3/12:14 PM
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@MyAnno2(one = "1", two = "2")
public @interface MyTestAnno {
    int test() default 0;
}
