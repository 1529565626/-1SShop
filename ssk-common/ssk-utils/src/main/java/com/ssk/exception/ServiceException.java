package com.ssk.exception;

/**
 * 统一的服务异常类
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

}
