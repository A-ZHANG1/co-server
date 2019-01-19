package com.trade.web.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Wayne.A.Z on 2019/1/18.
 */
public class GeneralResponse<T> {
    @ApiModelProperty(value="返回状态(1=正常，2=错误，3=未登录，4=自定义错误（直接抛出erroMes）)")
    private int status;
    @ApiModelProperty(value="返回数据")
    private T obj;
    @ApiModelProperty(value="错误返回说明")
    private String errorMes;

    public GeneralResponse() {
        errorMes = "";
        status = 1;
    }

    public GeneralResponse(String err) {
        errorMes = err;
        status = 2;
    }

    public GeneralResponse(T obj) {
        this.obj = obj;
        errorMes = "";
        status = 1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getErrorMes() {
        return errorMes;
    }

    public void setErrorMes(String errorMes) {
        this.status = 2;
        this.errorMes = errorMes;
    }

    public boolean isSuccess() {
        return status == 1;
    }
}
