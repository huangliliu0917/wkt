package com.zmj.wkt.common.aspect;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Sys_log;
import com.zmj.wkt.mapper.Sys_logMapper;
import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.utils.ZmjUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 所有control的切面，记日志，记录返回
 * @author zmj
 */
@Aspect
@Component
public class ControllerAspect extends CommonController{
    private static final Logger logger  = LoggerFactory.getLogger(ControllerAspect.class);

    @Autowired
    Sys_logMapper sys_logMapper;

    Sys_log sys_log = new Sys_log();
    long startTime;
    long endTime;

    @Pointcut(value = "execution(public * com.zmj.wkt.controller.*.*(..))")
    public void jsonController(){

    }

    /**
     * 记录进入control的参数
     * @param joinPoint
     */
    @Before("jsonController()")
    public void doBefore(JoinPoint joinPoint){
        startTime= System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURL());
        sys_log.setUrl(request.getRequestURL().toString());
        //method
        logger.info("method={}",request.getMethod());
        sys_log.setMethod(request.getMethod());
        //ip
        logger.info("ip={}",getIpAddr(request));
        sys_log.setIp(getIpAddr(request));
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        sys_log.setClass_method(request.getMethod());
        //参数
        logger.info("args={}", Arrays.toString(joinPoint.getArgs()));
        sys_log.setArgs( Arrays.toString(joinPoint.getArgs()));

        //校验参数
        Arrays.stream(joinPoint.getArgs())
                .filter(arg->arg instanceof BeanPropertyBindingResult)
                .findFirst()
                .ifPresent(arg->
                    ((BeanPropertyBindingResult) arg).getAllErrors().stream()
                            .findFirst()
                            .ifPresent((objectError) -> {
                                logger.warn("args异常：{}:{}",ErrorCode.VERIFY_ERROR.getDescription(), objectError.getDefaultMessage());
                                throw new CommonException(ErrorCode.VERIFY_ERROR,objectError.getDefaultMessage());
                            })
                );
    }

    /**
     * 记录response
     * @param object
     */
    @AfterReturning(pointcut = "jsonController()",returning = "object")
    public void doAfterReturning(Object object){
        endTime= System.currentTimeMillis();
        logger.info("处理时间={}ms", endTime-startTime);
        if(!ZmjUtil.isNullOrEmpty(object)&&object instanceof RestfulResult){
            logger.info("response={}", object.toString());
        }else if(!ZmjUtil.isNullOrEmpty(object)&&object instanceof String){
            logger.info("response={}", object.toString());
        }else {
            logger.info("response={}", object);
        }

        sys_log.setRunTime(endTime-startTime);
        try {
            sys_log.setUsername(this.getThisUser().getUserName());
        } catch (Exception e) {
            sys_log.setUsername("null");
        }
        if (object.toString().length()>500){
            sys_log.setResponse(object.toString().substring(0,500));
        }else{
            sys_log.setResponse(object.toString());
        }
        sys_log.setEndTime(DateUtil.getNowTimestamp());
        sys_logMapper.insert(sys_log);
    }

    /**
     * 获取真实ip地址
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
