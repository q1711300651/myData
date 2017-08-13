package TestHttpUtil;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.URL;

public class TestMain {

    private static String httpPostData(String urlPath, String data, String charSet, String[] header, String contentType, String acceptType) {
        String result = null;
        URL url = null;
        HttpsURLConnection httpsUrlConnection = null;
        DataOutputStream out = null;
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
            httpsUrlConnection.setDefaultUseCaches(false);
            httpsUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpsUrlConnection.setRequestProperty("Content-Type", contentType);
            if (null != acceptType) {
                httpsUrlConnection.setRequestProperty("accept", acceptType);
            }
            httpsUrlConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            httpsUrlConnection.connect();
            System.out.println("end connect");

            out = new DataOutputStream(httpsUrlConnection.getOutputStream()); // utf-8编码
            out.writeBytes(data);
            out.flush();
            out.close();
            System.out.println("connect...done");
            int code = httpsUrlConnection.getResponseCode();
            System.out.println("code"+code);
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
            System.out.println("connection1");
            System.out.println(e);
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
                System.out.println("connection2");
                System.out.println(e);
            }
        }
        return result;
    }

    public void test22() throws Exception {
//        String dtdXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><!DOCTYPE cXML SYSTEM \"http://xml.cxml.org/schemas/cXML/1.2.014/cXML.dtd\"><cXML timestamp=\"2017-01-19T19:56:30\" payloadID=\"bac4b4a82e3342da919c7b427ee0fef2\"><Header><From><Credential domain=\"NetworkID\"><Identity>JDVEP4DIDI</Identity></Credential></From><To><Credential domain=\"NetworkID\"><Identity>Didipur</Identity></Credential></To><Sender><Credential domain=\"NetworkID\"><Identity>JDVEP4DIDI</Identity><SharedSecret>OGNmNGM3OGYtNWJhYi00ZTUwLTk0YTYtODAwZDVmYTU4NjMx</SharedSecret></Credential><UserAgent>JD VEP</UserAgent></Sender></Header><Message><PunchOutOrderMessage><BuyerCookie>3e3e68a280f45796cc24e59573e88ef7</BuyerCookie><PunchOutOrderMessageHeader operationAllowed=\"edit\"><Total><Money currency=\"CNY\">102.00</Money></Total><Shipping><Money currency=\"CNY\">0.00</Money><Description xml:lang=\"zh-CN\">运费</Description></Shipping></PunchOutOrderMessageHeader><ItemIn quantity=\"1\"><ItemID><SupplierPartID>102196</SupplierPartID><SupplierPartAuxiliaryID>46666778472</SupplierPartAuxiliaryID></ItemID><ItemDetail><UnitPrice><Money currency=\"CNY\">102.00</Money></UnitPrice><Description xml:lang=\"zh-CN\">维氏VICTORINOX瑞士军刀星座系列双鱼座0.6223.2PISC</Description><UnitOfMeasure>EA</UnitOfMeasure></ItemDetail></ItemIn><ItemIn quantity=\"1\"><ItemID><SupplierPartID>150706</SupplierPartID><SupplierPartAuxiliaryID>46666778472</SupplierPartAuxiliaryID></ItemID><ItemDetail><UnitPrice><Money currency=\"CNY\">0.00</Money></UnitPrice><Description xml:lang=\"zh-CN\">锐步Reebok女短袖T恤R537589 M码</Description><UnitOfMeasure>EA</UnitOfMeasure></ItemDetail></ItemIn></PunchOutOrderMessage></Message></cXML>";
//        String data = "{\"access_token\":\"b783296d49e8213b14967c177f493d\",\"touser\":\"991203069\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello World!\"}}";
        String data="";
        String access_token="b783296d49e8213b14967c177f493d";
        String touser="991203069";
        String msgtype="text";
        String text="{\"content\":\"Hello World!\"}";
        data+="access_token"+access_token;
        data+="&touser"+touser;
        data+="&msgtype"+msgtype;
        data+="&text"+touser;
//        String url="https://www.so.com";
//        String url = "https://dly.cib.com.cn:26062/plcm.html";
//        String url = "https://168.3.23.224:19016/auth/";
//        String url = "https://168.3.23.224:19016/auth/token.action?grant_type=client_credentials&client_id=e43d42ec18f84ff08aa3ccab6627b659&client_secret=5e53278e9591f3a393badb46c61c572";
        String url = "https://168.3.23.224:19016/custom/custom_send.action"+data;

        String result = "";
//                postXml(url, dtdXml, "UTF-8");

        result = httpPostData(url, data, Consts.UTF_8.name(), null, "application/json", "application/xml");
        System.out.println(result);
    }

    public  void test33(){
        JSONObject data=new JSONObject();
        data.put("touser","991203069");
        data.put("msgtype","text");
        JSONObject content = new JSONObject();
        content.put("content","hello world");
        data.put("text",content.toString());
        System.out.println(data.toString());
        String result = "";
        String access_token="b783296d49e8213b14967c177f493d";
        String url = "https://168.3.23.224:19016/custom/custom_send.action"+access_token;

        result = httpPostData(url, data.toString(), Consts.UTF_8.name(), null, "application/x-www-form-urlencoded", "application/xml");
        System.out.println(result);
    }




    public static void main(String[] args) throws Exception {
        TestMain main = new TestMain();
//        main.test22();
        main.test33();
    }
}  