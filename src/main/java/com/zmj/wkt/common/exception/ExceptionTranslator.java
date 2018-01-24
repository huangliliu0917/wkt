package com.zmj.wkt.common.exception;

import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 * @author zmj
 */
@ControllerAdvice
public class ExceptionTranslator {
    public static final String DEFAULT_ERROR_VIEW = "exception_error";
    private static final Logger logger  = LoggerFactory.getLogger(ExceptionTranslator.class);
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public RestfulResult handle(CommonException e){
        if(ZmjUtil.isNullOrEmpty(e.getCode())){
            logger.info(RestfulResultUtils.error(ErrorCode.UNKNOWNS_ERROR.getCode(), e.getMessage().trim()).toString());
            return RestfulResultUtils.error(ErrorCode.UNKNOWNS_ERROR.getCode(),e.getMessage().trim());
        }
        else{
            logger.info(RestfulResultUtils.error(e.getCode(), e.getMessage().trim()).toString());
            return RestfulResultUtils.error(e.getCode(),e.getMessage().trim());
        }
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public RestfulResult handle(HttpRequestMethodNotSupportedException e){
        logger.info(RestfulResultUtils.error(ErrorCode.HTTPREQUESTMETHODNOTSUPPORTED.getCode(), ErrorCode.HTTPREQUESTMETHODNOTSUPPORTED.getDescription() + ":" + e.getMessage().trim()).toString());
        return RestfulResultUtils.error(ErrorCode.HTTPREQUESTMETHODNOTSUPPORTED.getCode(),ErrorCode.HTTPREQUESTMETHODNOTSUPPORTED.getDescription()+":"+e.getMessage().trim());
    }
}
