package string;


import http.HttpsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * string
 *
 * @Author HeFeng
 * @Create 2018-09-16 15:19
 */
public class StringTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringTest.class);
    public static void main(String[] args) {
        String s1 = "Hollis";
        String s2 = new String("Hollis");
        String s3 = new String("Hollis").intern();

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s3);

        char []s = {'a','b','c'};
        String string1 = s.toString();
        String string2 = new String(s);
        LOGGER.info("s: " + s);
        LOGGER.info("string1: " + string1);
        LOGGER.info("string2: " + string2);
    }

}