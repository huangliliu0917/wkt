package com.zmj.wkt.utils.socket;

import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.SpringApplicationContextHolder;
import com.zmj.wkt.utils.ZmjUtil;

import java.io.*;
import java.net.Socket;
import java.util.Date;

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
 * @description :   socket线程类
 * ---------------------------------
 */
final class MySocketThread implements Runnable {
    private Socket socket;
    private IsoStrManager isoStrManager = (IsoStrManager) SpringApplicationContextHolder.getSpringBean("IsoStrManagerImpl");

    public MySocketThread(Socket var1) {
        this.socket = var1;
    }

    /**
     * 处理入参
     * @param inputString
     */
    private void deal(String inputString) {
        try {
            Server.log.info("----------------------");
            Server.log.info("客户端IP：" + this.socket.getInetAddress().toString() + "：" + this.socket.getPort());
            Server.log.info("请求时间：" + DateUtil.getNowTime());
            Server.log.info("请求内容：" + inputString);
            if (!ZmjUtil.processNull(inputString = isoStrManager.deal(inputString, this.socket)).equals("")) {
                OutputStream outputStream = this.socket.getOutputStream();
                PrintWriter printWriter;
                (printWriter = new PrintWriter(new OutputStreamWriter(outputStream, ConfigParser.server_responseEncoding))).println(inputString + ConfigParser.server_end);
                printWriter.flush();
                printWriter.close();
                outputStream.close();
            }

            Server.log.info("返回时间：" + DateUtil.getNowTime());
            Server.log.info("返回内容：" + inputString);
        } catch (Exception var4) {
            Server.log.warn("业务发生错误：" + var4.getMessage());
        }
    }

    /**
     * run方法
     */
    @Override
    public final void run() {
        try {
            if (ConfigParser.longConnection && Server.socket_map.get(this.socket.getInetAddress().toString() + this.socket.getPort()) == null) {
                Server.socket_map.put(this.socket.getInetAddress().toString() + this.socket.getPort(), this.socket);
                Server.socket_map_time.put(this.socket.getInetAddress().toString() + this.socket.getPort(), new Date());
            }

            InputStream inputStream = this.socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, ConfigParser.server_requestEncoding));
            StringBuffer stringBuffer = new StringBuffer();
            String s = null;
            if (ConfigParser.longConnection) {
                while(true) {
                    if (ConfigParser.longConnection && Server.socket_map.get(this.socket.getInetAddress().toString() + this.socket.getPort()) == null) {
                        bufferedReader.close();
                        break;
                    }

                    if (!this.socket.isConnected()) {
                        break;
                    }

                    if (!(s = ZmjUtil.processNull(bufferedReader.readLine())).equals("")) {
                        Server.socket_map_time.put(this.socket.getInetAddress().toString() + this.socket.getPort(), new Date());
                        stringBuffer.append(s);
                        if (stringBuffer.toString().lastIndexOf(ConfigParser.server_end) != -1) {
                            this.deal(stringBuffer.toString().lastIndexOf(ConfigParser.server_end) != -1 ? stringBuffer.toString().substring(0, stringBuffer.length() - ConfigParser.server_end.length()) : stringBuffer.toString());
                            stringBuffer = new StringBuffer();
                        }
                    }
                }
            } else {
                while(!(s = ZmjUtil.processNull(bufferedReader.readLine())).equals("")) {
                    stringBuffer.append(s);
                    if (s.lastIndexOf(ConfigParser.server_end) != -1 || stringBuffer.toString().lastIndexOf(ConfigParser.server_end) != -1) {
                        break;
                    }
                }

                if (!stringBuffer.equals("")) {
                    this.deal(stringBuffer.toString().lastIndexOf(ConfigParser.server_end) != -1 ? stringBuffer.toString().substring(0, stringBuffer.length() - ConfigParser.server_end.length()) : stringBuffer.toString());
                }
            }

            bufferedReader.close();
            inputStream.close();
        } catch (Exception e) {
            Server.log.warn("请求地址【" + this.socket.getInetAddress().toString() + "：" + this.socket.getPort() + "】发生错误：" + e.getMessage());

            try {
                if (ConfigParser.longConnection && this.socket != null) {
                    this.socket.close();
                    Server.socket_map.remove(this.socket.getInetAddress().toString() + this.socket.getPort());
                    Server.socket_map_time.remove(this.socket.getInetAddress().toString() + this.socket.getPort());
                    Server.log.warn("断开连接【" + this.socket.getInetAddress().toString() + "：" + this.socket.getPort() + "】");
                }
            } catch (Exception e1) {
                Server.log.warn("清理连接发生错误：" + e1.getMessage());
            }
        } finally {
            try {
                if (!ConfigParser.longConnection && this.socket != null) {
                    this.socket.close();
                }
            } catch (Exception e) {
                Server.log.warn("断开连接【" + this.socket.getInetAddress().toString() + "：" + this.socket.getPort() + "】发生错误：" + e.getMessage());
            }

        }

    }
}
