package common;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * 描述:
 * 自动装拆箱
 * 如果数字在-128至127之间时，会直接使用缓存中的对象，而不是重新创建一个对象。
 *
 * @Author HeFeng
 * @Create 2018-09-14 16:39
 */

//其中的javadoc详细的说明了缓存支持-128到127之间的自动装箱过程。最大值127可以通过-XX:AutoBoxCacheMax=size修改。
//
//        实际上这个功能在Java 5中引入的时候,范围是固定的-128 至 +127。后来在Java 6中，可以通过java.lang.Integer.IntegerCache.high设置最大值。
//
//        这使我们可以根据应用程序的实际情况灵活地调整来提高性能。到底是什么原因选择这个-128到127范围呢？因为这个范围的数字是最被广泛使用的。 在程序中，第一次使用Integer的时候也需要一定的额外时间来初始化这个缓存。
//
//        在Boxing Conversion部分的Java语言规范(JLS)规定如下：
//
//        如果一个变量p的值是：
//    -128至127之间的整数(§3.10.1)
//
//            true 和 false的布尔值 (§3.10.3)

//            ‘\u0000’至 ‘\u007f’之间的字符0-127(§3.10.4)
//    范围内的时，将p包装成a和b两个对象时，可以直接使用a==b判断a和b的值是否相等。
public class IntegerUtil {

    public static void main(String[] args) {
        Integer integer1 = 3;
        Integer integer2 = 3;

        if (integer1 == integer2)
            System.out.println("integer1 == integer2");
        else
            System.out.println("integer1 != integer2");

        Integer integer3 = 300;
        Integer integer4 = 300;

        if (integer3 == integer4)
            System.out.println("integer3 == integer4");
        else
            System.out.println("integer3 != integer4");

        Integer integer5 = new Integer(12);
        Integer integer6 = new Integer(12);

        if (integer5 == integer6)
            System.out.println("integer5 == integer6");
        else
            System.out.println("integer5 != integer6");


        HashSet<String> hashSet = new HashSet<>();

        LinkedHashMap<Integer,String> linkedHashMap = new LinkedHashMap<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
    }
}