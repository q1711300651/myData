package TestHttpUtil;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ww on 17/5/20.
 */
public class HttpUrlConnection {

    public static void main(String[]args) throws IOException {
        MultipartEntity multipartEntity = new MultipartEntity();
        ByteArrayBody byteArrayBody = new ByteArrayBody("a".getBytes(), "a1");
        multipartEntity.addPart("media",byteArrayBody);
        Header contentEncoding = multipartEntity.getContentEncoding();
        long contentLength = multipartEntity.getContentLength();
        Header contentType = multipartEntity.getContentType();
        String name = contentType.getName();
        String value = contentType.getValue();
        System.out.println();

    }

    /**
     * 上传文件,返回mediaid
     * @return
     */
    public String uploadMedia(String serverUrl, String accessToken, String type, String filePath) throws MalformedURLException {
        //返回结果
        String result = "";
        URL url=null;
        //泰岳的接口地址
        String urlPath = "自己补全"+serverUrl+accessToken;
        //转换后的数据,封装在httpEntity
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); // 设置浏览器兼容模式
        multipartEntityBuilder.addTextBody("type", type);
        File media = new File(filePath);
        if (!media.exists() || !media.isFile()) {
            System.out.println("文件" + filePath + "不存在");
            return result;
        }
        multipartEntityBuilder.addBinaryBody("media", media);
        HttpEntity build = multipartEntityBuilder.build();


        //开始urlConnection
        HttpsURLConnection httpsUrlConnection = null;
        DataOutputStream out = null;
        BufferedReader reader = null;
        try {
            url = new URL(urlPath);
            httpsUrlConnection = (HttpsURLConnection) url.openConnection();
            httpsUrlConnection.setSSLSocketFactory(new TLSSocketConnectionFactory());
            httpsUrlConnection.setDoInput(true);
            httpsUrlConnection.setDoOutput(true);
            httpsUrlConnection.setReadTimeout(10000);
            httpsUrlConnection.setConnectTimeout(15000);

            httpsUrlConnection.setRequestMethod("POST");
            httpsUrlConnection.setDefaultUseCaches(false);
            httpsUrlConnection.setRequestProperty("Connection", "Keep-Alive");
           //注意这里传输reqEntity数据,所有数据都是封在reqEntity里
            httpsUrlConnection.addRequestProperty(build.getContentType().getName(), build.getContentType().getValue());

            httpsUrlConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

            OutputStream os = httpsUrlConnection.getOutputStream();
            build.writeTo(httpsUrlConnection.getOutputStream());
            os.close();
            httpsUrlConnection.connect();

            int code = httpsUrlConnection.getResponseCode();
            if (code == 200) {
                // 读取响应
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
        } finally {
            url = null;
            if (httpsUrlConnection != null) {
                httpsUrlConnection.disconnect();
            }
        }
        return result;


//        result = mapper.writeValueAsString(bug);
//        ResponseUtils.renderJson(response, result);
    }



}
