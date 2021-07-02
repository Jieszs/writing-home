package com.jiesz.writinghome.common.bean;

import com.jiesz.writinghome.common.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public class Result<T> extends BaseBean {

    private static final long serialVersionUID = -2804195259517755050L;

    /**
     * 状态码
     */
    @Setter
    @Getter
    private int code;
    /**
     * 说明信息
     */
    @Setter
    @Getter
    private String message;
    /**
     * 结果数据
     */
    @Setter
    @Getter
    private T data;

    /**
     * 无参默认构造函数
     */
    public Result() {
    }

    /**
     * 有参构造函数
     */
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回，无数据，使用默认code
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS_CODE.getCode(), ResultCode.SUCCESS_CODE.getDesc(), null);
    }

    /**
     * 成功返回，无数据，使用传参code
     */
    public static <T> Result<T> success(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getDesc(), null);
    }

    /**
     * 成功返回，有数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS_CODE.getCode(), ResultCode.SUCCESS_CODE.getDesc(), data);
    }


    /**
     * 告警返回, 自定义返回值
     */
    public static <T> Result<T> warning(String message) {
        return new Result<>(ResultCode.WARNING_CODE.getCode(), message, null);
    }

    /**
     * 告警返回, 自定义返回值，有数据
     */
    public static <T> Result<T> warning(String message, T data) {
        return new Result<>(ResultCode.WARNING_CODE.getCode(), message, data);
    }

    /**
     * 失败返回，无数据
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getDesc(), null);
    }

    /**
     * 失败返回，有数据
     */
    public static <T> Result<T> fail(ResultCode resultCode, T data) {
        return new Result<>(resultCode.getCode(), resultCode.getDesc(), data);
    }

    /**
     * 失败返回, 自定义编码
     */
    public static <T> Result<T> fail(int code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * 判断接口是否成功返回
     */
    public static boolean isSuccess(Result<?> result) {
        return result.getCode() == ResultCode.SUCCESS_CODE.getCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result<?> result = (Result<?>) o;
        return new EqualsBuilder()
                .append(code, result.code)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .toHashCode();
    }
}
