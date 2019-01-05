package json;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import http.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述:
 * json工具类
 *
 * @Author HeFeng
 * @Create 2018-09-03 14:31
 */
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);


    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{

        Device device = new Device();
        device.setDeviceId("00000000851787b5");
        device.setLockStatus(0);
        device.setActiveDate(new Date());

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//格式时间对象
        Date date=sdf.parse("2020-12-31");

        device.setExpiringDate(date);
        device.setType("RASP");

        LOGGER.info("json: "+ JsonUtil.objectToJson(device));

    }
}