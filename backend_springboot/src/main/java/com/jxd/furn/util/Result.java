package com.jxd.furn.util;

public class Result<T> {
    private String code; //status code
    private String msg; //description of the status
    private T data; // When returning, the data carried is implemented using generics
                    // for better extensibility

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    //no-argument constructor
    public Result() {

    }

    //Parameterized constructor - specifying the returned data
    public Result(T data) {
        this.data = data;
    }

    //return successful Result object
    public static Result success() {
        Result result = new Result<>();
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    //return successful Result object with data
    //to use generics in a static method, should use static <T>
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    //return failed Result object
    public static Result error(String code, String msg) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    //return failed Result object with data

    public static <T> Result<T> error(String code, String msg, T data) {
        Result<T> result = new Result<>(data);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
