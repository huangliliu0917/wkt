package com.zmj.wkt.common.interceptor;

import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
public class CommonInterceptor implements HandlerInterceptor {
    private static final Logger logger  = LoggerFactory.getLogger(CommonInterceptor.class);

    /**
     * 拦截前处理
     * @param request
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            //用于内部传输的Map
            Map<String, Object> map = new HashMap();
            //入参
            JSONObject json = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            //出参
            JSONObject object = new JSONObject();
            //url
            logger.info("url={}",request.getRequestURL());
            //method
            logger.info("method={}",request.getMethod());
            //ip
            logger.info("ip={}",request.getRemoteAddr());
            String inJsonObjectStr = initRequestParam(request, request.getMethod());
            System.out.println("inJsonObjectStr:"+inJsonObjectStr);
            //如果入参为null则跳出
            if (inJsonObjectStr == null || inJsonObjectStr.equals("")) {
                return true;
            }
            //获取json字符串的第一个字符
            String firstChar = ZmjUtil.processNull(inJsonObjectStr.substring(0, 1));
            //判断是json对象，还是json数组对象
            if (firstChar.equals("{")) {
                json = JSONObject.fromObject(inJsonObjectStr);
            } else if (firstChar.equals("[")) {
                jsonArray = JSONArray.fromObject(inJsonObjectStr);
            }
            //接口请求码
            String reqCode = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
            //如果是json对象
            if (json != null) {
                logger.info(reqCode + "入参：" + json.toString());
                map.put("json", json);
                map.put("reqCode", reqCode);
                map.put("object", object);
                request.setAttribute("baseMap", map);
            }
            return true;
        }catch (Exception e){
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 拦截后处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 获取客户端输入参数
     */
    public String initRequestParam(HttpServletRequest request, String method) {
        try {
            if (ZmjUtil.processNull(method).equals("POST")) {
                StringBuffer sb = new StringBuffer();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(
                        request.getInputStream(), "UTF-8"));
                String line = "";
                while ((line = buffer.readLine()) != null) {
                    sb.append(line);
                }
                buffer.close();
                return ZmjUtil.processNull(sb.toString());
            } else if (ZmjUtil.processNull(method).equals("GET")) {
                Iterator ite = request.getParameterMap().keySet().iterator();
                while (ite.hasNext()) {
                    String sb = request.getParameter((String) ite.next());
                    if (ZmjUtil.processNull(sb).equals("")) {
                        continue;
                    }
                    String firstChar = ZmjUtil.processNull(sb.substring(0, 1));
                    //直到拿到jsonObject或jsonArray为止
                    if (firstChar.equals("{") || firstChar.equals("[")) {
                        return sb.toString();
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e) {

        }
        return "";
    }
}
