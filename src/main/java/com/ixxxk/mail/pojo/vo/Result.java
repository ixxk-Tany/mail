package com.ixxxk.mail.pojo.vo;


import lombok.Data;

import java.io.Serializable;
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1355258441540672843L;
    private String msg;
    private String code;
    private T data;

    private static final String SUCCESS_MSG = "success";
    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_MSG = "error";

    public Result(String msg, String code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new <T>Result<T>(SUCCESS_MSG, SUCCESS_CODE, null);
    }

    public static <T> Result<T> success(T data) {
        return new <T>Result<T>(SUCCESS_MSG, SUCCESS_CODE, data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new <T>Result<T>(msg, SUCCESS_CODE, data);
    }

    public static <T> Result<T> error(String errorCode, String errorMsg) {
        return new <T>Result<T>(errorMsg, errorCode, null);
    }
}