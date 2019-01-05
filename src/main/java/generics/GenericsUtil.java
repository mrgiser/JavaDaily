package generics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 泛型
 *
 * @Author HeFeng
 * @Create 2018-09-18 16:53
 */
public class GenericsUtil {
    private static final Logger logger = LoggerFactory.getLogger(GenericsUtil.class);
    public static void main(String[] args) {
        MyClass<Integer> intClass = new MyClass<Integer>();
        logger.info("int class " + MyClass.i++);

        MyClass<String> strClass = new MyClass<String>();
        logger.info("int class " + MyClass.i++);

        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "hollis");
        map.put("age", "22");
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
    }



}

class MyClass<T> {
    public static int i = 10;
}

class ClassTest<X extends Number, Y, Z> {
    private X x;
//    private static Y y; //编译错误，不能用在静态变量中
    public X getFirst() {
        //正确用法
        return x;
    }
    public void wrong() {
//        Z z = new Z(); //编译错误，不能创建对象
    }
}