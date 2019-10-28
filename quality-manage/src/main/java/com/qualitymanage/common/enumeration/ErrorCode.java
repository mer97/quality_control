package com.qualitymanage.common.enumeration;

/**
 * @author lihai
 * Create Date: 2019-10-22
 */
public enum ErrorCode {
    /** 成功 */
    SUCCESS("200", "成功"),

    /** 操作失败 */
    FAIL("205", "操作失败"),

    /** 用户名或密码错误 */
    BAD_REQUEST("401", "用户名或密码错误"),

    /** 发生异常 */
    UNAUTHORIZED("401", "未授权"),

    /** 不允许访问 */
    FORBIDDEN("403", "不允许访问"),

    /** AuthCode错误 */
    INVALID_AUTHCODE("444", "无效的AuthCode"),

    /** 太频繁的调用 */
    TOO_FREQUENT("445", "太频繁的调用"),

    /** 未知的错误 */
    UNKNOWN_ERROR("499", "未知错误"),

    /** 系统错误 */
    SYSTEM_ERROR("500", "系统错误");

    private ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
