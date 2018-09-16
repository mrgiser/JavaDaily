package http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * 描述:
 * HttpsClient
 *
 * @Author HeFeng
 * @Create 2018-09-13 9:57
 */
public class HttpsClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsClient.class);
    private static RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(3000)
            .setConnectionRequestTimeout(1000).setSocketTimeout(10000).build();

    public static String doPostByClient(String url, String requestStr, boolean isNoSSL)
            throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        if(isNoSSL)
        {
            httpClient = (CloseableHttpClient)wrapClient(httpClient);
        }

        HttpPost httpPost = new HttpPost(url);
        if (requestStr != null) {
            StringEntity strEntity = new StringEntity(requestStr, "UTF-8");
            strEntity.setContentType("application/json");
            httpPost.setEntity(strEntity);
        }
        httpPost.setConfig(defaultRequestConfig);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            StatusLine status = response.getStatusLine();
            int statusCode = status.getStatusCode();
            String responseStr = EntityUtils.toString(entity);
            JSONObject json = JSON.parseObject(responseStr);

            return json.toJSONString();
        } catch (IOException e) {
            LOGGER.info("HttpExecutor error: " + e.getMessage());
            return null;
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 避免HttpClient的”SSLPeerUnverifiedException: peer not authenticated”异常
     * 不用导入SSL证书
     * @param base
     * @return
     */
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

    public static void main(String[] args) throws Exception {
        String json = "{\"tenantId\":123456,\"userName\":\"tang\",\"password\":\"qwe123\"}";
        String result = HttpsClient.doPostByClient("https://localhost:8888/aep-clouddesktop-mgr/client/login",json,true);
        LOGGER.info("result: " + result);
    }

}