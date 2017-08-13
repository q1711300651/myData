package TestHttpUtil;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ww on 17/5/7.
 */
public class HttpClientUtil {
    public HttpClientUtil() {
    }


    public static String doPost(boolean ssl, String url, String charset, Map<String, String> data) throws KeyManagementException, NoSuchAlgorithmException, IOException {
        String result = null;
        HttpClient httpClient = null;
        if (ssl) {
            httpClient = new SSLClient();
        } else {
            httpClient = new DefaultHttpClient();
        }
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        if (data != null && !data.isEmpty()) {
            Iterator<Map.Entry<String, String>> iterator = data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                NameValuePair nameValue = new BasicNameValuePair(next.getKey(), next.getValue());
                parameters.add(nameValue);
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, charset);
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        if (httpResponse != null) {
            HttpEntity resultEntity = httpResponse.getEntity();
            if (resultEntity != null) {
                result = EntityUtils.toString(resultEntity, charset);
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(result);
                result = m.replaceAll("");
            }
        }
        return result;
    }

    @Test
    public void test() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        String url = "https://dly.cib.com.cn:26062";
        Map<String, String> createMap = new HashMap<String, String>();
        String paramStr = "{\"ddyljgmc\":\"\",\"type\":\"2\",\"pageNo\":\"1\",\"pageSize\":\"200\",\"TradeCode\":\"30011\"}";
        createMap.put("paramStr", paramStr);
        String httpOrgCreateTestRtn = HttpClientUtil.doPost(false, url, "utf-8", createMap);
        if (httpOrgCreateTestRtn != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(httpOrgCreateTestRtn);
            httpOrgCreateTestRtn = m.replaceAll("");
        }
        System.out.println(httpOrgCreateTestRtn);
    }

    /**
     * content-type类型为xml方式发送post请求
     *
     * @param urlPath
     * @param data
     * @param charSet
     * @return
     */
    public static String postXml(String urlPath, String data, String charSet) {
        String result = httpPostData(urlPath, data, charSet, null, "application/xml", "application/xml");
        return result;
    }

    private static String httpPostData(String urlPath, String data, String charSet, String[] header, String contentType, String acceptType) {
        String result = null;
        URL url = null;
        HttpsURLConnection httpsUrlConnection = null;
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        try {
            url = new URL(urlPath);
            httpsUrlConnection = (HttpsURLConnection) url.openConnection();
            httpsUrlConnection.setSSLSocketFactory(new TLSSocketConnectionFactory());
            httpsUrlConnection.setDoInput(true);
            httpsUrlConnection.setDoOutput(true);

            if (header != null) {
                for (int i = 0; i < header.length; i++) {
                    String[] content = header[i].split(":");
                    httpsUrlConnection.setRequestProperty(content[0], content[1]);
                }
            }

            httpsUrlConnection.setRequestMethod("POST");
            httpsUrlConnection.setRequestProperty("Content-Type", contentType);
            if (null != acceptType) {
                httpsUrlConnection.setRequestProperty("accept", acceptType);
            }

            httpsUrlConnection.connect();
            out = new OutputStreamWriter(httpsUrlConnection.getOutputStream(), charSet); // utf-8编码
            out.append(data);
            out.flush();
            out.close();

            int code = httpsUrlConnection.getResponseCode();

            if (code == 200) {
                // 读取响应
                int length = (int) httpsUrlConnection.getContentLength();// 获取长度
                InputStream is = httpsUrlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line);
                    line = reader.readLine();
                }
                result = builder.toString();
            } else {
                // TODO
            }
        } catch (Exception e) {
            // TODO
        } finally {
            url = null;
            if (httpsUrlConnection != null) {
                httpsUrlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // TODO
            }
        }
        return result;
    }

    @Test
    public void test2() throws Exception {
//        String dtdXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><!DOCTYPE cXML SYSTEM \"http://xml.cxml.org/schemas/cXML/1.2.014/cXML.dtd\"><cXML timestamp=\"2017-01-19T19:56:30\" payloadID=\"bac4b4a82e3342da919c7b427ee0fef2\"><Header><From><Credential domain=\"NetworkID\"><Identity>JDVEP4DIDI</Identity></Credential></From><To><Credential domain=\"NetworkID\"><Identity>Didipur</Identity></Credential></To><Sender><Credential domain=\"NetworkID\"><Identity>JDVEP4DIDI</Identity><SharedSecret>OGNmNGM3OGYtNWJhYi00ZTUwLTk0YTYtODAwZDVmYTU4NjMx</SharedSecret></Credential><UserAgent>JD VEP</UserAgent></Sender></Header><Message><PunchOutOrderMessage><BuyerCookie>3e3e68a280f45796cc24e59573e88ef7</BuyerCookie><PunchOutOrderMessageHeader operationAllowed=\"edit\"><Total><Money currency=\"CNY\">102.00</Money></Total><Shipping><Money currency=\"CNY\">0.00</Money><Description xml:lang=\"zh-CN\">运费</Description></Shipping></PunchOutOrderMessageHeader><ItemIn quantity=\"1\"><ItemID><SupplierPartID>102196</SupplierPartID><SupplierPartAuxiliaryID>46666778472</SupplierPartAuxiliaryID></ItemID><ItemDetail><UnitPrice><Money currency=\"CNY\">102.00</Money></UnitPrice><Description xml:lang=\"zh-CN\">维氏VICTORINOX瑞士军刀星座系列双鱼座0.6223.2PISC</Description><UnitOfMeasure>EA</UnitOfMeasure></ItemDetail></ItemIn><ItemIn quantity=\"1\"><ItemID><SupplierPartID>150706</SupplierPartID><SupplierPartAuxiliaryID>46666778472</SupplierPartAuxiliaryID></ItemID><ItemDetail><UnitPrice><Money currency=\"CNY\">0.00</Money></UnitPrice><Description xml:lang=\"zh-CN\">锐步Reebok女短袖T恤R537589 M码</Description><UnitOfMeasure>EA</UnitOfMeasure></ItemDetail></ItemIn></PunchOutOrderMessage></Message></cXML>";
        String dtdXml = "1111111";
        String url = "https://dly.cib.com.cn:26062/plcm.html";

        String result = "";
//                postXml(url, dtdXml, "UTF-8");

        result = httpPostData(url, dtdXml, Consts.UTF_8.name(), null, "application/xml", "application/xml");
        System.out.println(result);
    }
    public static void main(String[]args){
        System.out.println("hello");
    }
}
