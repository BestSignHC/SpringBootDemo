package com.hecheng.exception;

/**
 * Created by ChengH on 2018/3/21.
 */
public class MyException extends Exception {
    private int code = 0;
    private String errMsg = "";

    public MyException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

    public MyException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
