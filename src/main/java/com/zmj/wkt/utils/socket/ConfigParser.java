
package com.zmj.wkt.utils.socket;

import java.io.File;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  socket接口服务的配置类
 * @author zmj
 */
@Component
@ConfigurationProperties
public class ConfigParser {
    public static boolean initIsoStr = false;
    @Value("${ISOSTRconfigUrl}")
    public static String ISOSTRconfigUrl ;
    public static boolean longConnection = true;
    public static int heartBeatTime = 60;
    public static int minConnectionTime = 600;
    public static int server_port = 88;
    public static int server_pool_size = 100;
    public static String server_requestEncoding = "UTF-8";
    public static String server_responseEncoding = "UTF-8";
    public static String server_end;
    public static String client_ip;
    public static int client_port = 88;
    public static int client_timeout = 60000;
    public static String client_requestEncoding = "UTF-8";
    public static String client_responseEncoding = "UTF-8";
    public static String client_end;

    public ConfigParser() {
    }

    public static int getClient_port() {
        return client_port;
    }

    public static void setClient_port(int var0) {
        client_port = var0;
    }

    public static String getClient_ip() {
        return client_ip;
    }

    public static void setClient_ip(String var0) {
        client_ip = var0;
    }

    public static int getClient_timeout() {
        return client_timeout;
    }

    public static void setClient_timeout(int var0) {
        client_timeout = var0;
    }

    public static String getServer_end() {
        return server_end;
    }

    public static void setServer_end(String var0) {
        server_end = var0;
    }

    public static String getServer_requestEncoding() {
        return server_requestEncoding;
    }

    public static void setServer_requestEncoding(String var0) {
        server_requestEncoding = var0;
    }

    public static String getServer_responseEncoding() {
        return server_responseEncoding;
    }

    public static void setServer_responseEncoding(String var0) {
        server_responseEncoding = var0;
    }

    public static String getClient_end() {
        return client_end;
    }

    public static void setClient_end(String var0) {
        client_end = var0;
    }

    public static String getClient_requestEncoding() {
        return client_requestEncoding;
    }

    public static void setClient_requestEncoding(String var0) {
        client_requestEncoding = var0;
    }

    public static String getClient_responseEncoding() {
        return client_responseEncoding;
    }

    public static void setClient_responseEncoding(String var0) {
        client_responseEncoding = var0;
    }

    public static int getHeartBeatTime() {
        return heartBeatTime;
    }

    public static void setHeartBeatTime(int var0) {
        heartBeatTime = var0;
    }

    public static boolean isInitIsoStr() {
        return initIsoStr;
    }

    public static void setInitIsoStr(boolean var0) {
        initIsoStr = var0;
    }

    public static boolean isLongConnection() {
        return longConnection;
    }

    public static void setLongConnection(boolean var0) {
        longConnection = var0;
    }

    public static int getMinConnectionTime() {
        return minConnectionTime;
    }

    public static void setMinConnectionTime(int var0) {
        minConnectionTime = var0;
    }

    public static int getServer_pool_size() {
        return server_pool_size;
    }

    public static void setServer_pool_size(int var0) {
        server_pool_size = var0;
    }

    public static int getServer_port() {
        return server_port;
    }

    public static void setServer_port(int var0) {
        server_port = var0;
    }

    public static String getISOSTRconfigUrl() {
        return ISOSTRconfigUrl;
    }

    public static void setISOSTRconfigUrl(String var0) {
        ISOSTRconfigUrl = var0;
    }

    public static void initConfit() {
        try {
            if (!initIsoStr) {
                SAXBuilder saxBuilder = new SAXBuilder();
                Document document;
                if (!ISOSTRconfigUrl.equals("")) {
                    document = saxBuilder.build(ISOSTRconfigUrl);
                } else if ((new File(saxBuilder.getClass().getClassLoader().getResource(".").getPath() + "/ISOSTRconfig.xml")).exists()) {
                    document = saxBuilder.build(saxBuilder.getClass().getClassLoader().getResource(".").getPath() + "/ISOSTRconfig.xml");
                } else if ((new File(saxBuilder.getClass().getResource("/").getPath() + "ISOSTRconfig.xml")).exists()) {
                    document = saxBuilder.build(saxBuilder.getClass().getResource("/").getPath() + "ISOSTRconfig.xml");
                } else if((new File(saxBuilder.getClass()+ "ISOSTRconfig.xml")).exists()){
                    document = saxBuilder.build(saxBuilder.getClass() + "ISOSTRconfig.xml");
                }
                else {
                    document = saxBuilder.build("file:///" + saxBuilder.getClass().getClassLoader().getResource("/") + "ISOSTRconfig.xml");
                }

                Element element;
                Element element1;
                if ((element = (element1 = document.getRootElement()).getChild("server")) != null) {
                    longConnection = element.getAttributeValue("longConnection").equals("true");
                    heartBeatTime = Integer.valueOf(element.getAttributeValue("heartBeatTime")).intValue();
                    minConnectionTime = Integer.valueOf(element.getAttributeValue("minConnectionTime")).intValue();
                    server_port = Integer.valueOf(element.getAttributeValue("port")).intValue();
                    server_pool_size = Integer.valueOf(element.getAttributeValue("poolSize")).intValue();
                    server_requestEncoding = element.getAttributeValue("requestEncoding");
                    server_responseEncoding = element.getAttributeValue("responseEncoding");
                    server_end = element.getAttributeValue("end");
                }

                if ((element1 = element1.getChild("client")) != null) {
                    client_ip = element1.getAttributeValue("ip");
                    client_port = Integer.valueOf(element1.getAttributeValue("port")).intValue();
                    client_timeout = Integer.valueOf(element1.getAttributeValue("timeout")).intValue();
                    client_requestEncoding = element1.getAttributeValue("requestEncoding");
                    client_responseEncoding = element1.getAttributeValue("responseEncoding");
                    client_end = element1.getAttributeValue("end");
                }

                initIsoStr = true;
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] strings) {
        SAXBuilder saxBuilder = new SAXBuilder();
        System.out.println(saxBuilder.getClass().getClassLoader().getResource(".").getPath() + "ISOSTRconfig.xml");
        System.out.println(saxBuilder.getClass().getResource("/").getPath() + "ISOSTRconfig.xml");
    }
}
