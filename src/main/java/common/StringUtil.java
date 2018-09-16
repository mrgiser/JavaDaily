package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:
 * string
 *
 * @Author HeFeng
 * @Create 2018-09-14 14:34
 */
public class StringUtil {
    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
    public static void main(String[] args) {
        String s = "123";
        StringBuffer stringBuffer = new StringBuffer("1234");
        stringBuffer.append(567);
        logger.info("stringBuffer: " + stringBuffer.toString());

        StringBuilder stringBuilder = new StringBuilder("12345");
        stringBuilder.append(67);
        logger.info("stringBuilder: " + stringBuilder.toString());

        String string = "h,o,l,l,i,s,c,h,u,a,n,g";
        String[] splitAll = string.split(",");
        String[] splitFive = string.split(",",5);
        for (String str: splitAll ) {
            logger.info("splitAll: " + str);

        }
        for (String str: splitFive ) {
            logger.info("splitFive: " + str);

        }
        Integer i =0;
    }

}