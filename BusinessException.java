package com.jiahao;

// 基类 BusinessException
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}
