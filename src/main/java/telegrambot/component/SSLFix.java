package telegrambot.component;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Component
@UtilityClass
public class SSLFix {

    @PostConstruct
    public static void execute(){
    TrustManager[] trustAllCerts =
        new TrustManager[] {
          new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {}

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {}
          }
        };

        SSLContext sc=null;
        try {
            sc = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier validHosts = (arg0, arg1) -> true;
        // All hosts will be valid
        HttpsURLConnection.setDefaultHostnameVerifier(validHosts);
    }

}
