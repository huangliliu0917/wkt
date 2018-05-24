package com.zmj.wkt.common.validate.exception;

import java.io.Serializable;

/**
 * 验证异常
 * @author zhaomingjie
 */
public class ValidationException extends Exception implements Serializable{
    private static final long serialVersionUID = -2902025037137372147L;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
