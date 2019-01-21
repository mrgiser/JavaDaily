package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 * ConcurrentHashMap test
 *
 * @Author he
 * @Create 2019-01-10 10:44 PM
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args)  throws Exception{
//        Map<String, String> map = new ConcurrentHashMap<String, String>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("A","A");
        map.put("B","B");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next();
//            iterator.remove();
            map.remove("A");
        }

        if(iterator.hasNext()) {
            iterator.next().setValue("new");
        }
        map.forEach((k,v) -> System.out.printf("key:%s,value:%s\n", k, v));




//        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>(4);
//        map.put("A","A");
//        map.put("B","B");
//        // map.remove("A");
//        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
//        while(iterator.hasNext()) {
////            map.remove("A","A");
////            map.remove("B");
////            map.put("C", "C");
//
//            Map.Entry<String, String> entry = iterator.next();
//            iterator.remove();
//            System.out.printf("key:%s, value:%s\n", entry.getKey(), entry.getValue());
//
//            System.out.println("------print all start-------");
//            map.forEach((k,v) -> System.out.printf("key:%s,value:%s\n", k, v));
//            System.out.println("------print all end --------");

        }

}