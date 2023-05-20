package telegrambot.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Component
public class SSLFix {
  /*
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
  */

  @PostConstruct
  public static void addCertificateToTrustStore()
      throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {

    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

    FileInputStream fileInputStream =
        new FileInputStream(System.getProperty("java.home") + "/lib/security/cacerts");
    keyStore.load(fileInputStream, "changeit".toCharArray());
    fileInputStream.close();

    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

    FileInputStream certificateInputStream = new FileInputStream("kfcby-live.iprojectdev.com");
    X509Certificate certificate =
        (X509Certificate) certificateFactory.generateCertificate(certificateInputStream);
    certificateInputStream.close();

    keyStore.setCertificateEntry("kfc", certificate);

    FileOutputStream fileOutputStream =
        new FileOutputStream(System.getProperty("java.home") + "/lib/security/cacerts");
    keyStore.store(fileOutputStream, "changeit".toCharArray());
    fileOutputStream.close();
  }
}
