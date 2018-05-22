package com.zmj.wkt.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class ZmjUtil {

    /**
     * 判断对象是否为空
     * @param o
     * @return
     */
    public static boolean isNullOrEmpty(Object o){
        if (o == null) {
            return true;
        }

        if (o instanceof CharSequence) {
            return ((CharSequence) o).length() == 0;
        }

        if (o instanceof Collection) {
            return ((Collection) o).isEmpty();
        }

        if (o instanceof Map) {
            return ((Map) o).isEmpty();
        }

        if (o instanceof Object[]) {
            Object[] object = (Object[]) o;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    /**
     * 复制对象的非空属性
     * @param newObj    源对象
     * @param oldObj    结果对象
     */
    public static void copyPropertiesIgnoreNull(Object newObj, Object oldObj) {
        BeanUtils.copyProperties(newObj, oldObj, getNullPropertyNames(newObj));
    }

    /**
     * 获取空属性名
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {

        final BeanWrapper src = new BeanWrapperImpl(source);

        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * Java文件操作 获取文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
    /**
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    //置空开始-------------------------


    public static String processNull(String var0) {
        return var0 == null ? "" : var0;
    }

    public static String processNull(Date var0) {
        return var0 == null ? "" : var0.toString();
    }

    public static String processNull(Long var0) {
        return var0 == null ? "" : var0.toString();
    }

    public static String processNull(Object var0) {
        return var0 == null ? "" : var0.toString();
    }

    public static String processNull(float var0) {
        return (double)var0 == 0.0D ? "" : String.valueOf(var0);
    }


    public static String processSpace(String var0) {
        return var0 == null ? "&nbsp;" : var0;
    }


    //置空结束-------------------------------

    public static String getNum19(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param paramMap 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while(it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 生成流水号
     * @return
     */
    public static String getOrderIdByUUId() {
        //最大支持1-9个集群机器部署
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        //有可能是负数
        if(hashCodeV < 0) {
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

    /**
     * 生成邀请码
     * @return
     */
    public static String getInvitation_code(){
        return "I_"+UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            System.out.println(getInvitation_code());
        }
        /*String s = "";
        System.out.println(ZmjUtil.isNullOrEmpty(s));*/
       // System.out.println(getNum19());
        //System.out.println(getOrderIdByUUId());
    }
}
