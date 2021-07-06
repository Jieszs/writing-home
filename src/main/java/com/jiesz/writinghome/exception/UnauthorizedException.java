package com.jiesz.writinghome.exception;

public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    /**
     * 错误信息
     */
    protected String errorMsg;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
