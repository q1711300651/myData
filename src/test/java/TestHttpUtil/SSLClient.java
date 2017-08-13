package TestHttpUtil;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by ww on 17/5/7.
 */
public class SSLClient extends DefaultHttpClient {
    public SSLClient() throws NoSuchAlgorithmException, KeyManagementException {
        super();
        SSLContext sslContext=SSLContext.getInstance("TLS");
        X509TrustManager x509TrustManager=new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
                //这里ssl跳过
                return null;
            }
        };
        sslContext.init(null,new TrustManager[]{x509TrustManager},null);
        SSLSocketFactory sslSocketFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        SchemeRegistry schemeRegistry=this.getConnectionManager().getSchemeRegistry();
        schemeRegistry.register(new Scheme("https",443,sslSocketFactory));
    }
}
