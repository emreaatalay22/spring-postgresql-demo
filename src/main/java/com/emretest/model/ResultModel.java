package com.emretest.model;


public class ResultModel<T> {

    private boolean status;
    private String message;
    private T data;
    private T error;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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

    public T getError() {
        return error;
    }

    public void setError(T error) {
        this.error = error;
    }

    public static <T> ResultModel<T> ok(T data){
        ResultModel<T> resultModel = new ResultModel<>();
        resultModel.setData(data);
        resultModel.setStatus(true);
        resultModel.setMessage(null);
        return resultModel;
    }

    public static <T> ResultModel<T> error(String data){
        ResultModel<T> resultModel = new ResultModel<>();
        resultModel.setData(null);
        resultModel.setStatus(false);
        resultModel.setMessage(data);
        return resultModel;
    }
}