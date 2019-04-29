package com.github.xjch.common.response.test;

import com.github.xjch.common.response.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: xjch
 * Date: 2019/4/29
 * Project: common-response
 * Version: 1.0.0
 * Description: 公共的响应对象 单元测试
 * <p>
 *     1、默认构造响应对象  测试
 *     2、默认构造响应对象和所有set方法  测试
 *     3、根据响应数据构造响应对象 测试
 *     4、根据错误码，错误描述，构造响应对象 测试
 *     5、重写toString方法 测试
 *     6、获取请求结果 测试
 *     7、获取响应结果 测试
 *     8、获取响应错误码 测试
 *     9、获取响应错误信息 测试
 * </p>
 */
public class ResultTest {
    /**
     * 默认构造响应对象 测试
     */
    @Test
    public void buildResult(){
        Result<Object> defaultResult = new Result<>();
        Assert.assertEquals(defaultResult.getReqResult(),false);
        Assert.assertEquals(defaultResult.getRespResult(),null);
        Assert.assertEquals(defaultResult.getRespErrorCode(),null);
        Assert.assertEquals(defaultResult.getRespErrorMsg(),null);
    }

    /**
     * 默认构造响应对象和所有set方法 测试
     */
    @Test
    public void buildResultAndSet(){
        Result<Object> setResult = new Result<>();
        setResult.setReqResult(true);
        setResult.setRespResult("set a string");
        setResult.setRespErrorCode("00000000");
        setResult.setRespErrorMsg("set ok");
        Assert.assertEquals(setResult.getReqResult(),true);
        Assert.assertEquals(setResult.getRespResult(),"set a string");
        Assert.assertEquals(setResult.getRespErrorCode(),"00000000");
        Assert.assertEquals(setResult.getRespErrorMsg(),"set ok");
    }

    /**
     * 根据响应数据构造响应对象 测试
     */
    @Test
    public void buildResultByRespResult(){
        Result<String> stringResult = new Result<>("junit Test");
        Assert.assertEquals(stringResult.getReqResult(),true);
        Assert.assertEquals(stringResult.getRespResult(),"junit Test");
        Assert.assertEquals(stringResult.getRespErrorCode(),null);
        Assert.assertEquals(stringResult.getRespErrorMsg(),null);
    }

    /**
     * 根据错误码，错误描述，构造响应对象 测试
     */
    @Test
    public void buildResultByErrorCodeAndErrorMsg(){
        Result<String> errorResult = new Result<>("00000001","错误1");
        Assert.assertEquals(errorResult.getReqResult(),false);
        Assert.assertEquals(errorResult.getRespResult(),null);
        Assert.assertEquals(errorResult.getRespErrorCode(),"00000001");
        Assert.assertEquals(errorResult.getRespErrorMsg(),"错误1");
    }

    /**
     * 重写toString方法 测试
     */
    @Test
    public void toStringTest(){
        Result<String> okResult = new Result<>("auok");
        okResult.setRespErrorCode("00000000");
        okResult.setRespErrorMsg("i am ok");
        String okResultToString = okResult.toString();
        Assert.assertEquals(okResult.getReqResult(),true);
        Assert.assertEquals(okResult.getRespResult(),"auok");
        Assert.assertEquals(okResult.getRespErrorCode(),"00000000");
        Assert.assertEquals(okResult.getRespErrorMsg(),"i am ok");
        Assert.assertEquals(okResultToString,"Result{reqResult=true, respResult=auok, respErrorCode='00000000', respErrorMsg='i am ok'}");
        Assert.assertTrue(okResultToString.startsWith("Result{"));
        Assert.assertTrue(okResultToString.endsWith("}"));
        Assert.assertTrue(okResultToString.contains("reqResult=true"));
        Assert.assertTrue(okResultToString.contains("respResult=auok"));
        Assert.assertTrue(okResultToString.contains("respErrorCode='00000000'"));
        Assert.assertTrue(okResultToString.contains("respErrorMsg='i am ok'"));
    }

    /**
     * 获取请求结果 测试
     */
    @Test
    public void getReqResult(){
        Result<String> okResult = new Result<>("auok");
        okResult.setRespErrorCode("00000000");
        okResult.setRespErrorMsg("i am ok");
        boolean reqResult = okResult.getReqResult();
        Assert.assertEquals(reqResult,true);
        Assert.assertTrue(reqResult);
    }

    /**
     * 获取响应结果 测试
     */
    @Test
    public void getRespResult(){
        Result<String> okResult = new Result<>("auok");
        okResult.setRespErrorCode("00000000");
        okResult.setRespErrorMsg("i am ok");
        String respResult = okResult.getRespResult();
        Assert.assertEquals(respResult,"auok");
    }

    /**
     * 获取响应错误码 测试
     */
    @Test
    public void getRespErrorCode(){
        Result<String> okResult = new Result<>("auok");
        okResult.setRespErrorCode("00000000");
        okResult.setRespErrorMsg("i am ok");
        String repsErrorCode = okResult.getRespErrorCode();
        Assert.assertEquals(repsErrorCode,"00000000");
    }

    /**
     * 获取响应错误信息 测试
     */
    @Test
    public void getRespErrorMsg(){
        Result<String> okResult = new Result<>("auok");
        okResult.setRespErrorCode("00000000");
        okResult.setRespErrorMsg("i am ok");
        String repsErrorMsg = okResult.getRespErrorMsg();
        Assert.assertEquals(repsErrorMsg,"i am ok");
    }
}