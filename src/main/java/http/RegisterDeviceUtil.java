package http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import json.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 批量注册设备
 *
 * @Author HeFeng
 * @Create 2018-12-10 18:51
 */
public class RegisterDeviceUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterDeviceUtil.class);
    private static RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(3000)
            .setConnectionRequestTimeout(1000).setSocketTimeout(10000).build();
    public static List<String> mgrUrl = new ArrayList<>();
    public static String cpuIDs;

    public static void main(String[] args) {
        cpuIDs = "00000000bb91fd52 " ;

        mgrUrl.add("125.74.55.69:8888");
        mgrUrl.add("192.168.90.75:8888");
        mgrUrl.add("36.111.161.47:8888");
        mgrUrl.add("36.111.161.50:8888");
//        mgrUrl.add("192.168.90.41:8888");

        for(int i =0; i<mgrUrl.size(); i++){

            registerDevices2Url(mgrUrl.get(i), cpuIDs);
        }


    }

    public static void registerDevices2Url(String mgrUrl, String cpuIDs){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient hp = (CloseableHttpClient)wrapClient(httpClient);
        String https_Str = genHttpsUrl(mgrUrl);

        List<String> parserIDs = parserIDs(cpuIDs);

        for (int i=0; i< parserIDs.size();i++){
            String now_id = parserIDs.get(i);
            String json_request = genJsonStr(now_id);

            HttpPost httpPost = new HttpPost(https_Str);

            if (json_request != null) {
                StringEntity strEntity = new StringEntity(json_request, "UTF-8");
                strEntity.setContentType("application/json");
                httpPost.setEntity(strEntity);
            }
            httpPost.setConfig(defaultRequestConfig);
            CloseableHttpResponse response = null;

            try{
                response = hp.execute(httpPost);
            } catch (Exception e){
                LOGGER.error(e.getMessage(),e);
                return;
            }

            HttpEntity entity = response.getEntity();
            StatusLine status = response.getStatusLine();
            String responseStr = null;
            try{
                responseStr = EntityUtils.toString(entity);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(),e);
                return;
            }
            JSONObject json = JSON.parseObject(responseStr);
            if (json.getString("code").equals("200")){

                LOGGER.info( json.getString("code") + " mgr :" + mgrUrl + " id :" + now_id);
            } else {
                LOGGER.error("mgr : " +mgrUrl + " id: " +now_id + " code: " + json.getString("code"));
                LOGGER.error(  json.toString());
            }

        }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                }
            }

    }

    public static String genJsonStr(String ID){
        Device device = new Device();
        device.setDeviceId(ID);
        device.setLockStatus(0);
        device.setActiveDate(new Date());

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//格式时间对象
        Date date = null;
        try{
            date=sdf.parse("2020-12-31");
        } catch (ParseException e) {
            LOGGER.warn(e.getMessage(),e);
        }

        device.setExpiringDate(date);
        device.setType("RASP");

//        LOGGER.info("json: "+ JsonUtil.objectToJson(device));

        return JsonUtil.objectToJson(device);
    }

    public static List<String> parserIDs(String cpuIDs){
        String [] arr = cpuIDs.split("\\s+");
        List list = Arrays.asList(arr);
        return list;
    }

    public static HttpClient wrapClient(HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(ctx,NoopHostnameVerifier.INSTANCE);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(ssf).build();
            return httpclient;
        } catch (Exception ex) {
            ex.printStackTrace();
            return HttpClients.createDefault();
        }
    }

    public static String genHttpsUrl(String url){
        String pre = "https://";
        String aft = "/aep-clouddesktop-mgr/device/add";
        String httpsStr = pre + url + aft;
//        LOGGER.info("HttpsUrl : " + httpsStr);
        return httpsStr;
    }

}