package com.zmj.wkt.utils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author : zmj
 * @description :
 * ---------------------------------
 */
public class HttpsClientUtil implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        // TODO Auto-generated method stub

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 处理https GET/POST请求
     * 请求地址、请求方法、参数
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static String httpsRequest(String requestUrl,String requestMethod,String outputStr){
        StringBuffer buffer=null;
        try{
            //创建SSLContext
            SSLContext sslContext=SSLContext.getInstance("SSL");
            TrustManager[] tm={new HttpsClientUtil()};
            //初始化
            sslContext.init(null, tm, new java.security.SecureRandom());;
            //获取SSLSocketFactory对象
            SSLSocketFactory ssf=sslContext.getSocketFactory();
            URL url=new URL(requestUrl);
            HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            //往服务器端写内容
            if(null!=outputStr){
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
