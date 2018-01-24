package com.zmj.wkt.utils.socket;

import com.zmj.wkt.utils.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
 * @Description :   socket接口服务的初始化和启动
 * ---------------------------------
 * @author : zmj
 */
public class Server {
    public static Log log = LogFactory.getLog(Server.class.getName());
    public static Map socket_map = new HashMap();
    public static Map socket_map_time = new HashMap();
    private static ServerSocket socket;
    private static ExecutorService executorService;
    private static Timer timer = new Timer();
    @Resource
    IsoStrManager manager ;
    //ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor();
    public Server() {
    }

    /**
     * 初始化
     * @throws IOException
     */
    public static void init() throws IOException {
        //初始化配置
        ConfigParser.initConfit();
        //创建ServerSocket服务实例
        socket = new ServerSocket(ConfigParser.server_port);
        //服务创建线程池
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * ConfigParser.server_pool_size);
    }

    /**
     * 启动方法
     */
    public static void start() {
        try {
            log.info("----------------------");
            log.info("正在启动Socket服务");
            init();
            log.info("Socket服务端口【" + ConfigParser.server_port + "】已启动...");
            if (ConfigParser.longConnection) {
                timer.schedule(new MyTimeTask(), 0L, (long)(ConfigParser.heartBeatTime * 1000));
                log.info("启动心跳服务，每间隔" + ConfigParser.heartBeatTime + "秒清理一次，断开闲置超过" + ConfigParser.minConnectionTime + "秒的客户端连接...");
            }
            log.info("Socket服务正在服务中...");
            log.info("----------------------");

            while(true) {
                Socket socket = null;
                try {
                    //接收信息阻塞方法
                    socket = Server.socket.accept();
                    //线程池开始分配线程工作
                    executorService.execute(new MySocketThread(socket));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            log.warn("Socket服务端发生错误：" + e1.getMessage());
        }
    }

    /**
     * 清楚不适用的socket链接
     */
    public static void ClearNotUseSocket() {
        if (ConfigParser.longConnection) {
            Object[] sockets = socket_map_time.keySet().toArray();

            for(int i = 0; i < sockets.length; ++i) {
                if (DateUtil.getSecondsFromTwoDate((Date)socket_map_time.get(sockets[i]), new Date()) > (long)ConfigParser.minConnectionTime) {
                    Socket socket = (Socket)socket_map.get(sockets[i]);
                    try {
                        socket.close();
                        socket_map.remove(sockets[i]);
                        socket_map_time.remove(sockets[i]);
                    } catch (Exception var4) {
                        log.warn("关闭闲置连接【" + socket.getInetAddress().toString() + "：" + socket.getPort() + "】发生错误：" + var4.getMessage());
                    }
                }
            }
        }

    }

    /**
     * 停止socket服务方法
     */
    public static void stop() {
        try {
            if (ConfigParser.longConnection) {
                timer.cancel();
            }
            Object[] sockets = socket_map_time.keySet().toArray();
            for(int i = 0; i < sockets.length; ++i) {
                ((Socket)socket_map.get(sockets[i])).close();
                socket_map.remove(sockets[i]);
                socket_map_time.remove(sockets[i]);
            }
            socket.close();
            executorService.shutdown();
            log.info("----------------------");
            log.info("Socket服务已关闭...");
            log.info("----------------------");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("----------------------");
            log.info("Socket服务关闭出错...");
            log.info(e.getMessage());
            log.info("----------------------");
        }
    }

    public IsoStrManager getManager() {
        return manager;
    }

    public void setManager(IsoStrManager manager) {
        this.manager = manager;
    }

    public static void main(String[] var0) {
        start();
    }
}
