package com.ssoserver.common.util;

import com.ssoserver.common.bean.PageInfo;
import com.ssoserver.common.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Map;

/**
 * Restful统一Json响应对象封装
 *
 * @author lihai
 * Create Date: 2019-10-21
 */
public class ResultUtil<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final static String SUCCESS_CODE = "200";

    /**
     * 返回状态码
     */
    private String status;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回内容
     */
    private T data;

    /**
     * 分页信息
     */
    private PageInfo page;

    /**
     * 其他内容
     */
    private Map<String, Object> ext;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public ResultUtil(){
        this.status = SUCCESS_CODE;
        this.message = "SUCCESS";
    }

    public ResultUtil(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultUtil(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResultUtil(String status, String message, T data, Map<String, Object> ext) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.ext = ext;
    }

    public ResultUtil(String status, String message, T data, PageInfo pageInfo) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.page = pageInfo;
    }

    public ResultUtil(String status, String message, T data, Map<String, Object> ext, PageInfo pageInfo) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.ext = ext;
        this.page = pageInfo;
    }

    public ResultUtil(String status, String message, T data, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        this.status = status;
        this.message = message;
        this.data = data;
        this.page = pageInfo;
    }

    public ResultUtil(String status, String message, T data, Map<String, Object> ext, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        this.status = status;
        this.message = message;
        this.data = data;
        this.ext = ext;
        this.page = pageInfo;
    }

    //快速返回成功
    public static <T>ResultUtil success(){
        return new ResultUtil<T>(SUCCESS_CODE,"请求成功",null);
    }

    public static <T>ResultUtil success(T result){
        return new ResultUtil<T>(SUCCESS_CODE,"请求成功",result);
    }

    public static <T>ResultUtil success(String message, T result){
        return new ResultUtil<T>(SUCCESS_CODE,message,result);
    }

    public static <T>ResultUtil success(String message, T result, Map<String, Object> extra){
        return new ResultUtil<T>(SUCCESS_CODE,message,result, extra);
    }

    public static <T>ResultUtil success(T result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new ResultUtil<T>(SUCCESS_CODE,"请求成功",result, pageInfo);
    }

    public static <T>ResultUtil success(T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new ResultUtil<T>(SUCCESS_CODE,"请求成功",result, extra,pageInfo);
    }

    public static <T>ResultUtil success(String message, T result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new ResultUtil<T>(SUCCESS_CODE,message,result,pageInfo);
    }

    public static <T>ResultUtil success(String message, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new ResultUtil<T>(SUCCESS_CODE,message,result, extra,pageInfo);
    }

    //快速返回失败状态
    public static <T>ResultUtil fail(){
        return new ResultUtil<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage());
    }

    public static <T>ResultUtil fail(T result){
        return new ResultUtil<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage(),result);
    }

    public <T>ResultUtil fail(String message, T result){
        return new ResultUtil<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,result);
    }

    public <T>ResultUtil fail(String message, T result, Map<String, Object> extra){
        return new ResultUtil<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,result, extra);
    }

    public static <T>ResultUtil fail(ErrorCode errorCode){
        return new ResultUtil<T>(errorCode.getCode(),errorCode.getMessage());
    }

    public static <T>ResultUtil fail(ErrorCode errorCode, T result){
        return new ResultUtil<T>(errorCode.getCode(),errorCode.getMessage(),result);
    }

    public static <T>ResultUtil fail(ErrorCode errorCode, String message, T result){
        return new ResultUtil<T>(errorCode.getCode(),message,result);
    }

    public static <T>ResultUtil fail(ErrorCode errorCode, String message, T result, Map<String, Object> extra){
        return new ResultUtil<T>(errorCode.getCode(),message,result, extra);
    }

    //快速返回自定义状态码
    public static <T>ResultUtil result(String statusCode, String message){
        return new ResultUtil<T>(statusCode,message);
    }

    public static <T>ResultUtil result(String statusCode, String message, T result){
        return new ResultUtil<T>(statusCode,message,result);
    }

    public static <T>ResultUtil result(String statusCode, String message, T result, Map<String, Object> extra){
        return new ResultUtil<T>(statusCode,message,result, extra);
    }

    public static <T>ResultUtil result(String statusCode, String message, T result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new ResultUtil<T>(statusCode,message,result, pageInfo);
    }

    public static <T>ResultUtil result(String statusCode, String message, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new ResultUtil<T>(statusCode,message,result, extra,pageInfo);
    }

    //快速返回Http状态
    public static <T>ResultUtil httpStatus(HttpStatus httpStatus, String message){
        return result(httpStatus.toString(),message);
    }

    public static <T>ResultUtil httpStatus(HttpStatus httpStatus, String message, T result){
        return result(httpStatus.toString(),message,result);
    }

    public static <T>ResultUtil httpStatus(HttpStatus httpStatus, String message, T result, Map<String, Object> extra){
        return result(httpStatus.toString(),message,result, extra);
    }

    public static <T>ResultUtil httpStatus(HttpStatus httpStatus, String message, T result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return result(httpStatus.toString(),message,result, total, pageNo, pageSize);
    }

    public static <T>ResultUtil httpStatus(HttpStatus httpStatus, String message, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return result(httpStatus.toString(),message,result, extra, total, pageNo, pageSize);
    }

}
