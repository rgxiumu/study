package com.whw.test;

import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.Console;
import java.net.URL;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * -Djavax.net.debug=all
 */
public class Texxxx {


    @Test
    public void texxxxx() throws Exception{
        URL url = new URL("https://www.sina.com.cn/");
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.connect();
        conn.disconnect();
    }
    @Test
    public void tex2() throws Exception{
        boolean f = ( 1 & 0 ) == 1 ;
        System.out.println(f);
    }

    private static String getThumbPrint(X509Certificate cert) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] der = cert.getEncoded();
        md.update(der);
        byte[] digest = md.digest();
        return bytesToHexString(digest);
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
