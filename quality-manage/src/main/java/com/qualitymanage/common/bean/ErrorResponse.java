package com.qualitymanage.common.bean;

/**
 * 服务执行异常的返回对象。
 *
 * @author lihai
 * Create Date: 2019-10-21
 */
public class ErrorResponse {

    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
