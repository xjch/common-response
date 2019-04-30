package com.github.xjch.common.response;

import java.io.Serializable;

/**
 * User: xjch
 * Date: 2019/4/29
 * Project: common-response
 * Version: 1.0.0
 * Description: 公共的响应对象
 * <p>
 *     1、默认构造响应对象
 *     2、根据响应数据构造响应对象
 *     3、根据错误码，错误描述，构造响应对象
 *     4、重写toString方法
 * </p>
 */
@SuppressWarnings({"unused", "SameParameterValue"})
public class Result<T> implements Serializable {

    /** 请求成功与否。true:请求成功；false请求失败 */
    private boolean reqResult;
    /** 请求返回的数据。 */
    private T respResult;
    /** 请求返回的错误编码。 00000000表示success，非00000000表示失败错误 */
    private String respErrorCode;
    /** 请求失败返回的错误信息 */
    private String respErrorMsg;

    /**
     *  java 为每个不明确定义至少一个类的类添加了一个隐式的公共构造函数。
     *  应该定义至少一个非公共构造函数，如下为显式的公共构造函数
     */
    public Result(){
    }

    /**
     * 根据响应数据构造响应对象
     * @param respResult 请求返回的数据
     */
    public Result(T respResult){
        this.reqResult = true;
        this.respResult = respResult;
    }

    /**
     * 根据错误码，错误描述，构造响应对象
     * @param respErrorCode 错误编码
     * @param respErrorMsg 错误信息
     */
    public Result(String respErrorCode, String respErrorMsg){
        this.reqResult = false;
        this.respErrorCode = respErrorCode;
        this.respErrorMsg = respErrorMsg;
    }

    /**
     * 重写toString方法
     * @return 返回对象的toString结果
     */
    @Override
    public String toString(){
        return "Result{" +
                "reqResult=" + this.reqResult +
                ", respResult=" + this.respResult +
                ", respErrorCode='" + this.respErrorCode + "\'" +
                ", respErrorMsg='" + this.respErrorMsg + "\'" +
                "}";
    }

    /**
     * 获取请求结果
     * @return 请求成功与否。true:请求成功；false请求失败
     */
    public boolean getReqResult(){
        return this.reqResult;
    }

    /**
     * 设置请求结果
     * @param reqResult 请求成功与否。true:请求成功；false请求失败
     */
    public void  setReqResult(Boolean reqResult){
        this.reqResult = reqResult;
    }

    /**
     * 获取响应结果
     * @return 请求返回的数据
     */
    public T getRespResult(){
        return this.respResult;
    }

    /**
     * 设置响应结果
     * @param respResult 请求返回的数据
     */
    public void setRespResult(T respResult){
        this.respResult = respResult;
    }

    /**
     * 获取错误编码
     * @return 请求返回的错误编码
     */
    public String getRespErrorCode(){
        return this.respErrorCode;
    }

    /**
     * 设置错误编码
     * @param respErrorCode 请求返回的错误编码
     */
    public void setRespErrorCode(String respErrorCode){
        this.respErrorCode = respErrorCode;
    }

    /**
     * 获取请求失败返回的错误信息
     * @return 请求失败返回的错误信息
     */
    public String getRespErrorMsg(){
        return this.respErrorMsg;
    }

    /**
     * 设置请求失败返回的错误信息
     * @param respErrorMsg 请求失败返回的错误信息
     */
    public void setRespErrorMsg(String respErrorMsg){
        this.respErrorMsg = respErrorMsg;
    }

}
