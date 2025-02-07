package com.emretest.controller;

import com.emretest.model.ResultModel;

public class RestBaseController   {

    public <T> ResultModel<T> ok(T data){
        return ResultModel.ok(data);
    }

    public <T> ResultModel<T> error(String errorMessage){
        return ResultModel.error(errorMessage);
    }

}
